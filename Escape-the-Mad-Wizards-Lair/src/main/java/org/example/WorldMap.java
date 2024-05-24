package org.example;

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
    Room grandHallWay = new Room("Grand Hall Way");

    Map <String, Room> mapper = new HashMap<>();
    Room currentRoom = grandHallWay;

    Item player = new Item();

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

    void MapSetUp(){
        // Set up for Prison Room
        prisonRoom.addNextRoom(potionRoom.getName());
        prisonRoom.setDescription("""
                                    The player awaken to the suffering darkness, air filled with mold and decays,
                                    rats scurrying across the hard cold stone. Dim light reveals a man chained to the wall
                                    you can hear him grown faintly as if he could be dying at any moment. Looking in your
                                    pocket you noticed that you have a triangular cheese. There is an iron door.
                                    """);
        prisonRoom.setChallenge("""
                           You walked up to a man, well... half of a man. As you try to converse with him. He told you
                           that the only way to get out of this prison is to answer his question.
                           
                           Question: The key is within reach, where can it be?
                           1) Behind some cracks in the wall.
                           2) In side his ripped torso.
                           3) It's a trick question, THERE IS NO ESCAPE.
                           """);
        prisonRoom.setAnswer("2");
        prisonRoom.setOutCome("""
                            You reach in to his ripped opened torso and grabbed the Iron Key, covered by the old man" +
                            "blood, and died * look at what you did *""");
        prisonRoom.setItems("Prison Key");


        // Set up for Potion Room
        potionRoom.setRequiredItems("Prison Key");
        potionRoom.addPreRoom(prisonRoom.getName());
        potionRoom.addNextRoom(grandHallWay.getName());
        potionRoom.setDescription("You in potion Room");
        potionRoom.setItems("Water");
        potionRoom.setEvent("""
                            You noticed all kind of potion, healing potions, strength potions, lighting in a bottle, WATER!, even potions that can give you god like powers. What ever potion you can think of, its all in here.
                            What Would you like to do?
                            1) Grab the potions
                            2) Walk away
                            """);
        potionRoom.setActions(new ArrayList<>(Arrays.asList("Grab", "Leave")));
        potionRoom.setEventOutcome(new ArrayList<>(Arrays.asList("""
                        As you reach out to get the potions, you accidentally knocked over all the potions, boo hoo.
                        For some miracle... or curse... I don't know. The water that you saw was still in tact.
                        You obtained WATER
                        """," If anything you can always go back")));

        // Set up for the hall way to boss room
        grandHallWay.addNextRoom(bossRoom.getName());
        grandHallWay.addPreRoom(potionRoom.getName());
        grandHallWay.setDescription("Hall way to boss Room, *secret* (for now)");
        grandHallWay.setEvent("""
                           Noticed A Slot in the wall, looks like a triangle
                           1) Take A closer look
                           2) Walk Away
                           """);
        grandHallWay.setActionItem("Cheese");
        grandHallWay.setItems("Literature");
        grandHallWay.setActions(new ArrayList<>(Arrays.asList("Explore","Leave", "Replace")));
        grandHallWay.setEventOutcome(new ArrayList<>(Arrays.asList("Just look like a triangle"," If anything you can always go back", "Who knew the cheese you had with you the whole time would unlock such power")));


        // Set up for Boss Room
        //bossRoom.setRequiredItems("Water");
        //bossRoom.setRequiredItems("Prison Key");
        bossRoom.addPreRoom(grandHallWay.getName());
        bossRoom.setDescription("You In Boss Room");
        bossRoom.setEvent("""
                            Text here
                            1) Fight for your survival
                            """);
        bossRoom.setActions(new ArrayList<>(Arrays.asList("Ending", "Leave")));
        bossRoom.setEventOutcome(new ArrayList<>(Arrays.asList("","I mean yeah I guess.. the boss fight isn't important or anything, just walk away from the boss")));

        mapper.put(prisonRoom.getName(), prisonRoom);
        mapper.put(potionRoom.getName(), potionRoom);
        mapper.put(bossRoom.getName(), bossRoom);
        mapper.put(grandHallWay.getName(), grandHallWay);


    }
    public void Play() throws IOException {
        MapSetUp();
        System.out.println("""
                Welcome to 'Escape the Mad Wizard's Lair' Text Adventure Game
                You are a -Prisoner- that was captured by the Grand Mad Wizard... Jacob Badson
                Your goal is to go through a series of room, find items that can aid you in your journey to escape from
                The Grand Mad Wizard... Jacob Badson.. son... son.. son... nn.... *THUNDER SOUNDS*
                
                Game Rule:
                Rule 1:
                When given options 1) 2) 3)... etc
                Just type in the NUMBER
                
                Rule 2:
                When asked a question with out option type the answer in LOWER CASE
                
                Rule 3:
                Type the name EXACTLY as given when traversing
                
                ---Game Start---
                
                """);
        System.out.println("Press Enter To Continue");
        input = getInput.readLine();
        while(true){
            // Every time the player go to a room it will display the name of the room
            System.out.println(STR."--==| \{currentRoom.getName()} |==--");
            if (!(currentRoom.isCleared())){
                // If the room isn't cleared (Item hasn't been found or event haven't been cleared) it will repeat
                System.out.println(currentRoom.getDescription());
                System.out.println("Press Enter To Continue");
                input = getInput.readLine();

            }
            // Always let the user know what they can do
            System.out.println("What would you like to do?");
            System.out.println("1) Interact\n2) Move on\n3) Check Inventory");
            input = getInput.readLine();
            switch (input){
                // Allows the player to interact with the Environment
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
                                System.out.println("Try again brah, Jacob ain't gonna let that slide");
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
                                    // Let the game know the game has ended
                                    if (currentRoom.getActions().get(temp).equals("Ending")){
                                        System.exit(0);
                                    }
                                } else {
                                    System.out.println("You don't got what it take do that");
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
                            // Show the possible room that player can move forward to
                            if (!(currentRoom.getNextRoom().isEmpty())){
                                System.out.println("You can go to forward:");
                                currentRoom.getNextRoom().forEach(System.out::println);
                                System.out.println();
                            }
                            // Show the possible that player can move back to
                            if (!(currentRoom.getPreRoom().isEmpty())){
                                System.out.println("You can go backward to:");
                                currentRoom.getPreRoom().forEach(System.out::println);
                                System.out.println();
                            }
                            input = getInput.readLine();
                            if (mapper.get(input).getRequiredItems().isEmpty()){
                                currentRoom = mapper.get(input);
                                break;
                            } else {
                                for (String item: mapper.get(input).getRequiredItems()){
                                    if (!player.hasItem(item)){
                                        // Lock system where the player can't go on if they don't have the required item
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
                            // In case the player spell the room name wrong
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
