import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FlightBooking extends JFrame {
    private String selectedTime;
    private String layoverType;

    public FlightBooking(String selectedTime, String layoverType) {
        this.selectedTime = selectedTime;
        this.layoverType = layoverType;
        setTitle("Flight Booking Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        mainPanel.add(createDetailPanel("Departure Time: ", selectedTime));
        mainPanel.add(createDetailPanel("Estimated Arrival Time: ", calculateArrivalTime(selectedTime, layoverType)));
        mainPanel.add(createDetailPanel("Terminal: ", getTerminal(selectedTime)));

        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("Book Flight");
        bookButton.addActionListener(e -> onBookFlight());
        buttonPanel.add(bookButton);

        JButton backButton = new JButton("Back to Selection");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel);

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private JPanel createDetailPanel(String label, String detail) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(label));
        panel.add(new JLabel(detail));
        return panel;
    }

    private String calculateArrivalTime(String departureTime, String layoverType) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        try {
            Date departure = sdf.parse(departureTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departure);

            // Add one hour for direct flights, three hours for 1-stop flights
            int hoursToAdd = "1 Stop".equals(layoverType) ? 3 : 1;
            calendar.add(Calendar.HOUR_OF_DAY, hoursToAdd);

            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error calculating arrival time";
        }
    }

    private String getTerminal(String departureTime) {
        return "Terminal 1"; // Replace with actual logic
    }

    private void onBookFlight() {
        new PassengerDetailsForm(this).setVisible(true);
    }
}