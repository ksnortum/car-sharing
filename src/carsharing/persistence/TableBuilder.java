package carsharing.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableBuilder {
    private static final String CREATE_COMPANY_SQL =
            "CREATE TABLE IF NOT EXISTS COMPANY ( " +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR UNIQUE NOT NULL " +
                    ")";

    private static final String CREATE_CAR_SQL =
            "CREATE TABLE IF NOT EXISTS CAR ( " +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR UNIQUE NOT NULL, " +
                    "COMPANY_ID INT NOT NULL, " +
                    "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) " +
                    "REFERENCES COMPANY (ID) " +
                    ")";

    private static final String CREATE_CUSTOMER_SQL =
            "CREATE TABLE IF NOT EXISTS CUSTOMER ( " +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INT, " +
                    "CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) " +
                    "REFERENCES CAR (ID) " +
                    ")";

    public static void buildTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_COMPANY_SQL);
            stmt.executeUpdate(CREATE_CAR_SQL);
            stmt.executeUpdate(CREATE_CUSTOMER_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
