package W1D2D3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicTacToeBasicTest {
    @Test
    public void testCheckRow() {
        String[][] board = {{"X","X","X"},{"O","O","1"},{"O","2","3"}};
        assertEquals("X",W1D2D3.TicTacToeBasic.checkRow(0, 3, board));
        assertEquals("N",W1D2D3.TicTacToeBasic.checkRow(2, 3, board));
    }

    @Test
    public void testCheckDia() {
        String[][] board = {{"X","X","X"},{"O","X","O"},{"X","O","O"}};
        assertEquals("X",W1D2D3.TicTacToeBasic.checkDia( 3, board));
        String[][] board2 = {{"X","X","X","O"},{"O","X","O","X"},{"X","O","X","X"},{"O","O","X","O"}};
        assertEquals("O",W1D2D3.TicTacToeBasic.checkDia( 4, board2));
    }

    @Test
    public void testCheckBoard() {
        String[][] board = {{"X","X","X","O"},{"O","X","O","X"},{"X","O","X","X"},{"O","O","X","O"}};
        assertEquals("O",W1D2D3.TicTacToeBasic.checkBoard( 4, board));
        String[][] board2 = {{"X","X","X","O"},{"O","X","O","X"},{"X","O","X","X"},{"1","O","X","O"}};
        assertEquals("N",W1D2D3.TicTacToeBasic.checkBoard( 4, board2));
    }

    
}
