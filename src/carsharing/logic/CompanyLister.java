package carsharing.logic;

import carsharing.model.Company;
import carsharing.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyLister {
    private static final String COMPANY_LIST_SQL =
            "SELECT id, name FROM company";

    public List<Company> printCompanies() {
        Optional<List<Company>> optionalCompanies = getCompanies();
        List<Company> companies = new ArrayList<>();

        if (optionalCompanies.isPresent()) {
            System.out.println();
            companies = optionalCompanies.get();

            if (companies.isEmpty()) {
                System.out.println("The company list is empty!");
            } else {
                System.out.println("Company list:");

                for (int i = 0; i < companies.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, companies.get(i).getName());
                }

                System.out.println("0. Back");
            }
        }

        return companies;
    }

    private Optional<List<Company>> getCompanies() {
        Connection conn = DatabaseConnection.getConnection();
        List<Company> companies = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(COMPANY_LIST_SQL)) {

            while(resultSet.next()) {
                Company company = new Company(resultSet.getLong(1), resultSet.getString(2));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(companies);
    }
}
