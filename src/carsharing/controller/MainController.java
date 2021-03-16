package carsharing.controller;

import carsharing.logic.CommandLineArgs;
import carsharing.persistence.DatabaseConnection;
import carsharing.persistence.TableBuilder;

import java.sql.Connection;

public class MainController {
    private static final MainMenuController mainMenuController = new MainMenuController();

    public static void execute(String[] args) {
        String databaseName = CommandLineArgs.parseArgs(args);
        Connection conn = DatabaseConnection.getConnection(databaseName);
        TableBuilder.buildTable(conn);
        mainMenuController.execute();
        DatabaseConnection.closeConnection();
    }
}
