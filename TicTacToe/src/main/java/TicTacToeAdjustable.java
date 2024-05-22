

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Adjustable Grid Game mode of Tic Tac Toe
public class TicTacToeAdjustable {
    // Create A buffer that will read the line of user Inputs
    private final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

    public void playAdjust(int difficulty) throws IOException, InterruptedException {
        System.out.println("Welcome to Tic Tac Toe Adjustable Mode, please pick your grid size X ");
        String input;
        int size;
        while(true){
            // collect the size that the user want the tic tac toe to be
            input = getInput.readLine();
            try {
                // Check if the input was a valid integer
                Integer.parseInt(input);
                size = Integer.parseInt(input);
                break;
            // Warns the user if they anything but 1 single number
            } catch (NumberFormatException e){
                System.out.println("Try 1 number only");
            }
        }
        System.out.println(STR."You picked size \{size} here is a \{size}x\{size} grid");

        TicTacToeBasic game = new TicTacToeBasic();
        game.play(size,difficulty);

    }


}

