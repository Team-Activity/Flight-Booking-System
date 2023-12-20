import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class SeatSelectionForm extends JDialog {
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLUMNS_PER_SIDE = 2; // Number of columns per side of the aisle
    private Set<String> selectedSeats = new HashSet<>();
    private ImageIcon seatIcon;

    public SeatSelectionForm(JFrame parent) {
        super(parent, "Seat Selection", true);
        getContentPane().setLayout(new BorderLayout()); // Use BorderLayout for the main layout
        setSize(1000, 800);

        loadSeatIcon();
        createSeatButtons();
        addNextButton();

        setLocationRelativeTo(parent);
    }

    private void loadSeatIcon() {
        try {
            ImageIcon originalIcon = new ImageIcon("C:\\Users\\Dador\\Documents\\GitHub\\Flight-Booking-System\\FlightBookingSystem\\src\\com\\teamactivitiy\\seat.png");
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale it to 30x30
            seatIcon = new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load seat icon.", "Error", JOptionPane.ERROR_MESSAGE);
            seatIcon = null;
        }
    }

    private void createSeatButtons() {
        JPanel leftPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLUMNS_PER_SIDE));
        JPanel rightPanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLUMNS_PER_SIDE));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int column = 0; column < NUM_COLUMNS_PER_SIDE; column++) {
                leftPanel.add(createSeatButton(row, column));
            }
            for (int column = 0; column < NUM_COLUMNS_PER_SIDE; column++) {
                rightPanel.add(createSeatButton(row, column + NUM_COLUMNS_PER_SIDE + 1)); // Offset for aisle
            }
        }

        mainPanel.add(leftPanel);
        mainPanel.add(Box.createHorizontalStrut(40)); // This represents the aisle
        mainPanel.add(rightPanel);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createSeatButton(int row, int seatPosition) {
        char seatChar = (char) ('A' + seatPosition % (NUM_COLUMNS_PER_SIDE * 2));
        String seatId = String.format("%d%c", row + 1, seatChar);
        JButton seatButton = new JButton(seatIcon);
        seatButton.setActionCommand(seatId);
        seatButton.addActionListener(this::toggleSeatSelection);
        return seatButton;
    }

    private void toggleSeatSelection(ActionEvent e) {
        JButton seatButton = (JButton) e.getSource();
        String seatId = seatButton.getActionCommand();
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId);
            seatButton.setIcon(seatIcon); // Reset to default icon
        } else {
            selectedSeats.add(seatId);
            seatButton.setIcon(null); // Visually indicate selection, change as needed
        }
    }

    private void addNextButton() {
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (!selectedSeats.isEmpty()) {
                dispose();
                new PaymentDetailsForm((JFrame) getParent(), selectedSeats).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seat.", "Selection Required", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextButtonPanel.add(nextButton);
        getContentPane().add(nextButtonPanel, BorderLayout.SOUTH);
    }
}