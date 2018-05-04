package com.company;

public class Actions {

    public static String generateMove(int num) {
        String move;
        switch (num) {
            case 0:
                move = "Rock";
                break;
            case 1:
                move = "Paper";
                break;
            case 2:
                move = "Scissors";
                break;
            default:
                move = null;
        }
        return move;
    }
}
