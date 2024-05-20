package W1D1;
import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


// Code for Week 1 Day 1
public class W1D1{
    // Initialize countdown number
    static boolean flag = false;
    
    // Function that takes care of the countdown for time mode
    static AtomicInteger CountDown(int time){
        // Using AtomicInteger in order to access the variable by many threads concurrently 
        final AtomicInteger counter = new AtomicInteger(time);
        Timer t = new Timer(true);
        // When calling CountDown it will run this timer task and decrementing the timer by 1 each second
        TimerTask tt = new TimerTask(){

            public void run(){

                if (counter.get() == 1 || flag == true){
                    t.cancel();
                    t.purge();
                    
                } 
                System.out.println("Time Left: " + counter.decrementAndGet());
                // This update the countdown number outside of this function so we know when the timer hits 0
            }

            
        };
        t.schedule( tt, 1000, 1000);
        
        return counter;
    }
    
    // Code for Classic Mode
    public static void classic(){
        Random rand = new Random();
        // Add 1 because random return 0 to (End Number - 1)
        int rand_int = rand.nextInt(100) + 1;
        // Keep Track of the number of attempts
        int counter = 0;
        // Reads the user's input
        Scanner SC = new Scanner(System.in);
        // Initialize User Input Number
        int num = -1;

        while (num != rand_int){
            System.out.println("Guess the Number:");
            num = SC.nextInt();
            // Everytime user input, counter increase by 1
            counter++;
            
            if (counter == 15){
                System.out.println("Can't tell if skill issue or what");
            }
            // When user guess the correct number it will print out a 'You got it!' message along with the number of attempts they took
            if (num == rand_int){
                System.out.println("You got it!");
                System.out.println("You took " + counter + " tries");
                break;
            // Hint for When the user input is higher than the random number
            } else if(num > rand_int){
                System.out.println("Lower!");
            // Hint for When the user input is lower than the random number
            } else {
                System.out.println("Higher!");
            }
        }
    }
    
    // Code for Difficulty Mode
    public static void difficult(int endNum, int attempts){
        Random rand = new Random();
        // This take in a number set by the main()
        int rand_int = rand.nextInt(endNum) + 1;
        Scanner SC = new Scanner(System.in);
        int num = -1;

        while (num != rand_int && attempts != 0){
            System.out.println("Guess the Number:");
            num = SC.nextInt();

            // When user guess the correct number it will print out a 'You got it!'
            if (num == rand_int){
                System.out.println("You got it!");
                break;
            // Hint for When the user input is higher than the random number
            } else if(num > rand_int){
                System.out.println("Lower!");
            // Hint for When the user input is lower than the random number
            } else {
                System.out.println("Higher!");
            }
            // Decreases the attempts number as user input in numbers
            attempts -= 1;
            // Let the user know how many attempts they have left
            System.out.print("You have " + attempts + " Attempts Left\n");
            // When the attempts counter reaches 0, it will end the game
            if (attempts == 0){
                System.out.println("Sorry you kinda stinky, no more attemps :");
                break;
            }
        }
    }

    // Code for Difficulty Time Mode
    public static void timeMode(int endNum, int time){
        Random rand = new Random();
        int rand_int = rand.nextInt(endNum) + 1;
        Scanner SC = new Scanner(System.in);
        int num = -1;
        // It will let the user know how much time they have in total
        System.out.println("You have " + time + " seconds to guess the number:");
        // This run the function CountDown to start the timer
        var countDownCounter = CountDown(time);
        while (num != rand_int){
            System.out.println("Guess the Number:");
            num = SC.nextInt();
            // When the timer reaches 0, it will let let the user know they have ran out of time and the answer
            if (countDownCounter.get() == 0){
                System.out.println("Time's up stinky :");
                System.out.println("The answer was " + rand_int);
                break;
            } else {
                if (num == rand_int){
                System.out.println("You got it!");
                flag = true;
                break;
                } else if(num > rand_int){
                    System.out.println("Lower!");
                } else {
                    System.out.println("Higher!");
                }
            }

        }
        
    }

    public static void main(String args[]){
        Scanner lineSC = new Scanner(System.in);
        // a variable that allows the user to continue playing until they don't want to anymore
        boolean con = true;
        boolean repeat;

        // While con (continue) is true, the game will continue 
        while (con){
            System.out.println("Pick your game mode:");
            System.out.println("( 1 ) Classic Mode\n( 2 ) Difficult Mode\n( 3 ) Time Mode");
            String line = lineSC.next();
            repeat = true;
            // This switch case allow the user to pick the gamemodes
            switch(line){
                // Case 1 will be the classic gamemode
                case "1":
                    classic();
                    break;
                // Case 2 will be the difficult gamemode
                case "2":
                    System.out.println("Pick your difficulty:");
                    System.out.println("( 1 ) Easy : Find the number 1-50 with 100 attempts\n( 2 ) Medium : Find the number 1-100 with 50 attempts\n( 3 ) hard : Find the number 1-500 with 20 attempts");
                    line = lineSC.next();
                    // This switch case allow the user to pick the difficulty of the difficult gamemode
                    switch(line){
                        case "1":
                            difficult(50,100);
                            break;
                        case "2":
                            difficult(100,50);
                            break;
                        case "3":
                            difficult(500,20);
                            break;
                        // If they input any number other than 1-3 it will let the user know
                        default: 
                            System.out.println("Enter a number 1-3 to choose your difficulty");
                    }
                    break;
                // Case 3 will be the time gamemode
                case "3":
                    System.out.println("Pick your difficulty:");
                    System.out.println("( 1 ) Easy :Find the number 1-50 with 2 minutes time limit\n( 2 ) Medium : Find the number 1-100 with 1 minute time limit\n( 3 ) hard : Find the number 1-500 with 30 seconds time limit");
                    line = lineSC.next();
                    // This switch case allow the user to pick the difficulty of the time gamemode
                    switch(line){
                        case "1":
                            timeMode(50,120);
                            break;
                        case "2":
                            timeMode(100,60);
                            break;
                        case "3":
                            timeMode(1,5);
                            break;
                        // If they input any number other than 1-3 it will let the user know
                        default: 
                            System.out.println("Enter a number 1-3 to choose your difficulty for the Time Mode");
                    }
                    break;
                // If they input any number other than 1-3 it will let the user know
                default:
                    System.out.println("Enter a number 1-3 to choose your gamemode");
                }

            // After a game is finished it will ask if the user want to try again
            System.out.println("Would you like to try again? Y/N");
            line = lineSC.next();
            if (!(line.equals("Y") || line.equals("y") )){
                con = false;
                // Let the user know the game ended
                System.out.println("GG");
                lineSC.close();
            }
            
        }
        

    }
}
