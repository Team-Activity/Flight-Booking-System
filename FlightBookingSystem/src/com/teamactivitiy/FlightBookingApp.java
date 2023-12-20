import javax.swing.*;
import java.awt.*;

public class FlightBookingApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Further increase the window size
        JFrame frame = new JFrame("Team Activity Flight Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500); // Increased size
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Enlarge and reposition the title label
        JLabel titleLabel = new JLabel("Team Activity Flight Booking", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30)); // Enlarged font
        titleLabel.setBounds(100, 30, 600, 50); // Adjusted bounds
        panel.add(titleLabel);

        // Enlarge and reposition the "Search for flights" button
        JButton searchButton = new JButton("Search for flights");
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Enlarged font
        searchButton.setBounds(250, 150, 300, 50); // Adjusted position and size
        panel.add(searchButton);

        // Enlarge and reposition the "Manage Bookings" button
        JButton manageButton = new JButton("Manage Bookings");
        manageButton.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Enlarged font
        manageButton.setBounds(250, 220, 300, 50); // Adjusted position and size
        panel.add(manageButton);

        // Enlarge and reposition the "My Account" button
        JButton accountButton = new JButton("My Account");
        accountButton.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Enlarged font
        accountButton.setBounds(620, 30, 150, 50); // Adjusted position and size
        panel.add(accountButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
