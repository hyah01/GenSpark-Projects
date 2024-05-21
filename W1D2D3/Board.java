package W1D2D3;

public class Board {
    private String [][] board;
    public Board(){
        String [][] board = new String[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; i < 3; i++){
                board[i][j] = " ";
            }
        }
        this.board = board;
    }
    public Board(int size){
        String [][] board = new String[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; i < size; i++){
                board[i][j] = " ";
            }
        }
        this.board = board;
    }

    public void changeBoard(int row, int col, String string){
        this.board[row][col] = string;
    }

    public String getEle(int row, int col){
        return this.board[row][col];
    }

    public String checkRow(int row, int size){
        String total = "";
        String xWin = "X".repeat(size);
        String oWin = "O".repeat(size);
        for (int i = 0; i < size; i++){
            total+= this.board[row][i];
        }
        if (total.equals(xWin)){
            return "X";
        } else if (total.equals(oWin)){
            return "O";
        } return "N";
    }

    public String checkCol(int col, int size){
        String total = "";
        String xWin = "X".repeat(size);
        String oWin = "O".repeat(size);
        for (int i = 0; i < size; i++){
            total+= this.board[i][col];
        }
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
            if (totalR.equals(xWin)|| totalL.equals(xWin)){
                return "X";
            } else if (totalR.equals(oWin)|| totalL.equals(oWin)){
                return "O";
            }
            return "N";
        
        }
    

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
        return "N";
    }
    // Print board out
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
            if (i != size-1){
                System.err.println("|"+("-".repeat((size*4)-1))+"|");
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
