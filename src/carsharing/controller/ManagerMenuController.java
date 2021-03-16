package carsharing.controller;

import carsharing.logic.CompanyCreator;
import carsharing.logic.CompanySelector;
import carsharing.utils.Inputer;

public class ManagerMenuController {
    private final CompanySelector companySelector = new CompanySelector();
    private final CompanyCreator companyCreator = new CompanyCreator();

    public void execute() {
        boolean done = false;

        while (!done) {
            printMenu();
            int selection = Inputer.nextInt("Enter selection: ");

            switch (selection) {
                case 1: companySelector.execute(); break;
                case 2: companyCreator.execute(); break;
                case 0: done = true; break;
                default: System.out.println("Invalid selection");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }
}
