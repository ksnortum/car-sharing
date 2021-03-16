package carsharing.logic;

import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.utils.Inputer;

import java.util.List;

public class RentCarCompanySelector {
    private final CompanyLister companyLister = new CompanyLister();
    private final RentCarSelector rentCarSelector = new RentCarSelector();

    public void execute(Customer customer) {
        System.out.println();

        if (customer.getRentedCarId() != null) {
            System.out.println("You've already rented a car!");
            return;
        }

        List<Company> companies = companyLister.printCompanies();

        if (companies.isEmpty()) {
            return;
        }

        int selection = Inputer.nextInt("Enter your selection: ");

        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= companies.size()) {
            rentCarSelector.execute(companies.get(selection - 1), customer);
        } else {
            System.out.println("Selection invalid");
        }
    }
}
