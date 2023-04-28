package org.tictactoe;

public class UserInputParser {

    /**
     * parse the input coordinates
     * @param userInput user input string
     * @return the parsed coordinates as int array
     * @throws IllegalArgumentException in case the input is not two numbers
     */

    public static int[] parseCoordinates(String userInput) throws IllegalArgumentException {
        String[] userInputArr = userInput.split(" ");
        if (userInputArr.length != 2) {
            throw new IllegalArgumentException("You should enter two one-digit numbers!");
        }
        try {
            int[] parsedCoordinates = new int[2];
            // subtracting one since game field starts from zero not one
            parsedCoordinates[0] = Integer.parseInt(userInputArr[0]) - 1;
            parsedCoordinates[1] = Integer.parseInt(userInputArr[1]) - 1;
            return parsedCoordinates;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("You should enter numbers!");
        }
    }

    /**
     * parses the user input command
     * @param userInput user input as string
     * @return a Game object in case the command is start game command
     */
    public static Game parseInputCommand(String userInput) {
        String[] userInputArr = userInput.split(" ");
        if (userInputArr.length == 1) {
            if ("exit".equalsIgnoreCase(userInputArr[0])) {
                System.exit(0);
            }
        } else if(userInputArr.length == 3) {
            if ("start".equalsIgnoreCase(userInputArr[0])) {
                Player playerOne = parsePlayerType(userInputArr[1], 1);
                Player playerTwo = parsePlayerType(userInputArr[2], 2);
                return new Game(playerOne, playerTwo);
            }
        }
        throw new IllegalArgumentException("Bad parameters!");
    }

    /**
     * parses the input player type
     * @param string player in string
     * @param playerIndex whether it is the first or the second
     * @return Player object
     */
    private static Player parsePlayerType(String string, int playerIndex) {
        char playerChar = playerIndex == 1 ? Game.PLAYER_ONE_CHAR : Game.PLAYER_TWO_CHAR;
        char otherPlayerChar = playerIndex == 1 ? Game.PLAYER_TWO_CHAR : Game.PLAYER_ONE_CHAR;
        if ("user".equalsIgnoreCase(string)) {
            return new UserPlayer(playerChar);
        } else if ("easy".equalsIgnoreCase(string)) {
            return new AIPlayer(playerChar, otherPlayerChar, AIPlayer.Level.Easy);
        } else if ("medium".equalsIgnoreCase(string)) {
            return new AIPlayer(playerChar, otherPlayerChar, AIPlayer.Level.Medium);
        }
        throw new IllegalArgumentException("Bad parameters!");
    }
}
