package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class WorldMap {
    Room darkCell = new Room("Dark Cell");
    Room potionRoom = new Room("Potion Room");
    Room bossRoom = new Room("Boss Room");
    Room grandHallWay = new Room("Grand Hall Way");
    Room sleepingGuard = new Room("Sleeping Guard");
    Room guardRoom = new Room("Guard Room");
    Room infirmary = new Room("Infirmary");
    Room alterRoom = new Room("Alter");
    Room guardItemRoom = new Room("Guard Room Side");
    Room goblinRoom = new Room("Goblin Room");
    Room chamberRoom = new Room("Chamber");
    Room wardenOffice = new Room("Warden Office");


    Map <String, Room> mapper = new HashMap<>();
    Room currentRoom = darkCell;

    Item player = new Item();

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

    void MapSetUp(){
        mapper.put(darkCell.getName(), darkCell);
        mapper.put(potionRoom.getName(), potionRoom);
        mapper.put(bossRoom.getName(), bossRoom);
        mapper.put(grandHallWay.getName(), grandHallWay);
        mapper.put(sleepingGuard.getName(), sleepingGuard);
        mapper.put(infirmary.getName(), infirmary);
        mapper.put(guardRoom.getName(), guardRoom);
        mapper.put(alterRoom.getName(), alterRoom);
        mapper.put(guardItemRoom.getName(), guardItemRoom);
        mapper.put(wardenOffice.getName(), wardenOffice);
        mapper.put(chamberRoom.getName(), chamberRoom);
        mapper.put(goblinRoom.getName(), goblinRoom);
        // Set up for Prison Room
        darkCell.addNextRoom(sleepingGuard.getName());
        darkCell.setDescription("""
                                    The player awaken to the suffering darkness, air filled with mold and decays,
                                    rats scurrying across the hard cold stone. Dim light reveals a man chained to the wall
                                    you can hear him grown faintly as if he could be dying at any moment. Looking in your
                                    pocket you noticed that you have a triangular cheese. There is an iron door.
                                    """);
        darkCell.setChallenge("""
                           You walked up to a man, well... half of a man. As you try to converse with him. He told you
                           that the only way to get out of this prison is to answer his question.
                           
                           Question: The key is within reach, where can it be?
                           1) Behind some cracks in the wall.
                           2) In side his ripped torso.
                           3) It's a trick question, THERE IS NO ESCAPE.
                           """);
        darkCell.setAnswer("2");
        darkCell.setOutCome("""
                            You reach in to his ripped opened torso and grabbed the Iron Key, covered by the old man" +
                            "blood, and died.
                            * look at what you did *""");
        darkCell.setItems("Prison Key");

        // Set up for sleepingGuardRoom
        sleepingGuard.addPreRoom(darkCell.getName());
        sleepingGuard.addNextRoom(infirmary.getName());
        sleepingGuard.addNextRoom(guardRoom.getName());
        sleepingGuard.setDescription("""
                The player enters a small room, dimly lit by the dying light of a white candle. The dim light cast enough
                light for the player to see scrawny looking guard, sleeping on the chair, snoring loudly. There is not
                a single thing in the world that will wake this guard up from his beauty nap. The room is otherwise sparse,
                with bottles, crumbs and dust.
                """);
        sleepingGuard.setItems("Guard Room Key");
        sleepingGuard.setActionItem("Knife");
        sleepingGuard.setEvent("""
                        You noticed a shinny key hangs loosely from the sleeping guard's leather strap, you
                        would need something sharp to cut it.
                        1) Get the key
                        2) Walk away
                        """);
        sleepingGuard.setActions(new ArrayList<>(Arrays.asList("Use2", "Leave")));
        sleepingGuard.setEventOutcome(new ArrayList<>(Arrays.asList("You cut the leather strap and obtained *Guard Room Key*"
                        ,"You backed away from the guard")));

        // Set up for infirmary
        infirmary.addPreRoom(sleepingGuard.getName());
        infirmary.addNextRoom(alterRoom.getName());
        infirmary.setDescription("""
                The player enters a room, Beds with dirty linens line the walls, and cabinets filled with dusty medical
                supplies stand in the corner. The smell of antiseptic is overpowering.
                """);
        infirmary.setItems("Knife");
        infirmary.setEvent("""
                        You search around the infirmary to find anything that you can use on the light wound that you have.
                        Instead you find something shinny, on the t able.
                        1) Grab the item
                        2) Walk away
                        """);
        infirmary.setActions(new ArrayList<>(Arrays.asList("Grab", "Leave")));
        infirmary.setEventOutcome(new ArrayList<>(Arrays.asList("You walked closer and the object, which seem to be a rusty knife "
                ,"You backed away from the shinny item")));

        // Set up for Guard Room
        guardRoom.addPreRoom(sleepingGuard.getName());
        guardRoom.addNextRoom(chamberRoom.getName());
        guardRoom.addNextRoom(goblinRoom.getName());
        guardRoom.addNextRoom(wardenOffice.getName());
        guardRoom.addNextRoom(guardItemRoom.getName());
        guardRoom.setRequiredItems("Guard Room Key");
        guardRoom.setDescription("""
                            The player enters a room with flickering torch on the wall. A wooden table and chairs are
                            overturned, and the floor is strewn with playing cards and empty mugs.
                            """);
        guardRoom.setEvent("""
                            You looked around and there seem to be nothing here, you would think that there would be weapon
                            or maybe even guards, but nope, nothing.
                            1) Walk away
                            """);
        guardRoom.setActions(new ArrayList<>(List.of("Leave")));
        guardRoom.setEventOutcome(new ArrayList<>(List.of("")));

        // Set up for guard room other side
        guardItemRoom.addPreRoom(guardRoom.getName());
        guardItemRoom.setDescription("""
                             On the far side of the Guard Room, you noticed some kind of weird Magic... or rather
                             some kind of coding power.
                             """);
        guardItemRoom.setChallenge("""
                           Suddenly a magic screen pop up in front of you.
                           
                           The screen read:
                           There's a reward if you can solve this problem, a prize that awaits the clever and persistent
                           
                           What does this mean:
                           .-- .- .-. -.. . -.
                           """);
        guardItemRoom.setAnswer("warden");
        guardItemRoom.setHint("Try Morse Code");
        guardItemRoom.setOutCome("""
                            You figured it out! the answer was "Warden".
                            The screen disappear and appeared a *Warden Key*
                            """);
        guardItemRoom.setItems("Warden Key");




        // Set up for Potion Room
        potionRoom.setRequiredItems("Prison Key");
        potionRoom.addPreRoom(darkCell.getName());
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
                            if(input.equals("hint")){
                                System.out.println(currentRoom.getHints());
                            }
                            else if (currentRoom.getAnswer(input)){
                                if(!(currentRoom.getItems().isEmpty())){
                                    player.giveItem(currentRoom.getItems());
                                    System.out.println(currentRoom.getOutCome());
                                    currentRoom.cleared();
                                }
                                break;
                            } else {
                                System.out.println("Try again brah, Jacob ain't gonna let that slide\nTry 'hint' if you need help");
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
