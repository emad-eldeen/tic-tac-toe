package org.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe game!");
        Scanner scanner = new Scanner(System.in);
        Game game = null;
        while (game == null) {
            try {
                System.out.print("Input command:");
                game = UserInputParser.parseInputCommand(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        game.start();
    }
}