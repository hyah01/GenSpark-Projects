
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
public class TicTacToeAI {

    private final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

    void AI(int size, int difficulty, Board board){
        Random rand = new Random();
        switch(difficulty){
            case 1:
                // Runs the basic random AI
                while (true){
                    int row = rand.nextInt(size);
                    int col = rand.nextInt(size);
                    if (board.getEle(row, col).equals(" ")){
                        board.changeBoard(row,col,"O");
                        break;
                    }
                }
                break;
            case 2:
                // This check the next "best" possible move
                String next = board.checkBoardAI(size);
                // Once it received a return it will then place an O based on checkBoardAI return
                if (next.equals("DR")){
                    while(true){
                        int RC = rand.nextInt(size);
                        if (board.getEle(RC, RC).equals(" ")){
                            board.changeBoard(RC,RC,"O");
                            break;
                        }
                    }
                } else if (next.equals("DL")){
                    while(true){
                        int row = rand.nextInt(size);
                        int col = size-row-1;
                        if (board.getEle(row, col).equals(" ")){
                            board.changeBoard(row,col,"O");
                            break;
                        }
                    }
                } else if (next.split(" ")[0].equals("R")){
                    int row = Integer.parseInt(next.split(" ")[1]);
                    while(true){
                        int col = rand.nextInt(size);
                        if (board.getEle(row, col).equals(" ")){
                            board.changeBoard(row,col,"O");
                            break;
                        }
                    }
                } else{
                    int col = Integer.parseInt(next.split(" ")[1]);
                    while(true){
                        int row = rand.nextInt(size);
                        if (board.getEle(row, col).equals(" ")){
                            board.changeBoard(row,col,"O");
                            break;
                        }
                    }
                }
                break;
        }
    }
    public void playAI(int size, int difficulty) throws IOException, InterruptedException {
        Board board = new Board(size);
        // Starting player will always be X
        boolean player = true;
        int maxSize = size * size;
        int turns = 0;

        board.printBoard(size);
        System.out.println("You Has The First Turn, Please Input The Row and Col, In A 1-indexed Grid (e.g: 1 1 )");

        while(true){
            if (player){
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
                    System.out.println("Use 2 Inputs");
                    continue;
                }

                // Transform user input into 0-indexed input
                int row = Integer.parseInt(inputArray[0]) - 1;
                int col = Integer.parseInt(inputArray[1]) - 1;

                try {
                    // If the spot is blank then the user can place their Symbol on there
                    if (board.getEle(row, col).equals(" ")){
                        board.changeBoard(row, col, "X");
                        // Each time the players make a valid input it will increment the turns counter by 1
                        turns += 1;
                        board.printBoard(size);
                        // It will check if there is a winner, if not it will continue
                        if (board.CheckWinner(size).equals("X") ){
                            System.out.println("You Are The Winner!!!");
                            System.out.println("Winner Winner Chicken Dinner");
                            break;
                        }

                        // If user try to pick a spot that already has a symbol then they will be reminded to pick another spot
                    } else {
                        System.out.println("Try A Different Spot");
                        board.printBoard(size);
                        continue;
                    }
                    // remind user that they are out of the grid bound
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Input Is Out Of Bound, Try Again");
                    continue;
                }

                // This indicates that there is no more spot left to place, so it is a draw and ends the game
                if (turns >= maxSize){
                    System.out.println("It's A Draw");
                    break;
                }
                System.out.println("It's The AI's Turn" );
                player = false;

            } else {
                // So that the AI doesn't response too fast
                Thread.sleep(1000);
                // It will run the AI based on the difficulty
                AI(size,difficulty,board);
                turns += 1;
                board.printBoard(size);
                if (board.CheckWinner(size).equals("O") ){
                    System.out.println("Sorry You Lost :((, But The AI Won Tho, WOOOOO!");
                    System.out.println("Skill Issue?");
                    break;
                }
                System.out.println("Your Turn");
                player = true;
            }

        }
    }
}
