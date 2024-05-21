package W1D1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class W1D1Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testCountDown() {
        W1D1.CountDown(5);
        assertEquals("4\n3\n2\n1\n0", outContent.toString());
    }

    @Test
    public void testClassic() {
        W1D1.classic();
        String data = "1" +
        "\n2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        assertEquals("4\n3\n2\n1\n0", outContent.toString());
    }

    @Test
    public void testDifficult() {

    }

    @Test
    public void testMain() {

    }

    @Test
    public void testTimeMode() {

    }
}
