package carsharing.logic;

import carsharing.persistence.DatabaseConnection;
import carsharing.utils.Inputer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyCreator {
    private static final String COMPANY_INSERT_SQL =
            "INSERT INTO company (name) VALUES (?)";

    public void execute() {
        String name = Inputer.nextString("Enter the company name: ");
        insertCompanyName(name);
    }

    private void insertCompanyName(String name) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(COMPANY_INSERT_SQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
