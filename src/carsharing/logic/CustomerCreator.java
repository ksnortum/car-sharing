package carsharing.logic;

import carsharing.persistence.DatabaseConnection;
import carsharing.utils.Inputer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerCreator {
    private static final String CUSTOMER_INSERT_SQL =
            "INSERT INTO customer (name, rented_car_id) VALUES (?, NULL)";

    public void createCustomer() {
        System.out.println();
        String name = Inputer.nextString("Enter the customer name: ");
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(CUSTOMER_INSERT_SQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
