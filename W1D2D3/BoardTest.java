package W1D2D3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testCheckCol() {
        Board test = new Board();
        assertEquals("N",test.checkRow(0, 3));
    }
}
