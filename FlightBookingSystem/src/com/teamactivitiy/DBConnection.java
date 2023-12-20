import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public static void addBooking(String cardNumber, Set<String> selectedSeats) {
        String sql = "INSERT INTO bookings(card_number, seat_numbers) VALUES(?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            String seatNumbers = String.join(", ", selectedSeats);
            pstmt.setString(2, seatNumbers);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL error occurred while inserting booking data.");
            e.printStackTrace();
        }
    }
}