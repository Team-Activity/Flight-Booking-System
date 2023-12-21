import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FlightSearch extends JFrame {
    private JComboBox<String> departureCityComboBox, destinationComboBox, airlinesComboBox, layoverComboBox;
    private JTextField priceRangeField;
    private JSpinner departureDateSpinner;
    private JButton searchButton;
    private int userId;
    private String passengerDetails;

    public FlightSearch() {
        setTitle("Flight Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        String[] cities = {"Cebu", "Manila", "Bohol", "Tacloban", "Baguio", "Siargao", "Cameguin", "Ormoc", "Davao", "Zamboanga", "Surigao"};
        departureCityComboBox = new JComboBox<>(cities);
        destinationComboBox = new JComboBox<>(cities);

        priceRangeField = new JTextField();
        priceRangeField.setEditable(false);

        airlinesComboBox = new JComboBox<>(new String[]{"Cebu Pacific", "Air Asia", "Philippine (PAL)"});
        airlinesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice();
            }
        });

        layoverComboBox = new JComboBox<>(new String[]{"Choose", "Direct", "1 Stop"});
        layoverComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice();
            }
        });

        departureDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(departureDateSpinner, "dd/MM/yyyy");
        departureDateSpinner.setEditor(dateEditor);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchActionListener());

        addComponentsToPanel();

        setVisible(true);
        setLocationRelativeTo(null); // Center the window
    }

    private void addComponentsToPanel() {
        add(new JLabel("Departure City:"));
        add(departureCityComboBox);
        add(new JLabel("Destination:"));
        add(destinationComboBox);
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

    private void updatePrice() {
        String selectedAirline = (String) airlinesComboBox.getSelectedItem();
        String selectedLayover = (String) layoverComboBox.getSelectedItem();
        int price = calculatePrice(selectedAirline, selectedLayover);
        priceRangeField.setText(price + " PHP");
    }

    private int calculatePrice(String airline, String layover) {
        int price = 0;
        if ("Cebu Pacific".equals(airline)) {
            price = 1200;
        } else if ("Air Asia".equals(airline)) {
            price = 1300;
        } else if ("Philippine (PAL)".equals(airline)) {
            price = 3000;
        }

        if ("1 Stop".equals(layover)) {
            price *= 2;
        }
        return price;
    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Collect booking details
            String departureCity = (String) departureCityComboBox.getSelectedItem();
            String destination = (String) destinationComboBox.getSelectedItem();
            String airlines = (String) airlinesComboBox.getSelectedItem();
            String layoverType = (String) layoverComboBox.getSelectedItem();
            Date departureDate = (Date) departureDateSpinner.getValue();
            int price = calculatePrice(airlines, layoverType);

            try {
                DBConnection.addFlightDetails(userId, departureCity, destination, departureDate, airlines, layoverType, price);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            String selectedLayover = (String) layoverComboBox.getSelectedItem();
            new FlightDetails(selectedLayover);
}
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightSearch());
    }
} 