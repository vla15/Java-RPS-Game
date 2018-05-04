package com.company;


public class Main {


    public static void main(String[] args) {
        //should prompt and ask if user wants to play game
        //should check
        Game game = new Game();
        game.init();

//        do {
//            try {
//                option = s.nextLine().toLowerCase();
//                if (option.isEmpty() || (!option.equals("play") && !option.equals("history") && !option.equals("quit"))) {
//                    throw new IOException();
//                } else {
//                    hasValidInput = true;
//                }
//            } catch (IOException e) {
//                System.out.println("Invalid input, please input either 'play', 'history', or 'quit'");
//                game.init();
//            }
//        } while (!hasValidInput);
//
//        if (option.equals("play")) {
//            game.startGame();
//            try {
//                option = s.nextLine().toLowerCase();
//                if (option.isEmpty()) {
//                    throw new IOException();
//                }
//            } catch {
//
//            }
//        } else if (option.equals("history")) {
//            game.viewHistory();
//        }
    }

}
