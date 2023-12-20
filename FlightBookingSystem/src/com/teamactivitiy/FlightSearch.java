import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSearch extends JFrame {
    private JTextField departureCityField, destinationField, priceRangeField;
    private JComboBox<String> airlinesComboBox, layoverComboBox;
    private JSpinner departureDateSpinner;
    private JButton searchButton;

    public FlightSearch() {
        setTitle("Flight Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        departureCityField = new JTextField();
        destinationField = new JTextField();
        priceRangeField = new JTextField();
        
        airlinesComboBox = new JComboBox<>(); // Populate with airline names
        layoverComboBox = new JComboBox<>(new String[]{"Any", "Non-stop", "1 Stop", "2+ Stops"});

        departureDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(departureDateSpinner, "dd/MM/yyyy");
        departureDateSpinner.setEditor(dateEditor);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchActionListener());

        addComponentsToPanel();

        setVisible(true);
    }

    private void addComponentsToPanel() {
        add(new JLabel("Departure City:"));
        add(departureCityField);
        add(new JLabel("Destination:"));
        add(destinationField);
        add(new JLabel("Departure Date:"));
        add(departureDateSpinner);
        add(new JLabel("Airlines:"));
        add(airlinesComboBox);
        add(new JLabel("Layovers:"));
        add(layoverComboBox);
        add(new JLabel("Price Range:"));
        add(priceRangeField);
        add(searchButton);
    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Here you can implement the logic to fetch data from the database based on the user inputs
            String departureCity = departureCityField.getText();
            String destination = destinationField.getText();
            String priceRange = priceRangeField.getText();
            String selectedAirline = (String) airlinesComboBox.getSelectedItem();
            String selectedLayover = (String) layoverComboBox.getSelectedItem();
            Date departureDate = (Date) departureDateSpinner.getValue();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(departureDate);

            // Display a message or update the search results display
            JOptionPane.showMessageDialog(FlightSearch.this, "Search clicked!\n" +
                    "Departure City: " + departureCity + "\n" +
                    "Destination: " + destination + "\n" +
                    "Departure Date: " + formattedDate + "\n" +
                    "Airline: " + selectedAirline + "\n" +
                    "Layovers: " + selectedLayover + "\n" +
                    "Price Range: " + priceRange);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightSearch());
    }
}
