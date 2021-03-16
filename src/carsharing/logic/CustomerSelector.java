package carsharing.logic;

import carsharing.model.Customer;
import carsharing.persistence.DatabaseConnection;
import carsharing.utils.Inputer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerSelector {
    private static final String CUSTOMER_LIST_SQL =
            "SELECT id, name, rented_car_id FROM customer";

    private final CustomerProcessing customerProcessing = new CustomerProcessing();

    public void execute() {
        List<Customer> customers = printCustomers();

        if (customers.isEmpty()) {
            return;
        }

        int selection = Inputer.nextInt("Enter your selection: ");

        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= customers.size()) {
            customerProcessing.execute(customers.get(selection - 1));
        } else {
            System.out.println("Selection invalid");
        }
    }

    private List<Customer> printCustomers() {
        Optional<List<Customer>> optionalCustomers = getCustomers();
        List<Customer> customers = new ArrayList<>();

        if (optionalCustomers.isPresent()) {
            System.out.println();
            customers = optionalCustomers.get();

            if (customers.isEmpty()) {
                System.out.println("The customer list is empty!");
            } else {
                System.out.println("Choose a customer:");

                for (int i = 0; i < customers.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, customers.get(i).getName());
                }

                System.out.println("0. Back");
            }
        }

        return customers;
    }

    private Optional<List<Customer>> getCustomers() {
        Connection conn = DatabaseConnection.getConnection();
        List<Customer> customers = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(CUSTOMER_LIST_SQL)) {

            while(resultSet.next()) {
                Customer customer = new Customer(resultSet.getLong(1), resultSet.getString(2));
                Long rentedCarId = resultSet.getLong(3);

                if (resultSet.wasNull()) {
                    rentedCarId = null;
                }

                customer.setRentedCarId(rentedCarId);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(customers);
    }
}
