package Wordle;

import java.util.Scanner;

public class Main {

    // ANSI color codes for displaying colored text in the console
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display the game menu
            System.out.println("\nWordle Game Menu");
            System.out.println("1. Play Game");
            System.out.println("2. Instructions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Game game = new Game();
                    game.start();
                    break;
                case "2":
                    printInstructions();
                    break;
                case "3":
                    exit = true;
                    System.out.println("Thank you for playing. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }
        scanner.close();
    }

    public static void printInstructions() {
        System.out.println("Welcome to Wordle!");
        System.out.println("You have 5 attempts to guess a 5-letter word.");
        System.out.println("After each guess, you will receive feedback:");
        System.out.println(ANSI_GREEN + "Green" + ANSI_RESET + " for correct letters in the correct position,");
        System.out.println(ANSI_YELLOW + "Yellow" + ANSI_RESET + " for correct letters in the wrong position,");
        System.out.println(ANSI_RED + "Red" + ANSI_RESET + " for incorrect letters.");
        System.out.println("Let's start!");
    }

}

