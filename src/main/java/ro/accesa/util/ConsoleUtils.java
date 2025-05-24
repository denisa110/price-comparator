package ro.accesa.util;

import java.util.Scanner;

public class ConsoleUtils {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    private ConsoleUtils() {
    }

    public static void showMenu() {
        System.out.println();
        System.out.println(BLUE + "╔════════════════════════════════════╗" + RESET);
        System.out.println(BLUE + "║       " + GREEN + "PRICE COMPARATOR" + BLUE + "             ║" + RESET);
        System.out.println(BLUE + "╚════════════════════════════════════╝" + RESET);
        System.out.println();

        System.out.println("Choose an option:");
        System.out.println(YELLOW + "1." + RESET + " Best Discounts");
        System.out.println(YELLOW + "2." + RESET + " New Discounts");
        System.out.println(YELLOW + "3." + RESET + " Create Price Alert");
        System.out.println(YELLOW + "4." + RESET + " View Price Alert");
        System.out.println(YELLOW + "5." + RESET + " Product Substitutes & Recommendations");
        System.out.println(RED + "0." + RESET + " Exit Application");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    public static int getChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printError("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public static void waitForEnter(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

}
