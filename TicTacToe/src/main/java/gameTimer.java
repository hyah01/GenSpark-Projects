import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// Credited to Jacob Goodson
public class gameTimer {
    // Create A buffer that will read the line of user Inputs
    private final BufferedReader getInput = new BufferedReader(new InputStreamReader(System.in));
    private final int timeoutDur;
    private final ExecutorService executors = Executors.newVirtualThreadPerTaskExecutor();
    private String input;

    void inputWait(){
        try{
            while (!getInput.ready()){
                Thread.sleep(16);
            }
            input = getInput.readLine();
            executors.shutdownNow();
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    // After the given amount of time passed it will turn off
    void timerCountdown(){
        IntStream.range(1, timeoutDur / 1000).boxed().toList().reversed().forEach(i -> {
            try {
                Thread.sleep(1000);
                //System.out.println(i);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
    }

    private gameTimer(int timeoutDur) throws InterruptedException{
        this.timeoutDur = timeoutDur;
        // Default input is "invalid"
        input = "invalid";
        executors.submit(this::inputWait);
        executors.submit(this::timerCountdown);
        executors.awaitTermination(timeoutDur, TimeUnit.MILLISECONDS);

        // After a certain amount of time and the input was never changed it will shutdown the virtual thread, ending the game
        if (input.equals("invalid")){
            System.out.print("Out of time Buddy\n");
        }
        executors.shutdownNow();
    }

    static String getInput(int timeOut) throws InterruptedException {
        return new gameTimer(timeOut).input;
    }

}
