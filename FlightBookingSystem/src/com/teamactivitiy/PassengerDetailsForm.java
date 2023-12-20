import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerDetailsForm extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField birthdayField;
    private JComboBox<String> genderComboBox;

    public PassengerDetailsForm(JFrame parent) {
        super(parent, "Passenger Details", true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(400, 200);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        add(formPanel);

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Birthday (dd/mm/yyyy):"));
        birthdayField = new JTextField(20);
        formPanel.add(birthdayField);

        formPanel.add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        formPanel.add(genderComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    dispose();
                    new SeatSelectionForm(parent).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(PassengerDetailsForm.this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(nextButton);

        add(buttonPanel);
        setLocationRelativeTo(parent);
    }

    private boolean validateFields() {
        return !firstNameField.getText().trim().isEmpty() &&
               !lastNameField.getText().trim().isEmpty() &&
               !birthdayField.getText().trim().isEmpty() &&
               genderComboBox.getSelectedItem() != null;
    }
}