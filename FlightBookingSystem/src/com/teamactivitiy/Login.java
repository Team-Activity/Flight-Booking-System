import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public static void showLoginDialog(JFrame parentFrame) {
        // Create a JDialog for login
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setLayout(new FlowLayout());
        loginDialog.setSize(300, 200);

        // Create UI components for login
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Add components to dialog
        loginDialog.add(new JLabel("Username:"));
        loginDialog.add(usernameField);
        loginDialog.add(new JLabel("Password:"));
        loginDialog.add(passwordField);
        loginDialog.add(loginButton);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate credentials (replace with actual database validation)
                if (validateCredentials(username, password)) {
                    loginDialog.dispose();
                    FlightBookingApp.userLoggedIn();
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginDialog.setLocationRelativeTo(parentFrame);
        loginDialog.setVisible(true);
    }

    private static boolean validateCredentials(String username, String password) {
        // Replace this logic with actual database validation
        return username.equals("admin") && password.equals("password");
    }
}