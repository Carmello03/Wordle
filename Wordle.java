package Wordle;

import java.util.Arrays;

public class Wordle {
    private String[] userGuess;
    private String[] userWord;
    private final String[] correctWord;
    private String[] userGuessWrongPos;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Wordle(String[] userWord, String[] correctWord) {
        this.userWord = userWord;
        this.correctWord = correctWord;
        this.userGuess = new String[5]; //words of user that are correct
        this.userGuessWrongPos = new String[5]; //words of user that are correct but in wrong position

    }

    public void word_validator() {
        for(int i = 0; i < userWord.length; i++) {
            String[] tempArray = Arrays.copyOf(correctWord, correctWord.length);

                if(userWord[i].equals(tempArray[i])) {
                    userGuess[i] = tempArray[i];
                    tempArray[i] = null;
                    continue;
                }
                for(int j = 0; j < tempArray.length; j++) {
                    if(userWord[i].equals(tempArray[j]) && !userWord[i].equals(userGuess[j])) {
                        userGuessWrongPos[i] = userWord[i];
                        tempArray[j] = null;
                    }
                }
        } 
    }

    public String evaluateGuess() {
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < userGuess.length; i++) {
            if (userGuess[i] != null) {
                feedback.append(ANSI_GREEN + userGuess[i] + ANSI_RESET + " ");
            } else if (userGuess[i] == null && userGuessWrongPos[i] == null) {
                feedback.append(ANSI_RED + userWord[i] + ANSI_RESET + " ");
            } else {
                feedback.append(ANSI_YELLOW + userGuessWrongPos[i] + ANSI_RESET + " ");
            }
        }
        return feedback.toString();
    }

    // getters
    public String[] getUserGuess() {
        return userGuess;
    }

    public String[] getUserWord() {
        return userWord;
    }

    public String[] getCorrectWord() {
        return correctWord;
    }
    public String[] getUserGuessWrongPos() {
        return userGuessWrongPos;
    }

}