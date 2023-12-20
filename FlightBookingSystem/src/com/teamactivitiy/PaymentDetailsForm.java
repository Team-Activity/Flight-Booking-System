import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;

public class PaymentDetailsForm extends JDialog {
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private Set<String> selectedSeats;
    private JFrame mainAppFrame; // Reference to the main application frame

    public PaymentDetailsForm(JFrame parent, Set<String> selectedSeats) {
        super(parent, "Payment Details", true);
        this.mainAppFrame = parent; // Store the reference to the main application frame
        this.selectedSeats = selectedSeats;

        // Layout setup
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(400, 200);

        // Card number input
        JPanel cardNumberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardNumberPanel.add(new JLabel("Card Number:"));
        cardNumberField = new JTextField(16);
        cardNumberPanel.add(cardNumberField);
        getContentPane().add(cardNumberPanel);

        // Expiry date input
        JPanel expiryDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        expiryDatePanel.add(new JLabel("Expiry Date (MM/YY):"));
        expiryDateField = new JTextField(5);
        expiryDatePanel.add(expiryDateField);
        getContentPane().add(expiryDatePanel);

        // CVV input
        JPanel cvvPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cvvPanel.add(new JLabel("CVV:"));
        cvvField = new JTextField(3);
        cvvPanel.add(cvvField);
        getContentPane().add(cvvPanel);

        // Book button
        JPanel bookButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton bookButton = new JButton("Book");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set a larger font
        bookButton.setPreferredSize(new Dimension(150, 50)); // Set the preferred size to make the button larger
        bookButton.addActionListener(this::processPayment);
        bookButtonPanel.add(bookButton);
        getContentPane().add(bookButtonPanel);

        setLocationRelativeTo(parent);
    }

    private void processPayment(ActionEvent e) {
        // Here you would handle the payment processing
        // For now, just display a confirmation message
        JOptionPane.showMessageDialog(this, "Payment successful!\nSeats: " + selectedSeats, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
        closeAllWindowsExceptMain();
    }

    private void closeAllWindowsExceptMain() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window != mainAppFrame && window instanceof JDialog) {
                window.dispose(); // Close the dialog
            }
        }
        // Bring the main application frame to the front if needed
        mainAppFrame.setState(JFrame.NORMAL);
        mainAppFrame.toFront();
    }
}