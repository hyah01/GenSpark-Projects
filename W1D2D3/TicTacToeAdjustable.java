package W1D2D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TicTacToeAdjustable{
    private static final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

    TicTacToeBasic test = new TicTacToeBasic();

    static void playAdjustable() throws IOException {
        String player = "X";
        int turns = 0;
        String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
        System.out.println("Welcome to Tic Tac Toe");
        test.printBoard(3, board);
        System.out.println("X has the first turn, Please input the row and col with 0-index");

        while(true){
        
            String input = getInput.readLine();
                String[] inputArray = input.split(" ");
            try {
                Integer.parseInt(inputArray[0]);
                Integer.parseInt(inputArray[1]);

            } catch (NumberFormatException e){
                System.out.println("Invalid Input");
                continue;
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Use 2 inputs");
                continue;
            }

            int row = Integer.parseInt(inputArray[0]);
            int col = Integer.parseInt(inputArray[1]);

            try {
                if (board[row][col].equals(" ")){
                    board[row][col] = player;
                    if (player.equals("X")){
                        player = "O";
                    } else {
                        player = "X";
                    }
                    turns += 1;
                    TicTacToeBasic.printBoard(3, board);
                    String winner = TicTacToeBasic.checkBoard(3, board);
                    if (TicTacToeBasic.checkBoard(3, board) != "N"){
                        System.out.print(winner + " is the winner!!!");
                        break;
                    }
                } else {
                    System.out.println("Try a different spot");
                    TicTacToeBasic.printBoard(3, board);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Input is out of bound, try again");
                continue;
            }

            if (turns >= 9){
                System.out.println("It's a draw");
                break;
            }
            System.out.println("It's " + player + " Turn" );

        }
    }


    public static void main(String args[]) throws IOException{
        
    }
}