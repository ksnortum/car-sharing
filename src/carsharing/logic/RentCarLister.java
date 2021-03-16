package carsharing.logic;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentCarLister {
    private static final String CAR_LIST_SQL =
            "SELECT id, name, company_id " +
                    "FROM car " +
                    "WHERE company_id = ? " +
                    "AND id NOT IN (" +
                    "  SELECT rented_car_id " +
                    "  FROM customer " +
                    "  WHERE rented_car_id IS NOT NULL) " +
                    "ORDER BY id";

    public List<Car> printCars(Company company) {
        Optional<List<Car>> optionalCars = getCars(company);
        List<Car> cars = new ArrayList<>();

        if (optionalCars.isPresent()) {
            System.out.println();
            cars = optionalCars.get();

            if (cars.isEmpty()) {
                System.out.printf("No available cars in the '%s'%n", company.getName());
            } else {
                System.out.println("Choose a car:");

                for (int i = 0; i < cars.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, cars.get(i).getName());
                }

                System.out.println("0. Back");
            }
        }

        return cars;
    }

    private Optional<List<Car>> getCars(Company company) {
        Connection connection = DatabaseConnection.getConnection();
        List<Car> cars = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CAR_LIST_SQL);
            preparedStatement.setLong(1, company.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Car car = new Car(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getLong(3)
                );
                cars.add(car);
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(cars);
    }
}
