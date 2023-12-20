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
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setPreferredSize(new Dimension(150, 50));
        bookButton.addActionListener(this::processPayment);
        bookButtonPanel.add(bookButton);
        getContentPane().add(bookButtonPanel);

        setLocationRelativeTo(parent);
    }

    private void processPayment(ActionEvent e) {
        // Placeholder for actual payment processing logic
        String cardNumber = cardNumberField.getText();
        String expiryDate = expiryDateField.getText();
        String cvv = cvvField.getText();

        // You would typically validate the input here
        // For now, let's assume the payment was successful
        JOptionPane.showMessageDialog(this, "Payment successful!\nSeats: " + selectedSeats, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
        dispose();

        // Call FinalizeBooking here with the card number and selected seats
        new FinalizeBooking(mainAppFrame, cardNumber, selectedSeats);
    }
}