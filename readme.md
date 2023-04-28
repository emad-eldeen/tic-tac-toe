## Tic-Tac-Toe
Implementation of [Tic-Tac-Toe](https://en.wikipedia.org/wiki/Tic-tac-toe) game. 

### Technical Requirements
- Create the game field 
- Make it possible to select the two players; AI or user player.
- For AI players, it should be possible to select the difficulty of the AI `easy` or `medium`
- `Easy` AI makes random moves.
- `Medium` AI makes moves as follows:
  - If it already has two in a row and can win with one further move, it does so. 
  - If its opponent can win with one move, it plays the move necessary to block this. 
  - Otherwise, it makes a random move.
- Handle any wrong user inputs

### How To Use
- start Main.main() 
- enter a command. available commands: 
  - `start <X player> <O player>` available player options: `user` for human player, and `easy` `medium` for AI player
  - `exit` to end the app
- in case there is a `user` player, input the next moves coordinates until one player wins.
- if both players are AI, get some popcorn and enjoy the battle. 

### Knowledge Used
- Java streams
- Java arrays
- Java collections
- Lambda functions
- Functional interface
- Enums
- Random numbers
- Checked Exceptions
- Exceptions handling
- User input parsing
- Coding style conventions