package W1D2D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 


public class TicTacToeBasic {
    
    private final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

    public void playBasic() throws IOException {
        Board board = new Board();
        String player = "X";
        int turns = 0;
        System.out.println("Welcome to Tic Tac Toe");
        board.printBoard(3);
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
                if (board.getEle(row, col).equals(" ")){
                    board.changeBoard(row, col, player);
                    if (player.equals("X")){
                        player = "O";
                    } else {
                        player = "X";
                    }
                    turns += 1;
                    board.printBoard(3);
                    String winner = board.checkBoard(3);
                    if (board.checkBoard(3) != "N"){
                        System.out.print(winner + " is the winner!!!");
                        break;
                    }
                } else {
                    System.out.println("Try a different spot");
                    board.printBoard(3);
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


}







// package W1D2D3;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException; 


// public class TicTacToeBasic {
    
//     private static final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));

//     public static String checkRow(int row, int size, String[][] board){
//         String total = "";
//         String xWin = "X".repeat(size);
//         String oWin = "O".repeat(size);
//         for (int i = 0; i < size; i++){
//             total+= board[row][i];
//         }
//         if (total.equals(xWin)){
//             return "X";
//         } else if (total.equals(oWin)){
//             return "O";
//         } return "N";
//     }

//     public static String checkCol(int col, int size, String[][] board){
//         String total = "";
//         String xWin = "X".repeat(size);
//         String oWin = "O".repeat(size);
//         for (int i = 0; i < size; i++){
//             total+= board[i][col];
//         }
//         if (total.equals(xWin)){
//             return "X";
//         } else if (total.equals(oWin)){
//             return "O";
//         } return "N";
//     }

//     public static String checkDia(int size, String[][] board){
//         String totalL = "";
//         String totalR = "";
//         String xWin = "X".repeat(size);
//         String oWin = "O".repeat(size);
//         for (int i = 0; i < size; i++){
//             totalR += board[i][i];
//             totalL += board[i][size-i-1];
//             }
//             if (totalR.equals(xWin)|| totalL.equals(xWin)){
//                 return "X";
//             } else if (totalR.equals(oWin)|| totalL.equals(oWin)){
//                 return "O";
//             }
//             return "N";
        
//         }
    

//     public static String checkBoard(int size, String[][] board){
//         if (checkDia(size,board).equals("X")){
//             return "X";
//         } else if (checkDia(size,board).equals("O")){
//             return "O";
//         }
//         for (int i = 0; i < size; i++){
//             if (checkRow(i, size, board).equals("X")){
//                 return "X";
//             } else if (checkRow(i, size,board).equals("O")){
//                 return "O";
//             }

//             if (checkCol(i, size,board).equals("X")){
//                 return "X";
//             } else if (checkCol(i, size,board).equals("O")){
//                 return "O";
//             }
//         }
//         return "N";
//     }
//     // Print board out
//     /*  
//         |---|---|---|---|
//         | X | X | X | O |
//         |---------------|
//         | O | X | O | X |
//         |---------------|
//         | X | O | X | X |
//         |---------------|
//         | O | O | X | O |
//         |---|---|---|---| 
//     */
//     public static void printBoard(int size ,String[][] board){
//         String buffer = "";
//         for (int i = 0; i < size; i++){
//             buffer += "|---";
//             if (i == size-1){
//                 buffer += "|";
//             }
//         } System.out.println(buffer);
//         for (int i = 0; i < size; i++){
//             buffer = "| ";
//             for (int j = 0; j < size-1; j++){
//                 buffer += board[i][j] + " | "; 
//             } buffer += board[i][size-1] + " |";
//             System.out.println(buffer);
//             if (i != size-1){
//                 System.err.println("|"+("-".repeat((size*4)-1))+"|");
//             }
//         }
//         buffer = "";
//         for (int i = 0; i < size; i++){
//             buffer += "|---";
//             if (i == size-1){
//                 buffer += "|";
//             }
//         } System.out.println(buffer);

//     }
    
//     static void playBasic() throws IOException {
//         String player = "X";
//         int turns = 0;
//         String[][] board = {{" "," "," "},{" "," "," "},{" "," "," "}};
//         System.out.println("Welcome to Tic Tac Toe");
//         printBoard(3, board);
//         System.out.println("X has the first turn, Please input the row and col with 0-index");

//         while(true){
        
//             String input = getInput.readLine();
//                 String[] inputArray = input.split(" ");
//             try {
//                 Integer.parseInt(inputArray[0]);
//                 Integer.parseInt(inputArray[1]);

//             } catch (NumberFormatException e){
//                 System.out.println("Invalid Input");
//                 continue;
//             } catch (ArrayIndexOutOfBoundsException e){
//                 System.out.println("Use 2 inputs");
//                 continue;
//             }

//             int row = Integer.parseInt(inputArray[0]);
//             int col = Integer.parseInt(inputArray[1]);

//             try {
//                 if (board[row][col].equals(" ")){
//                     board[row][col] = player;
//                     if (player.equals("X")){
//                         player = "O";
//                     } else {
//                         player = "X";
//                     }
//                     turns += 1;
//                     printBoard(3, board);
//                     String winner = checkBoard(3, board);
//                     if (checkBoard(3, board) != "N"){
//                         System.out.print(winner + " is the winner!!!");
//                         break;
//                     }
//                 } else {
//                     System.out.println("Try a different spot");
//                     printBoard(3, board);
//                 }
//             } catch (ArrayIndexOutOfBoundsException e){
//                 System.out.println("Input is out of bound, try again");
//                 continue;
//             }

//             if (turns >= 9){
//                 System.out.println("It's a draw");
//                 break;
//             }
//             System.out.println("It's " + player + " Turn" );

//         }
//     }


//     public static void main(String args[]) throws IOException{
//         playBasic();
//     }
// }
