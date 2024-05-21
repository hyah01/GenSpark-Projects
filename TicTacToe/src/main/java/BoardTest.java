

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testCheckCol() {
        Board test = new Board();
        assertEquals("N",test.checkCol(0, 3));

        test.changeBoard(0,0,"X");
        test.changeBoard(1,0,"X");
        test.changeBoard(2,0,"X");
        assertEquals("X",test.checkCol(0, 3));

        test.changeBoard(0,1,"O");
        test.changeBoard(1,1,"O");
        test.changeBoard(2,1,"O");
        assertEquals("O",test.checkCol(1, 3));

        test.changeBoard(2,1,"X");
        assertEquals("N",test.checkCol(1, 3));

    }

    @Test
    public void testCheckRow() {
        Board test = new Board();
        assertEquals("N",test.checkRow(0, 3));

        test.changeBoard(0,0,"X");
        test.changeBoard(0,1,"X");
        test.changeBoard(0,2,"X");
        assertEquals("X",test.checkRow(0, 3));

        test.changeBoard(1,0,"O");
        test.changeBoard(1,1,"O");
        test.changeBoard(1,2,"O");
        assertEquals("O",test.checkRow(1, 3));

        test.changeBoard(1,2,"X");
        assertEquals("N",test.checkRow(1, 3));

    }

    @Test
    public void testGetEle() {
        Board test = new Board();

        test.changeBoard(0,0,"X");
        assertEquals("X",test.getEle(0, 0));
        assertEquals(" ",test.getEle(1, 0));

    }

    @Test
    public void testCheckDia() {
        Board test = new Board(4);
        assertEquals("N",test.checkDia(4));

        test.changeBoard(0,0,"O");
        test.changeBoard(1,1,"O");
        test.changeBoard(2,2,"O");
        test.changeBoard(3,3,"O");
        assertEquals("O",test.checkDia(4));

        test.changeBoard(1,1,"X");
        assertEquals("N",test.checkDia(4));

        test.changeBoard(0,3,"X");
        test.changeBoard(1,2,"X");
        test.changeBoard(2,1,"X");
        test.changeBoard(3,0,"X");
        assertEquals("X",test.checkDia(4));

    }

    @Test
    public void testCheckBoard() {
        Board test = new Board();
        assertEquals("N",test.checkBoard(3));

        test.changeBoard(0,0,"O");
        test.changeBoard(1,1,"O");
        test.changeBoard(2,2,"O");
        assertEquals("O",test.checkBoard(3));

        test.changeBoard(1,1,"X");
        assertEquals("N",test.checkBoard(3));

        test.changeBoard(0,1,"X");
        test.changeBoard(2,1,"X");
        assertEquals("X",test.checkBoard(3));

    }
}
