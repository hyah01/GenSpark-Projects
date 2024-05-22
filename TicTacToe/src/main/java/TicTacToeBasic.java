

import java.io.IOException;

// Basic game mode of Tic Tac Toe
public class TicTacToeBasic {
    public void play(int size, int difficulty) throws IOException, InterruptedException {
        // Create a default 3x3 board for the players to play
        Board board = new Board(size);
        // Starting player will always be X
        String player = "X";
        int maxSize = size * size;
        int turns = 0;

        if (difficulty == 1){
            System.out.println("Each players will have 10 seconds to make a move before they lose");
            Thread.sleep(3000);
        }

        board.printBoard(size);
        System.out.println("X Has The First Turn, Please Input The Row and Col, In A 1-indexed Grid (e.g: 1 1 )");

        while(true){
            // Read the current line of user input

            String input = (difficulty == 0) ? gameTimer.getInput(200000) : gameTimer.getInput(10000);
            if (input.equals("invalid")){
                System.out.println("Congrats To "+ ((player.equals("X")) ? "O" : "X") +" You Won!\n");
                break;
            }
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
                System.out.println("Use 2 Inputs");
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
                    String winner = board.CheckWinner(size);
                    if (board.CheckWinner(size).equals("N")){
                        System.out.print(winner + " Is The Winner!!!");
                        System.out.print("Winner Winner Chicken Dinner");
                        break;
                    }
                // If user try to pick a spot that already has a symbol then they will be reminded to pick another spot
                } else {
                    System.out.println("Try A Different Spot");
                    board.printBoard(size);
                }
            // remind user that they are out of the grid bound
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Input Is Out Of Bound, Try Again");
                continue;
            }

            // This will indicates that there is no more spot left to place, so it is a draw and ends the game
            if (turns >= maxSize){
                System.out.println("It's A Draw");
                break;
            }
            System.out.println(STR."It's \{player}'s Turn" );

        }
    }


}

