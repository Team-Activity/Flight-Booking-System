import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatSelectionForm extends JDialog {
    public SeatSelectionForm(JFrame parent) {
        super(parent, "Seat Selection", true);
        setLayout(new FlowLayout());
        setSize(300, 200);

        // Add components for seat selection
        // ...

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PaymentDetailsForm(parent).setVisible(true);
            }
        });
        add(nextButton);

        setLocationRelativeTo(parent);
    }
}
