import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:FlightBookingSystem/flightbookingsystemdb.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void addFlightDetails(int userId, String departureCity, String destination, Date departureDate,
                                        String airlines, String layoverType, int price) throws SQLException {
        String sql = "INSERT INTO bookings (user_id, departure_city, destination, departure_date, airlines, layover_type, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, departureCity);
            pstmt.setString(3, destination);
            pstmt.setTimestamp(4, new Timestamp(departureDate.getTime()));
            pstmt.setString(5, airlines);
            pstmt.setString(6, layoverType);
            pstmt.setDouble(7, price);
            pstmt.executeUpdate();
        }
    }

    public static void updateBooking(String cardNumber, Set<String> selectedSeats) throws SQLException {
        String sql = "UPDATE bookings SET card_number = ?, seat_numbers = ? WHERE booking_id = (SELECT booking_id FROM bookings WHERE card_number IS NULL AND seat_numbers IS NULL LIMIT 1)";
    
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            String seatNumbers = String.join(", ", selectedSeats);
            pstmt.setString(2, seatNumbers);
            int updatedRows = pstmt.executeUpdate();
    
            if (updatedRows == 0) {
                System.err.println("No unassigned booking found to update.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred while updating booking data.");
            e.printStackTrace();
        }
    }
    
}