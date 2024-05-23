package org.example;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class WorldMap {
    Room prisonRoom = new Room("Prison Cell");
    Room potionRoom = new Room("Potion Room");
    Room bossRoom = new Room("Boss Room");
    Room hallWayToBoss = new Room("Hall Way To Boss Room");

    Map <String, Room> mapper = new HashMap<>();
    Room currentRoom = prisonRoom;

    Item player = new Item();

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

    void MapSetUp(){
        // Set up for Prison Room
        prisonRoom.addNextRoom(potionRoom.getName());
        prisonRoom.setDescription("You're in prison boi");
        prisonRoom.setChallenge("""
                           You noticed a man, well... half of a man talking to you. He told you that the only way to get out of this prison is to answer his question
                           Question: What is 1 + 1
                           1) 1
                           2) 2
                           3) 3
                           """);
        prisonRoom.setAnswer("2");
        prisonRoom.setOutCome("He rewarded you with a Prison Key, and died * look at what you did *\n");
        prisonRoom.setItems("Prison Key");

        // Set up for Potion Room
        potionRoom.setRequiredItems("Prison Key");
        potionRoom.addPreRoom(prisonRoom.getName());
        potionRoom.addNextRoom(hallWayToBoss.getName());
        potionRoom.setDescription("You in potion Room");
        potionRoom.setItems("Water");
        potionRoom.setEvent("""
                            You noticed all kind of potion, healing potions, strength potions, lighting in a bottle, WATER!, even potions that can give you god like powers. What ever potion you can think of, its all in here.
                            What Would you like to do?
                            1) Grab the potions
                            2) Walk away
                            """);
        potionRoom.setActions(new ArrayList<String>(Arrays.asList("Grab","Leave")));
        potionRoom.setEventOutcome(new ArrayList<String>(Arrays.asList("""
                        As you reach out to get the potions, you accidentally knocked over all the potions, boo hoo.
                        For some miracle... or curse... I don't know. The water that you saw was still in tact.
                        You obtained WATER
                        """," If anything you can always go back")));

        // Set up for the hall way to boss room
        hallWayToBoss.addNextRoom(bossRoom.getName());
        hallWayToBoss.addPreRoom(potionRoom.getName());
        hallWayToBoss.setDescription("Hall way to boss Room, *secret* (for now)");
        hallWayToBoss.setEvent("""
                           Noticed A Slot in the wall, looks like a triangle
                           1) Take A closer look
                           2) Walk Away
                           """);
        hallWayToBoss.setActionItem("Cheese");
        hallWayToBoss.setItems("Gun");
        hallWayToBoss.setActions(new ArrayList<String>(Arrays.asList("Explore","Leave", "Replace")));
        hallWayToBoss.setEventOutcome(new ArrayList<String>(Arrays.asList("Just look like a triangle"," If anything you can always go back", "You Found A secret :0, It's a GUN ???")));


        // Set up for Boss Room
        bossRoom.setRequiredItems("Water");
        bossRoom.setRequiredItems("Prison Key");
        bossRoom.addPreRoom(hallWayToBoss.getName());
        bossRoom.setDescription("You In Boss Room");
        bossRoom.setItems("Water");
        bossRoom.setEvent("""
                            You noticed all kind of potion, healing potions, strength potions, lighting in a bottle, WATER!, even potions that can give you god like powers. What ever potion you can think of, its all in here.
                            What Would you like to do?
                            1) Grab the potions
                            2) Walk away
                            """);
        bossRoom.setActions(new ArrayList<String>(Arrays.asList("Grab","Leave")));
        bossRoom.setEventOutcome(new ArrayList<String>(Arrays.asList("""
                        As you reach out to get the potions, you accidentally knocked over all the potions, boo hoo.
                        For some miracle... or curse... I don't know. The water that you saw was still in tact.
                        You obtained WATER
                        """," If anything you can always go back")));

        mapper.put(prisonRoom.getName(), prisonRoom);
        mapper.put(potionRoom.getName(), potionRoom);
        mapper.put(bossRoom.getName(), bossRoom);
        mapper.put(hallWayToBoss.getName(), hallWayToBoss);


    }
    public void Play() throws IOException, InterruptedException {
        MapSetUp();
        while(true){
            Thread.sleep(1000);
            System.out.println(currentRoom.getName());
            if (!(currentRoom.isCleared())){
                System.out.println(currentRoom.getDescription());
            }
            System.out.println("What would you like to do?");
            System.out.println("1) Interact\n2) Move on\n3) Check Inventory");
            input = getInput.readLine();
            switch (input){
                case "1":
                    if (currentRoom.isCleared()){
                        System.out.println("Nothing else to see here");
                    } else if (!(currentRoom.getChallenge().isEmpty())){
                        System.out.println(currentRoom.getChallenge());
                        while(true){
                            input = getInput.readLine();
                            if (currentRoom.getAnswer(input)){
                                if(!(currentRoom.getItems().isEmpty())){
                                    player.giveItem(currentRoom.getItems());
                                    System.out.println(currentRoom.getOutCome());
                                    currentRoom.cleared();
                                }
                                break;
                            } else {
                                System.out.println("WRONG");
                            }
                        }
                    } else {
                        System.out.println(currentRoom.getEvent());
                        while(true){

                            try {
                                input = getInput.readLine();
                                ArrayList<String> events = currentRoom.getEventOutcome();
                                int temp = Integer.parseInt(input)-1;
                                if (currentRoom.doAction(player, currentRoom.getActions().get(temp))){
                                    System.out.println(events.get(temp));
                                } else {
                                    System.out.println("You don't got what it takes");
                                }
                                break;


                            } catch (NumberFormatException | IndexOutOfBoundsException e){
                                System.out.println("try number");
                            }
                        }
                    }
                    break;
                case "2":
                    while(true){
                        try {
                            if (!(currentRoom.getNextRoom().isEmpty())){
                                System.out.println("You can go to forward:");
                                currentRoom.getNextRoom().forEach(System.out::println);
                            }
                            if (!(currentRoom.getPreRoom().isEmpty())){
                                System.out.println("You can go backward to:");
                                currentRoom.getPreRoom().forEach(System.out::println);
                            }
                            input = getInput.readLine();
                            if (mapper.get(input).getRequiredItems().isEmpty()){
                                currentRoom = mapper.get(input);
                                break;
                            } else {
                                for (String item: mapper.get(input).getRequiredItems()){
                                    if (!player.hasItem(item)){
                                        System.out.println("Sorry You can't continue, missing item(s)\n");
                                        break;
                                    } else {
                                        currentRoom = mapper.get(input);
                                        break;
                                    }
                                }
                                break;
                            }
                        } catch (NullPointerException e){
                            System.out.println("That's not right, spell it right dummy\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case "3":
                    player.getInventory();
                    System.out.println("\n");

            }

            if (input.equals("q")){
                break;
            }
        }
    }



}
