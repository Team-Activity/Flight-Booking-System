import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManageBookings extends JFrame {
    private JTable bookingsTable;
    private JButton modifyButton, cancelButton;

    public ManageBookings() {
        setTitle("Manage Bookings");
        setSize(800, 600); // Frame size increased for better readability
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        initializeComponents();
        addEventListeners();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeComponents() {
        bookingsTable = new JTable();
        populateTableWithDatabaseData(); // Fetch data from database
        bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode to single
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        add(scrollPane);

        modifyButton = new JButton("Modify Booking");
        cancelButton = new JButton("Cancel Booking");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifyButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel);
    }

    private void addEventListeners() {
        modifyButton.addActionListener(e -> modifyBooking());
        cancelButton.addActionListener(e -> cancelBooking());
    }

    private void modifyBooking() {
        int selectedRow = bookingsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to modify.");
            return;
        }
    
        // Fetch data from the selected row
        int booking_id = (int) bookingsTable.getValueAt(selectedRow, 0);
        String firstname = (String) bookingsTable.getValueAt(selectedRow, 2);
        String lastname = (String) bookingsTable.getValueAt(selectedRow, 3);
        String destination = (String) bookingsTable.getValueAt(selectedRow, 7);
        String departure_date = bookingsTable.getValueAt(selectedRow, 6).toString();
        
        // Assuming the 'seat_numbers' column is the last one in your table model
        String seat_numbers = (String) bookingsTable.getValueAt(selectedRow, bookingsTable.getColumnCount() - 1);
    
        // Open ModifyBookingFrame with the fetched data
        ModifyBookingFrame modifyFrame = new ModifyBookingFrame(booking_id, firstname, lastname, destination, departure_date, seat_numbers);
        modifyFrame.setVisible(true);
    }

    private void cancelBooking() {
        int selectedRow = bookingsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel the selected booking?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int bookingId = (int) bookingsTable.getValueAt(selectedRow, 0); // Assuming the Booking ID is in the first column
                Connection conn = DBConnection.getConnection(); // Replace with your method to get a connection
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM bookings WHERE booking_id = " + bookingId;
                int rowsAffected = stmt.executeUpdate(sql);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Booking cancelled successfully.");
                    ((DefaultTableModel) bookingsTable.getModel()).removeRow(selectedRow); // Remove row from table
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Unable to cancel booking.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in database operation: " + e.getMessage());
            }
        }
    }

    private void populateTableWithDatabaseData() {
        String[] columnNames = {"Booking ID", "User ID", "First Name", "Last Name", "Departure City", "Destination", "Departure Date", "Airlines", "Price", "Seats"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DBConnection.getConnection(); // Replace with your method to get a connection
            Statement stmt = conn.createStatement();
            String sql = "SELECT booking_id, user_id, firstname, lastname, departure_city, destination, departure_date, airlines, price, seat_numbers FROM bookings";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("departure_city"),
                    rs.getString("destination"),
                    rs.getString("departure_date"),
                    rs.getString("airlines"),
                    rs.getDouble("price"),
                    rs.getString("seat_numbers")
                });
            }
            bookingsTable.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error in database connection: " + e.getMessage());
        }
    }
}
