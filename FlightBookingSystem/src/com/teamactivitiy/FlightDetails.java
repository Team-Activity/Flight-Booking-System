import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDetails extends JFrame {
    private String layoverType;

    public FlightDetails(String layoverType) {
        this.layoverType = layoverType;
        setTitle("Available Flight Times");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400); // Increased size of the frame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        List<String> times = calculateFlightTimes(layoverType);
        addFlightTimes(times);

        setVisible(true);
        setLocationRelativeTo(null); // Center the window
    }

    private void addFlightTimes(List<String> times) {
        for (String time : times) {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel timeLabel = new JLabel(time);
            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(e -> onTimeSelected(time));

            panel.add(timeLabel, BorderLayout.CENTER);
            panel.add(selectButton, BorderLayout.EAST);

            add(panel);
        }
    }

    private void onTimeSelected(String time) {
        new FlightBooking(time, layoverType); // Open FlightBooking window with the selected time and layover type
    }

    private List<String> calculateFlightTimes(String layoverType) {
        List<String> times = new ArrayList<>();
        int hour = 1; // Starting at 1 AM

        if ("Direct".equals(layoverType)) {
            for (int i = 0; i < 8; i++) {
                times.add(formatTime(hour));
                hour = (hour + 3) % 24; // Increment by 3 hours
            }
        } else if ("1 Stop".equals(layoverType)) {
            for (int i = 0; i < 4; i++) {
                times.add(formatTime(hour));
                hour = (hour + 3) % 24; // Increment by 3 hours
            }
        }
        return times;
    }

    private String formatTime(int hour) {
        int displayHour = hour > 12 ? hour - 12 : (hour == 0 ? 12 : hour);
        String amPm = hour >= 12 && hour < 24 ? "PM" : "AM";
        return String.format("%02d:00 %s", displayHour, amPm);
    }
}