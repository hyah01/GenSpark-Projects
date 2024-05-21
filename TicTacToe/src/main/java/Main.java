
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private final static BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String args[]) throws IOException, InterruptedException {
        String input;
        TicTacToeBasic game = new TicTacToeBasic();
        TicTacToeAdjustable gameA = new TicTacToeAdjustable();

        while (true){
            System.out.println("Welcome to Tic Tac Toe");
            Thread.sleep(1000);
            System.out.println("Choose the game mode you want");
            Thread.sleep(500);
            System.out.println("( 1 ): Classic\n( 2 ): ?x? Grid Mode");
            input = getInput.readLine();
            switch(input){
                case "1":
                    game.play(3);
                    break;
                case "2":
                    gameA.playAdjust();
                    break;
            }

            System.out.println("Do you want to play again?");
            input = getInput.readLine();
            if (input.equals("Y") || input.equals("y")){
                continue;
            } else {
                System.out.println("Thanks for playing :)");
                break;
            }
        }

    }
}
