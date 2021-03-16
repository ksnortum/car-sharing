package carsharing.logic;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarLister {
    private static final String CAR_LIST_SQL =
            "SELECT id, name, company_id FROM car WHERE company_id = ?";

    public void listCars(Company company) {
        Optional<List<Car>> optionalCars = getCars(company);

        optionalCars.ifPresent(cars -> {
            System.out.println();

            if (cars.isEmpty()) {
                System.out.println("The car list is empty!");
            } else {
                System.out.println("Car list:");

                for (int i = 0; i < cars.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, cars.get(i).getName());
                }
            }
        });
    }

    private Optional<List<Car>> getCars(Company company) {
        Connection conn = DatabaseConnection.getConnection();
        List<Car> cars = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(CAR_LIST_SQL);
            pstmt.setLong(1, company.getId());
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                Car car = new Car(resultSet.getLong(1),
                        resultSet.getString(2),
                        company.getId());
                cars.add(car);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(cars);
    }
}
