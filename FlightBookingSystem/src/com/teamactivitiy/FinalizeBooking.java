import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;

public class FinalizeBooking extends JDialog {
    private String cardNumber;
    private Set<String> selectedSeats;

    public FinalizeBooking(JFrame parent, String cardNumber, Set<String> selectedSeats) {
        super(parent, "Finalize Booking", true);
        this.cardNumber = cardNumber;
        this.selectedSeats = selectedSeats;

        setLayout(new BorderLayout());
        setSize(300, 200);

        pack();
        setLocationRelativeTo(parent);

        addBookingToDatabase(); 
    }

    private void addBookingToDatabase() {
        try {
            DBConnection.updateBooking(cardNumber, selectedSeats);
            JOptionPane.showMessageDialog(this, "Booking successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to finalize booking.", "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dispose(); // Dispose of the dialog after showing the message
        }
    }
}