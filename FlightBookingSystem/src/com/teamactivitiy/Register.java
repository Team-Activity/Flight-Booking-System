import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField, addressField, phoneNumberField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JFrame parentFrame;

    public Register(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        createWindow();
        initializeComponents();
        addActionEvents();
    }

    private void createWindow() {
        setTitle("User Registration");
        setSize(350, 400); // Adjusted window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2); // Adding some padding
    
        // Username Field
        usernameField = new JTextField(15);
        panel.add(new JLabel("Username:"), gbc);
        panel.add(usernameField, gbc);
    
        // Password Field
        passwordField = new JPasswordField(15);
        panel.add(new JLabel("Password:"), gbc);
        panel.add(passwordField, gbc);
    
        // First Name Field
        firstNameField = new JTextField(15);
        panel.add(new JLabel("First Name:"), gbc);
        panel.add(firstNameField, gbc);
    
        // Last Name Field
        lastNameField = new JTextField(15);
        panel.add(new JLabel("Last Name:"), gbc);
        panel.add(lastNameField, gbc);
    
        // Address Field
        addressField = new JTextField(15);
        panel.add(new JLabel("Address:"), gbc);
        panel.add(addressField, gbc);
    
        // Phone Number Field
        phoneNumberField = new JTextField(15);
        panel.add(new JLabel("Phone Number:"), gbc);
        panel.add(phoneNumberField, gbc);
    
        // Register Button
        registerButton = new JButton("Register");
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);
    
        add(panel);
    }

    private void addActionEvents() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();

                if (insertNewUser(username, password, firstName, lastName, address, phoneNumber)) {
                    // Close this window and log the user in
                    setVisible(false);
                    FlightBookingApp.userLoggedIn();
                }
            }
        });
    }

    private boolean insertNewUser(String username, String password, String firstname, String lastname, String address, String phonenumber) {
        String sql = "INSERT INTO users(username, password, firstname, lastname, address, phonenumber) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.setString(5, address);
            pstmt.setString(6, phonenumber);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Register(null).setVisible(true));
    }
}
