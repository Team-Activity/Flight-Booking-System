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
        JOptionPane.showMessageDialog(this, "Modify Booking functionality to be implemented.");
    }

    private void cancelBooking() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel the selected booking?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Booking cancelled successfully.");
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
                    rs.getDate("departure_date"),
                    rs.getString("airlines"),
                    rs.getDouble("price"),
                    rs.getInt("seat_numbers")
                });
            }
            bookingsTable.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error in database connection: " + e.getMessage());
        }
    }
}