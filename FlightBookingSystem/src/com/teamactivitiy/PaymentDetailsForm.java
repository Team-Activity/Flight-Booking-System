import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentDetailsForm extends JDialog {
    public PaymentDetailsForm(JFrame parent) {
        super(parent, "Payment Details", true);
        setLayout(new FlowLayout());
        setSize(300, 200);

        // Add components for payment information
        // ...

        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FinalizeBooking(parent).setVisible(true);
            }
        });
        add(bookButton);

        setLocationRelativeTo(parent);
    }
}
