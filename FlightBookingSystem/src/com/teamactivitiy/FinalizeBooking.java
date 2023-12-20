import javax.swing.*;
import java.awt.*;

public class FinalizeBooking extends JDialog {
    public FinalizeBooking(JFrame parent) {
        super(parent, "Booking Confirmation", true);
        setLayout(new FlowLayout());
        setSize(300, 200);

        // Add components to confirm the booking
        JLabel confirmationLabel = new JLabel("Booking Confirmed!");
        add(confirmationLabel);

        setLocationRelativeTo(parent);
    }
}
