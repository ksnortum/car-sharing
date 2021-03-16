package carsharing.logic;

import carsharing.model.Company;
import carsharing.utils.Inputer;

public class CarProcessing {
    private final CarLister carLister = new CarLister();
    private final CarCreator carCreator = new CarCreator();

    public void execute(Company company) {
        boolean done = false;

        while (!done) {
            printMenu(company);
            int selection = Inputer.nextInt("Enter selection: ");

            switch (selection) {
                case 1: carLister.listCars(company); break;
                case 2: carCreator.execute(company); break;
                case 0: done = true; break;
                default: System.out.println("Invalid selection");
            }
        }
    }

    private void printMenu(Company company) {
        System.out.println();
        System.out.printf("'%s' company:%n", company.getName());
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }
}
