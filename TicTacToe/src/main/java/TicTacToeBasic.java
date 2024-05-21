

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 

// Basic game mode of Tic Tac Toe
public class TicTacToeBasic {
    // Create A buffer that will read the line of user Inputs
    private final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

    public void play(int size) throws IOException {
        // Create a default 3x3 board for the players to play
        Board board = new Board(size);
        // Starting player will always be X
        String player = "X";
        int maxSize = size * size;
        int turns = 0;
        board.printBoard(size);
        System.out.println("X has the first turn, Please input the row and col, in a 1-indexed grid (e.g: 1 1 )");

        while(true){
            // Read the current line of user input
            String input = getInput.readLine();
            // Split the string so that we can map the 1st element to row and 2nd element to col
            String[] inputArray = input.split(" ");
            try {
                // It will try to turn the input into Integer
                Integer.parseInt(inputArray[0]);
                Integer.parseInt(inputArray[1]);
            // If a number weren't provided it will catch the exception and tell the user to repeat
            } catch (NumberFormatException e){
                System.out.println("Invalid Input");
                continue;
            // If only 1 input were provided it will tell the user to repeat with 2 inputs
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Use 2 inputs");
                continue;
            }

            // Transform user input into 0-indexed input
            int row = Integer.parseInt(inputArray[0]) - 1;
            int col = Integer.parseInt(inputArray[1]) - 1;

            try {
                // If the spot is blank then the user can place their Symbol on there
                if (board.getEle(row, col).equals(" ")){
                    board.changeBoard(row, col, player);
                    // It will then swap the player
                    if (player.equals("X")){
                        player = "O";
                    } else {
                        player = "X";
                    }
                    // Each time the players make a valid input it will increment the turns counter by 1
                    turns += 1;
                    board.printBoard(size);
                    // It will check if there is a winner, if not it will continue
                    String winner = board.checkBoard(3);
                    if (board.checkBoard(size) != "N"){
                        System.out.print(winner + " is the winner!!!");
                        System.out.print("Congrats");
                        break;
                    }
                // If user try to pick a spot that already has a symbol then they will be reminded to pick another spot
                } else {
                    System.out.println("Try a different spot");
                    board.printBoard(size);
                }
            // remind user that they are out of the grid bound
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Input is out of bound, try again");
                continue;
            }

            // This will indicates that there is no more spot left to place, so it is a draw and ends the game
            if (turns >= maxSize){
                System.out.println("It's a draw");
                break;
            }
            System.out.println("It's " + player + " Turn" );

        }
    }


}

