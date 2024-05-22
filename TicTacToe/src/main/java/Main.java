
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private final static BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String args[]) throws IOException, InterruptedException, NumberFormatException {
        String input;
        TicTacToeAdjustable gameA = new TicTacToeAdjustable();

        while (true){
            System.out.println("Welcome to Tic Tac Toe");
            Thread.sleep(1000);
            System.out.println("Choose the game mode you want");
            Thread.sleep(500);
            System.out.println("( 1 ): Classic\n" +
                               "( 2 ): ?x? Grid Mode\n" +
                               "( 3 ): Time Mode\n" +
                               "( 4 ): AI Mode\n");
            input = getInput.readLine();
            switch(input){
                case "1":
                    TicTacToeBasic game = new TicTacToeBasic();
                    game.play(3,0);
                    break;
                case "2":
                    gameA.playAdjust(0);
                    break;
                case "3":
                    gameA.playAdjust(1);
                    break;
                case "4":
                    TicTacToeAI gameB = new TicTacToeAI();
                    System.out.println("You Choose To Play Against An AI Opponent, Choose It's Difficulty");
                    System.out.println("( 1 ): Easy: The AI makes random moves.\n" +
                                       "( 2 ): Medium: The AI uses a basic strategy to block the player's winning moves and make its own winning moves.\n");
                    int difficulty;
                    while (true){
                        input = getInput.readLine();
                        try{
                            Integer.parseInt(input);
                            difficulty = Integer.parseInt(input);
                            if (difficulty < 1 || difficulty > 3){
                                System.out.println("Invalid Input, Try a 1-2");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input, Try a 1-2");
                        }

                    }

                    System.out.println("You Choose To Play Against A Medium AI Opponent, Choose the Grid Size");
                    int size;
                    while (true){
                        input = getInput.readLine();
                        try{
                            Integer.parseInt(input);
                            size = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input, Try Using A Number");
                        }

                    }



                    gameB.playAI(size,difficulty);
            }

            System.out.println("Do you want to play again?");
            input = getInput.readLine();
            if (!(input.equals("Y") || input.equals("y"))){
                System.out.println("Thanks for playing :)");
                break;
            }
        }

    }
}
