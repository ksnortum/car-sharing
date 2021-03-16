package carsharing.logic;

import carsharing.model.Car;
import carsharing.model.Customer;
import carsharing.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentCarProcessing {
    private static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer SET rented_car_id = ? WHERE id = ?";

    public void execute(Car car, Customer customer) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
            preparedStatement.setLong(1, car.getId());
            preparedStatement.setLong(2, customer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customer.setRentedCarId(car.getId());
        System.out.printf("%nYou rented '%s'%n", car.getName());
    }
}
