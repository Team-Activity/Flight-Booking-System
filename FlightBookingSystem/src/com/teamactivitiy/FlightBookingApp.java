import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlightBookingApp {

    private static boolean isUserSignedIn = false;
    private static JFrame frame;
    private static JPopupMenu accountPopup;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Team Activity Flight Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Team Activity Flight Booking", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setBounds(100, 30, 600, 50);
        panel.add(titleLabel);

        JButton searchButton = new JButton("Search for flights");
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchButton.setBounds(250, 150, 300, 50);
        searchButton.addActionListener(e -> openFlightSearchWindow());
        panel.add(searchButton);

        JButton manageButton = new JButton("Manage Bookings");
        manageButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        manageButton.setBounds(250, 220, 300, 50);
        panel.add(manageButton);

        JButton accountButton = new JButton("My Account");
        accountButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        accountButton.setBounds(620, 30, 150, 50);
        panel.add(accountButton);

        accountPopup = new JPopupMenu();
        updateAccountPopup();

        accountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                updateAccountPopup();
                accountPopup.show(accountButton, 0, accountButton.getHeight());
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void updateAccountPopup() {
        accountPopup.removeAll();

        if (isUserSignedIn) {
            JMenuItem logoutItem = new JMenuItem("Logout");
            logoutItem.addActionListener(e -> userLoggedOut());
            accountPopup.add(logoutItem);
        } else {
            JMenuItem loginItem = new JMenuItem("Login");
            loginItem.addActionListener(e -> Login.showLoginDialog(frame));
            JMenuItem registerItem = new JMenuItem("Register");
            registerItem.addActionListener(e -> {
                new Register(frame).setVisible(true);
            });
            accountPopup.add(loginItem);
            accountPopup.add(registerItem);
        }
    }

    public static void userLoggedIn() {
        isUserSignedIn = true;
        updateAccountPopup();
        JOptionPane.showMessageDialog(frame, "Login Successful!");
    }

    private static void userLoggedOut() {
        isUserSignedIn = false;
        updateAccountPopup();
        JOptionPane.showMessageDialog(frame, "Logged out successfully.");
    }

    private static void openFlightSearchWindow() {
        FlightSearch flightSearchWindow = new FlightSearch(); // Assuming FlightSearch is your class
        flightSearchWindow.setVisible(true);
    }
}
