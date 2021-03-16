package carsharing.logic;

import carsharing.model.Customer;
import carsharing.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnCarProcessing {
    private static final String RETURN_CAR_SQL =
            "UPDATE customer " +
                    "SET rented_car_id = NULL " +
                    "WHERE id = ? ";

    public void execute(Customer customer) {
        System.out.println();

        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
            return;
        }

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RETURN_CAR_SQL);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customer.setRentedCarId(null);
        System.out.println("You've returned a rented car!");
    }
}
