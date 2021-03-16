package carsharing.utils;

import java.util.Scanner;

public class Inputer {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String nextString(String prompt, boolean useLineSeparator) {
        if (!prompt.isBlank()) {
            if (useLineSeparator) {
                prompt = String.format("%s%n", prompt);
            }

            System.out.print(prompt);
        }

        return SCANNER.nextLine();
    }

    public static String nextString(String prompt) {
        return nextString(prompt, false);
    }

    public static boolean nextYesNo(String prompt) {
        String response = nextString(prompt);
        boolean isYes = true;
        boolean done = false;

        while (!done) {
            if (response.matches("(?i)(y|yes)")) {
                done = true;
            } else if (response.matches("(?i)(n|no)")) {
                isYes = false;
                done = true;
            } else {
                response = nextString(prompt);
            }
        }

        return isYes;
    }

    public static int nextInt(String prompt) {
        if (!prompt.isBlank()) {
            System.out.print(prompt);
        }

        while (!SCANNER.hasNextInt()) {
            SCANNER.next();

            if (!prompt.isBlank()) {
                System.out.print(prompt);
            }
        }

        int response = SCANNER.nextInt();
        SCANNER.nextLine();

        return response;
    }
}
