package carsharing.controller;

import carsharing.logic.CustomerCreator;
import carsharing.logic.CustomerSelector;
import carsharing.utils.Inputer;

public class MainMenuController {
    private final ManagerMenuController managerMenuController = new ManagerMenuController();
    private final CustomerSelector customerSelector = new CustomerSelector();
    private final CustomerCreator customerCreator = new CustomerCreator();

    public void execute() {
        boolean done = false;

        while (!done) {
            printMenu();
            int selection = Inputer.nextInt("Enter selection: ");

            switch (selection) {
                case 1: managerMenuController.execute(); break;
                case 2: customerSelector.execute(); break;
                case 3: customerCreator.createCustomer(); break;
                case 0: done = true; break;
                default: System.out.println("Invalid selection");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }
}
