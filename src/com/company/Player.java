package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private boolean isTurn;
    private boolean isPlayer;
    private String status;
    private String action;
    private int points = 0;

    public Player(String playerState) {
        if (playerState.equals("computer")) {
            this.isPlayer = false;
        } else {
            this.isPlayer = true;
        }
        status = Character.toUpperCase(playerState.charAt(0)) + playerState.substring(1);
    }

    public void generateAction() {
        if (!isPlayer) {
            Random random = new Random();
            int actionNumber = random.nextInt(3);
            this.action = Actions.generateMove(actionNumber);
        } else {
            String option;
            ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList("rock", "paper", "scissors", "quit"));
            ArrayList<String> prompts = new ArrayList<String>(Arrays.asList("Type in 'rock' 'paper' or 'scissors'", "Or type 'quit' to quit playing"));
            option = Game.receiveInputs(validInputs, prompts);
            this.action = Character.toUpperCase((option.charAt(0))) + option.substring(1);
        }
        this.isTurn = false;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public String getAction() {
        return action;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public String getStatus() {
        return status;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void updatePoints() {
        this.points++;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public int getPoints() {
        return points;
    }
}
