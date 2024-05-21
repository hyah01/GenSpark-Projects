
/*
* This class works as a Util to the Tic Tac Toe game
* It consist of 2 constructor the basic 3x3 or ?x? based on the user input
* */
public class Board {
    private String [][] board;

    // Constructor to create 3x3 grid for the game
    public Board(){
        String [][] board = new String[3][3];
        // Populate the board with "space" string so that we can visualize it in printBoard()
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = " ";
            }
        }
        this.board = board;
    }
    // Constructor to create ?x? grid for the game based on the user's input
    public Board(int size){
        String [][] board = new String[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = " ";
            }
        }
        this.board = board;
    }

    // Function that allow other Java Class to modify the grid
    public void changeBoard(int row, int col, String string){
        this.board[row][col] = string;
    }

    // Function that allow other Java Class to get the element at the location of row and col
    public String getEle(int row, int col){
        return this.board[row][col];
    }

    // checkRow, checkCol, and checkDia are Helper functions that helps determine if that row,col, or diagonal has a winning player
    public String checkRow(int row, int size){
        String total = "";
        // Pre initialized the winning string to compare
        String xWin = "X".repeat(size);
        String oWin = "O".repeat(size);
        for (int i = 0; i < size; i++){
            total += this.board[row][i];
        }
        // If the row contain the winning string it will return the winning player
        if (total.equals(xWin)){
            return "X";
        } else if (total.equals(oWin)){
            return "O";
        // If there is no winning player it will return "N" to indicate NULL ( No winning player )
        } return "N";
    }

    public String checkCol(int col, int size){
        String total = "";
        String xWin = "X".repeat(size);
        String oWin = "O".repeat(size);
        for (int i = 0; i < size; i++){
            total+= this.board[i][col];
        }
        // If the col contain the winning string it will return the winning player
        if (total.equals(xWin)){
            return "X";
        } else if (total.equals(oWin)){
            return "O";
        } return "N";
    }

    public String checkDia(int size){
        String totalL = "";
        String totalR = "";
        String xWin = "X".repeat(size);
        String oWin = "O".repeat(size);
        for (int i = 0; i < size; i++){
            totalR += this.board[i][i];
            totalL += this.board[i][size-i-1];
            }
            // Checks if the diagonal grid contains the winning string and return the winner
            if (totalR.equals(xWin)|| totalL.equals(xWin)){
                return "X";
            } else if (totalR.equals(oWin)|| totalL.equals(oWin)){
                return "O";
            }
            return "N";
        
        }
    
    // checkBoard utilizes all the helper methods to check if there is a winner
    public String checkBoard(int size){
        if (this.checkDia(size).equals("X")){
            return "X";
        } else if (checkDia(size).equals("O")){
            return "O";
        }
        for (int i = 0; i < size; i++){
            if (this.checkRow(i, size).equals("X")){
                return "X";
            } else if (checkRow(i, size).equals("O")){
                return "O";
            }

            if (this.checkCol(i, size).equals("X")){
                return "X";
            } else if (this.checkCol(i, size).equals("O")){
                return "O";
            }
        }
        // If no winner it returns "N"
        return "N";
    }
    // What the Board will look like when the player plays the game
    /*  
        |---|---|---|---|
        | X | X | X | O |
        |---------------|
        | O | X | O | X |
        |---------------|
        | X | O | X | X |
        |---------------|
        | O | O | X | O |
        |---|---|---|---| 
    */
    // Prints the board to look like the one on top ^
    public void printBoard(int size ){
        String buffer = "";
        for (int i = 0; i < size; i++){
            buffer += "|---";
            if (i == size-1){
                buffer += "|";
            }
        } System.out.println(buffer);
        for (int i = 0; i < size; i++){
            buffer = "| ";
            for (int j = 0; j < size-1; j++){
                buffer += this.board[i][j] + " | "; 
            } buffer += this.board[i][size-1] + " |";
            System.out.println(buffer);
            if (i < size-1){
                System.out.println("|"+("-".repeat((size*4)-1))+"|");
            }
        }
        buffer = "";
        for (int i = 0; i < size; i++){
            buffer += "|---";
            if (i == size-1){
                buffer += "|";
            }
        } System.out.println(buffer);

    }
}
