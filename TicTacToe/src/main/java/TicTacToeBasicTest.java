import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeBasicTest {
    @Test
    public void testCheckRow() {
        Board board = new Board();
        assertEquals("X",board.checkRow(0, 3));

    }

    @Test
    public void testCheckDia() {

    }

    @Test
    public void testCheckBoard() {

    }

    
}
