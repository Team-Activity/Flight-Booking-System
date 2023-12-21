import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatMapApp extends JFrame {

    private JLabel selectedSeatLabel;
    private JButton currentlySelectedSeat = null; // Variable to track the currently selected seat

    public SeatMapApp() {
        setTitle("Seat Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JPanel seatMapPanel = createSeatMapPanel();
        add(seatMapPanel, BorderLayout.CENTER);

        selectedSeatLabel = new JLabel("Selected Seat: ");
        add(selectedSeatLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSeatMapPanel() {
        JPanel seatMapPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 2; col++) {
                JButton seatButton = createSeatButton(row + "-" + col);
                seatMapPanel.add(seatButton);
            }
        }

        return seatMapPanel;
    }

    private JButton createSeatButton(String seatNumber) {
        JButton seatButton = new JButton(seatNumber);
        seatButton.addActionListener(new SeatClickListener());
        seatButton.setPreferredSize(new Dimension(50, 50));
        return seatButton;
    }

    private class SeatClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedSeat = (JButton) e.getSource();
            String seatNumber = clickedSeat.getText();

            if (currentlySelectedSeat != null) {
                // Deselect the previously selected seat
                currentlySelectedSeat.setBackground(null);
            }

            // Select new seat and update label
            clickedSeat.setBackground(Color.GREEN); // Set a color to indicate selection
            selectedSeatLabel.setText("Selected Seat: " + seatNumber);

            // Update currently selected seat
            currentlySelectedSeat = clickedSeat;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SeatMapApp();
            }
        });
    }
}