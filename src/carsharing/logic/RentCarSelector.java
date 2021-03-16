package carsharing.logic;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.utils.Inputer;

import java.util.List;

public class RentCarSelector {
    private final RentCarLister rentCarLister = new RentCarLister();
    private final RentCarProcessing rentCarProcessing = new RentCarProcessing();

    public void execute(Company company, Customer customer) {
        List<Car> cars = rentCarLister.printCars(company);

        if (cars.isEmpty()) {
            return;
        }

        int selection = Inputer.nextInt("Enter your selection: ");

        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= cars.size()) {
            rentCarProcessing.execute(cars.get(selection - 1), customer);
        } else {
            System.out.println("Selection invalid");
        }
    }
}
