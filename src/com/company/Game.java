package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private String menuOption;
    private ArrayList<String> history = new ArrayList<String>();
    private static Scanner s = new Scanner(System.in);
    private String otherPlayerName;

    public void init() {
        String option;
        ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList("play", "history", "quit"));
        ArrayList<String> prompts = new ArrayList<String>(Arrays.asList("1. Type 'play' to play", "2. Type 'history' to view history", "Type 'quit' to exit"));
        option = this.receiveInputs(validInputs, prompts);
        if (option.equals("play")) {
            this.setupGame();
        } else if (option.equals("history")) {
            this.viewHistory();
        }
    }


    private void setupGame() {
        boolean hasValidInput = false;
        String option;
        ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList("computer", "player", "back"));
        ArrayList<String> prompts = new ArrayList<String>(Arrays.asList("Please type 'computer' to play against a computer", "Please type 'player' to play against another player", "Please type 'back' to go back to the main menu"));
        option = this.receiveInputs(validInputs, prompts);

        if (option.equals("back")) {
            this.init();
        } else {
            Player player1 = new Player("player");
            Player player2 = new Player (option);
            this.startGame(player1, player2);
        }
    }

    private void startGame(Player p1, Player p2) {
        p1.setAction("");
        p2.setAction("");
        p1.setTurn(true);
        p2.setTurn(true);

        while(p1.isTurn() || p2.isTurn()) {
            p1.generateAction();
            if (p1.getAction().equals("Quit")) {
                p2.setTurn(false);
            } else {
                p2.generateAction();
            }
        }

        if (p1.getAction().isEmpty() || p2.getAction().isEmpty() || p2.getAction().equals("Quit")) {
            this.init();
        } else {
            otherPlayerName = p2.getStatus().equals(p1.getStatus()) ? "Other Player" : "Computer";
            System.out.println(p1.getStatus() + "-action: " + p1.getStatus() + "-" + p1.getAction());
            System.out.println(otherPlayerName + "-action: " + p2.getStatus() + "-" + p2.getAction());
            this.checkWinner(p1, p2);
        }
    }

    private void checkWinner(Player p1, Player p2) {
        boolean p1Wins = false;
        boolean isTie = false;
        if (p1.getAction().equals(p2.getAction())) {
            isTie = true;
        } else {
            if (p1.getAction().equals("Rock")) {
                p1Wins = p2.getAction().equals("Scissors");
            }
            if (p1.getAction().equals("Paper")) {
                p1Wins = p2.getAction().equals("Rock");
            }
            if (p1.getAction().equals("Scissors")) {
                p1Wins = p2.getAction().equals("Paper");
            }
        }

        System.out.println("================");
        System.out.println("GAME RESULTS");
        System.out.println("================");

        if (isTie) {
            System.out.println("It's a tie!");
        } else if (p1Wins) {
            System.out.println("Player 1 Wins!");
            p1.updatePoints();
        } else {
            System.out.println(otherPlayerName + " Wins!");
            p2.updatePoints();
        }
        String victoryState = isTie ? "TIE: " : p1Wins ? "WIN: " : "LOSS: ";
        this.addToHistory(victoryState, p1, p2);
        System.out.println(p1.getStatus() + " has won: " + p1.getPoints() + " times.");
        System.out.println(p2.getStatus() + " has won: " + p2.getPoints() + " times.");
        this.startGame(p1, p2);
    }

    private void endPrompt() {
        System.out.println("Would you like to play again? Y/N");
    }

    public static void errorMessage() {
        System.out.println("================");
        System.out.println("Invalid input");
        System.out.println("================");
    }

    private void addToHistory(String victoryState, Player p1, Player p2) {
        this.history.add(victoryState + p1.getStatus() + "-" + p1.getAction() + " | " + p2.getStatus() + "-" + p2.getAction());
    }

    public static String receiveInputs(ArrayList<String> validInputs, ArrayList<String> prompts) {
        String input = "";
        boolean hasValidInput = false;
        do {
            try {
                prompts.forEach(prompt -> System.out.println(prompt));
                input = s.nextLine().toLowerCase();

                if (validInputs.contains(input)) {
                    hasValidInput = true;
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                Game.errorMessage();
            }
        } while (!hasValidInput);
        return input;
    }


    private void viewHistory() {
        this.history.forEach(h -> System.out.println(h));
        this.init();
    }
}
