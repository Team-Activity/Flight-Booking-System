import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightDetailsApp extends JFrame {

    public FlightDetailsApp() {

        setTitle("Flight Details App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel flightDetailsLabel = new JLabel("Flight Details:");

        JButton viewFlightInfoButton = new JButton("View Flight Information");

        JButton viewSeatMapsButton = new JButton("View Seat Maps");

        // Add action listeners for the buttons
        viewFlightInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click, e.g., open a new window for flight information
                showFlightInformationDialog();
            }
        });

        viewSeatMapsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click, open SeatMapApp
                openSeatMapsApp();
            }
        });

        // Add components to the frame
        add(flightDetailsLabel);
        add(viewFlightInfoButton);
        add(viewSeatMapsButton);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void showFlightInformationDialog() {
        // Create a dialog for displaying flight information
        JDialog flightInfoDialog = new JDialog(this, "Flight Information", true);
        flightInfoDialog.setSize(300, 150);
        flightInfoDialog.setLayout(new GridLayout(4, 1));

        // Add flight information components
        flightInfoDialog.add(new JLabel("Departure Time: 08:00 AM"));
        flightInfoDialog.add(new JLabel("Arrival Time: 12:00 PM"));
        flightInfoDialog.add(new JLabel("Layover Duration: 2 hours"));
        flightInfoDialog.add(new JLabel("Available Seats: 50"));

        // Set the dialog properties
        flightInfoDialog.setLocationRelativeTo(this);
        flightInfoDialog.setVisible(true);
    }

    private void openSeatMapsApp() {
        // Create an instance of SeatMapApp and make it visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SeatMapApp();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlightDetailsApp();
            }
        });
    }
}
