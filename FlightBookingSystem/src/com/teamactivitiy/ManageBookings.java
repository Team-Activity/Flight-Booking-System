import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageBookings extends JFrame {
    private JTable bookingsTable;
    private JButton modifyButton, cancelButton, checkInButton, eTicketButton;

    public ManageBookings() {
        setTitle("Manage Bookings");
        setSize(600, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        initializeComponents();
        addEventListeners();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeComponents() {
        bookingsTable = new JTable();
        populateTableWithDummyData();
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        add(scrollPane);

        modifyButton = new JButton("Modify Booking");
        cancelButton = new JButton("Cancel Booking");
        checkInButton = new JButton("Check-In Online");
        eTicketButton = new JButton("Get E-Ticket");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifyButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(eTicketButton);

        add(buttonPanel);
    }

    private void addEventListeners() {
        modifyButton.addActionListener(e -> modifyBooking());
        cancelButton.addActionListener(e -> cancelBooking());
        checkInButton.addActionListener(e -> checkIn());
        eTicketButton.addActionListener(e -> getETicket());
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

    private void checkIn() {
        JOptionPane.showMessageDialog(this, "Check-In functionality to be implemented.");
    }

    private void getETicket() {
        JOptionPane.showMessageDialog(this, "E-Ticket functionality to be implemented.");
    }

    private void populateTableWithDummyData() {
        String[] columnNames = {"Booking ID", "User ID", "Departure City", "Destination", "Departure Date", "Airlines", "Price", "Seats"};
        Object[][] data = {
            {1, 101, "City A", "City B", "2023-01-01", "Airline X", 200.00, 2},
            {2, 102, "City C", "City D", "2023-01-03", "Airline Y", 150.00, 1}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        bookingsTable.setModel(model);
    }
}
