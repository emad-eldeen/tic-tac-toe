package org.tictactoe;

public class Game {
    // 3 x 3
    public final static int FIELD_SIZE = 3;
    public final static char PLAYER_ONE_CHAR = 'X';
    public final static char PLAYER_TWO_CHAR = 'O';

    final Field field;
    private boolean finished;
    private STATE state = STATE.NOT_FINISHED;
    private final Player playerOne;
    private final Player playerTwo;
    private final WinChecker checker = new WinChecker();

    enum STATE {
        X_WINS("X wins"),
        O_WINS("O wins"),
        DRAW("Draw"),
        NOT_FINISHED("Game not finished");
        final String message;
        STATE(String message) {
            this.message = message;
        }
    }

    Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.field = new Field(FIELD_SIZE);
    }

    // play next move
    public void setMove(int rowIndex, int colIndex, char playerChar) {
        if (MoveValidator.moveIsValid(rowIndex, colIndex, field, FIELD_SIZE)) {
            field.setCell(rowIndex, colIndex, playerChar);
            setGameState();
            printField();
        }
    }
    public void start() {
        printField();
        // until the game is changed, each player makes a move
        while (!finished) {
            playerOne.makeAMove(this);
            if (!finished) {
                playerTwo.makeAMove(this);
            }
        }
        printState();
    }
    private void setGameState() {
        if (checker.thereIsAWinner(field)) {
            char winner = checker.getWinner();
            if (winner == PLAYER_ONE_CHAR) {
                state = STATE.X_WINS;
            } else if (winner == PLAYER_TWO_CHAR) {
                state = STATE.O_WINS;
            }
            finished = true;
        } else if (isDraw()) {
            state = STATE.DRAW;
            finished = true;
        }
    }

    // whether the field is full
    private boolean isDraw() {
        return field.getFlat().stream()
                .noneMatch(x -> x == Field.EMPTY_CHAR);
    }
    public void printState() {
        System.out.println(state.message);
    }
    public void printField() {
        field.print();
    }
}
