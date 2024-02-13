package Wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {
    private Wordle wordle;
    private final Scanner gameScanner;
    private final String[] correctWord = generator(); //win word

    public Game() {
        this.gameScanner = new Scanner(System.in);
    }

    public void start() {

        String[] userWord = new String[5]; //current word guessed by user

        int counter = 1;
    
        System.out.println();
        System.out.print("Wordle ================> ");
        System.out.println("- - - - -");
    
        while(true) {
            try {
                String userInput = validateAndRetrieveInput(gameScanner);
    
                // Split user input into an array
                userWord = userInput.split("");
        
                // Check if user word matches the winning word
                wordle = new Wordle(userWord, correctWord);
                wordle.word_validator();
        
                userWord = wordle.getUserWord();
                String [] userGuess = wordle.getUserGuess();
        
                // Display user guesses with appropriate colors
                System.out.print("Wordle ================> ");
                System.out.println(wordle.evaluateGuess());
        
                System.out.println();
        
                // Check for game completion
                if(word_finish(userGuess)) {
                    System.out.println("Congratulations! The correct word is: " + print_guess(correctWord));
                    break;
                } 

                System.out.println();
                if(counter++ == 6) {
                    System.out.println("You have run out of attempts the correct word was: " + print_guess(correctWord));
                    break;
                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); 
                break;
            }
        
        } 

    }

    public String validateAndRetrieveInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter a 5 letter word => ");
            String userInput = scanner.nextLine().toLowerCase();
            
            if (userInput.length() != 5) {
                System.out.println("The word must be exactly 5 letters long.");
            } else if (userInput.matches(".*[^a-z].*")) {
                System.out.println("Please use letters only.");
            } else {
                return userInput.toLowerCase();
            }
        }
    }

    private String[] generator() {
        String filePath = "Wordle\\words.txt";
        int linesCount = 0;
        String selectedWord = null;
        
        try {
            // Line count
            linesCount = (int) Files.lines(Paths.get(filePath)).count();

            if (linesCount == 0) {
                System.err.println("The word list is empty.");
                return null;
            }

            // Find randome line
            Random random = new Random();
            int randomLine = random.nextInt(linesCount);

            // Retrieve Selected word
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                selectedWord = stream.skip(randomLine).findFirst().orElse(null);
            }

        } catch (IOException e) {
            System.err.println("Failed to read words from file: " + e.getMessage());
            return null;
        }

        if (selectedWord == null) {
            System.err.println("Failed to select a word.");
            return null;
        }

        return selectedWord.split("");
    }

    private boolean word_finish(String[] actual_guess) {
        for(String letters : actual_guess) {
            if(letters == null) {
                return false;
            }
        }
        return true;
    }

    private String print_guess(String[] correctWord) {
        String word = "";
        for(int i = 0; i <= correctWord.length - 1; i++) word += correctWord[i];
        return word;
    }

}