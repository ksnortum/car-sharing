package carsharing.logic;

import carsharing.model.Customer;
import carsharing.utils.Inputer;

public class CustomerProcessing {
    private final RentCarCompanySelector rentCarCompanySelector = new RentCarCompanySelector();
    private final ReturnCarProcessing returnCarProcessing = new ReturnCarProcessing();
    private final RentedCarLister rentedCarLister = new RentedCarLister();

    public void execute(Customer customer) {
        boolean done = false;

        while (!done) {
            printMenu();
            int selection = Inputer.nextInt("Enter selection: ");

            switch (selection) {
                case 1: rentCarCompanySelector.execute(customer); break;
                case 2: returnCarProcessing.execute(customer); break;
                case 3: rentedCarLister.listRenterCar(customer); break;
                case 0: done = true; break;
                default: System.out.println("Invalid selection");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }
}
