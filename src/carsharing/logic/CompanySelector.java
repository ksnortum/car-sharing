package carsharing.logic;

import carsharing.model.Company;
import carsharing.utils.Inputer;

import java.util.List;

public class CompanySelector {
    private final CompanyLister companyLister = new CompanyLister();
    private final CarProcessing carProcessing = new CarProcessing();

    public void execute() {
        List<Company> companies = companyLister.printCompanies();

        if (companies.isEmpty()) {
            return;
        }

        int selection = Inputer.nextInt("Enter your selection: ");

        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= companies.size()) {
            carProcessing.execute(companies.get(selection - 1));
        } else {
            System.out.println("Selection invalid");
        }
    }
}
