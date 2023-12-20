import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    private JTextField usernameField, firstNameField, lastNameField, addressField, phoneNumberField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public Register() {
        createWindow();
        initializeComponents();
        addActionEvents();
    }

    private void createWindow() {
        setTitle("User Registration");
        setSize(350, 400); // Adjusted window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(field);
        return panel;
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

                insertNewUser(username, password, firstName, lastName, address, phoneNumber);
            }
        });

    }

    private Connection connect() {
        // Replace with your database connection details
        String url = "jdbc:sqlite:path_to_your_database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void insertNewUser(String username, String password, String firstName, String lastName, String address, String phoneNumber) {
        String sql = "INSERT INTO users(username, password, firstname, lastname, address, phonenumber) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, address);
            pstmt.setString(6, phoneNumber);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
}
