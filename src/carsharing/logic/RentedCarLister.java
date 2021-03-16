package carsharing.logic;

import carsharing.model.Customer;
import carsharing.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentedCarLister {
    private static final String SELECT_RENTED_CAR_SQL =
            "SELECT name, company_id FROM car WHERE id = ?";
    private static final String COMPANY_FROM_CAR_SQL =
            "SELECT name FROM company WHERE id = ?";

    public void listRenterCar(Customer customer) {
        System.out.println();

        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
            return;
        }

        String carName = "";
        long companyId = 0;
        String companyName = "";
        Connection conn = DatabaseConnection.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(SELECT_RENTED_CAR_SQL);
            pstmt.setLong(1, customer.getRentedCarId());
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                carName = resultSet.getString(1);
                companyId = resultSet.getLong(2);
            } else {
                System.out.println("Rental car not found");
            }

            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(COMPANY_FROM_CAR_SQL);
            pstmt.setLong(1, companyId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                companyName = resultSet.getString(1);
            } else {
                System.out.println("Company not found");
            }

            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Your rented car:");
        System.out.println(carName);
        System.out.println("Company:");
        System.out.println(companyName);
    }
}
