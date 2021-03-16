package carsharing.logic;

public class CommandLineArgs {
    public static final String DEFAULT_DB_FILENAME = "carsharing";

    public static String parseArgs(String[] args) {
        if (args.length == 0) {
            return DEFAULT_DB_FILENAME;
        }

        if (args.length != 2) {
            System.out.println("Bad number of arguments");
            return DEFAULT_DB_FILENAME;
        }

        if (!"-databaseFileName".equals(args[0])) {
            System.out.println("Bad argument");
            return DEFAULT_DB_FILENAME;
        }

        return args[1];
    }
}
