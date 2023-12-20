import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlightBookingApp {

    private static boolean isUserSignedIn = false;
    private static JFrame frame;

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
        panel.add(searchButton);

        JButton manageButton = new JButton("Manage Bookings");
        manageButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        manageButton.setBounds(250, 220, 300, 50);
        panel.add(manageButton);

        JButton accountButton = new JButton("My Account");
        accountButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        accountButton.setBounds(620, 30, 150, 50);
        panel.add(accountButton);

        JPopupMenu accountPopup = new JPopupMenu();
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> {
            Login.showLoginDialog(frame);
        });
        JMenuItem registerItem = new JMenuItem("Register");
        registerItem.addActionListener(e -> {
            // Registration logic here
            JOptionPane.showMessageDialog(frame, "Registration functionality not implemented yet.");
        });
        accountPopup.add(loginItem);
        accountPopup.add(registerItem);

        accountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isUserSignedIn) {
                    accountPopup.show(accountButton, 0, accountButton.getHeight());
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void userLoggedIn() {
        isUserSignedIn = true;
        JOptionPane.showMessageDialog(frame, "Login Successful!");
    }
}
