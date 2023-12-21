import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyBookingFrame extends JFrame {
    private JTextField firstNameField, lastNameField, destinationField, departureDateField, seatNumbersField;
    private int bookingId; // To identify which booking is being modified

    public ModifyBookingFrame(int bookingId, String firstName, String lastName, String destination, String departureDate, String seatNumbers) {
        this.bookingId = bookingId;

        setTitle("Modify Booking");
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        // Initialize fields with current booking data
        firstNameField = new JTextField(firstName);
        lastNameField = new JTextField(lastName);
        destinationField = new JTextField(destination);
        departureDateField = new JTextField(departureDate);
        seatNumbersField = new JTextField(seatNumbers);

        // Add fields to frame
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Destination:"));
        add(destinationField);
        add(new JLabel("Departure Date:"));
        add(departureDateField);
        add(new JLabel("Seat Numbers:"));
        add(seatNumbersField);

        JButton updateButton = new JButton("Update Booking");
        updateButton.addActionListener(e -> updateBooking());
        add(updateButton);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void updateBooking() {
        // Get updated data from fields
        String updatedFirstName = firstNameField.getText();
        String updatedLastName = lastNameField.getText();
        String updatedDestination = destinationField.getText();
        String updatedDepartureDate = departureDateField.getText();
        String updatedSeatNumbers = seatNumbersField.getText();

        // Update the booking in the database
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE bookings SET firstname = ?, lastname = ?, destination = ?, departure_date = ?, seat_numbers = ? WHERE booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, updatedFirstName);
            pstmt.setString(2, updatedLastName);
            pstmt.setString(3, updatedDestination);
            pstmt.setString(4, updatedDepartureDate);
            pstmt.setString(5, updatedSeatNumbers);
            pstmt.setInt(6, bookingId);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Booking updated successfully!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update booking: " + e.getMessage());
        }
    }
}

