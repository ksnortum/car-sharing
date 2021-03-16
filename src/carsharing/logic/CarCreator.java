package carsharing.logic;

import carsharing.model.Company;
import carsharing.persistence.DatabaseConnection;
import carsharing.utils.Inputer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarCreator {
    private static final String CAR_INSERT_SQL =
            "INSERT INTO car (name, company_id) VALUES (?, ?)";

    public void execute(Company company) {
        String name = Inputer.nextString("Enter the car name: ");
        insertCarName(name, company);
    }

    private void insertCarName(String name, Company company) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(CAR_INSERT_SQL)) {
            pstmt.setString(1, name);
            pstmt.setLong(2, company.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
