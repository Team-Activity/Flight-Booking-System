import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Notification {
    private JFrame frame;

    public Notification() {
        frame = new JFrame("Flight Notification");
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        JLabel messageLabel = new JLabel("<html><body style='text-align: center;'>" + getRandomNotification() + "</body></html>", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        frame.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> frame.dispose());
        frame.add(okButton, BorderLayout.SOUTH);
    }

    public void showNotification() {
        frame.setVisible(true);
    }

    private String getRandomNotification() {
        String[] notifications = {
            "Flight delayed by 30 minutes.",
            "Gate change to Gate 23A.",
            "Flight cancelled due to weather conditions.",
            "Special Deal: 50% off on next ticket purchase!"
        };
        int index = new Random().nextInt(notifications.length);
        return notifications[index];
    }
}

