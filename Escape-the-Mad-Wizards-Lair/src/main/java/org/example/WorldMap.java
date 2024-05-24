package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// This takes care of initializing the game and decides what the player can and cannot do
public class WorldMap {
    // Room Set Up
    Room darkCell = new Room("Dark Cell");
    Room potionRoom = new Room("Potion Room");
    Room bossRoom = new Room("Boss Room");
    Room grandHallWay = new Room("Grand Hall Way");
    Room sleepingGuard = new Room("Sleeping Guard");
    Room guardRoom = new Room("Guard Room");
    Room infirmary = new Room("Infirmary");
    Room alterRoom = new Room("Alter");
    Room guardItemRoom = new Room("Guard Room Side");
    Room supplyRoom = new Room("Supply Room");
    Room chamberRoom = new Room("Chamber");
    Room wardenOffice = new Room("Warden Office");
    Room trapRoom = new Room("Trap Room");

    // A map allow for the code to store the possible places to go and allow for transversal
    Map <String, Room> mapper = new HashMap<>();
    // Initializing the initial room
    Room currentRoom = darkCell;
    // Initializing the player
    Item player = new Item();

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

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
                    // If the room has already been cleared it will not print the description again so the player
                    // don't get spam by events they already did
                    if (currentRoom.isCleared()){
                        System.out.println("Nothing else to see here");
                        // This take care of challenge rooms where only 1 right answer
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
                            // This code take care of the events where multiple options are provided
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
                            // If no requirements are here player can just move forward
                            if (mapper.get(input).getRequiredItems().isEmpty()){
                                currentRoom = mapper.get(input);
                                break;
                            } else {
                                boolean hasALlItem = true;
                                // if requirements are here will not let the player move forward
                                for (String item: mapper.get(input).getRequiredItems()){
                                    if (!player.hasItem(item)){
                                        // Lock system where the player can't go on if they don't have the required item
                                        System.out.println("Sorry You can't continue, missing item(s)\n");
                                        hasALlItem = false;
                                        break;
                                    }
                                }
                                if(hasALlItem){
                                    currentRoom = mapper.get(input);
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
        mapper.put(supplyRoom.getName(), supplyRoom);
        mapper.put(trapRoom.getName(), trapRoom);
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
        sleepingGuard.setRequiredItems("Prison Key");
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
                        What will you do:
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
                        Instead you find something shinny, on the table.
                        What will you do:
                        1) Grab the item
                        2) Walk away
                        """);
        infirmary.setActions(new ArrayList<>(Arrays.asList("Grab", "Leave")));
        infirmary.setEventOutcome(new ArrayList<>(Arrays.asList("You walked closer and the object, which seem to be a rusty knife "
                ,"You backed away from the shinny item")));

        // Set up for Guard Room
        guardRoom.addPreRoom(sleepingGuard.getName());
        guardRoom.addNextRoom(chamberRoom.getName());
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
        guardItemRoom.setHint("""
                            Try Morse Code
                            https://cdn.shopify.com/s/files/1/2223/4507/files/morse-chart_grande.png?v=1501447409
                            """);
        guardItemRoom.setOutCome("""
                            You figured it out! the answer was "Warden".
                            The screen disappear and appeared a *Warden Key*
                            """);
        guardItemRoom.setItems("Warden Key");

        // Set up for Warden Office
        wardenOffice.setRequiredItems("Warden Key");
        wardenOffice.addPreRoom(guardRoom.getName());
        wardenOffice.addNextRoom(guardRoom.getName());
        wardenOffice.addPreRoom(alterRoom.getName());
        wardenOffice.addNextRoom(alterRoom.getName());
        wardenOffice.setDescription("""
                                    The player enters a lavishly decorated compared to the rest of the prison, this room contains a large desk,
                                    bookshelves filled with dusty tomes, and a portrait of the warden. You see a weird shinny
                                    Medallion... well half of it... like the old man........
                                    
                                    WAIT A MINUTE, is that the warden looking straight at you???
                                    """);
        wardenOffice.setItems("Left Half of Medallion");
        wardenOffice.setEvent("""
                            The warden appears to be staring directly at you, or even directly into your soul.
                            What will you do:
                            1) Confront the Warden
                            2) Grab half Medallion and leave
                            """);
        wardenOffice.setActions(new ArrayList<>(Arrays.asList("Grab", "Grab")));
        wardenOffice.setEventOutcome(new ArrayList<>(Arrays.asList("""
                        You ran up to the Warden, getting ready to unleash your most powerful punch yet.
                        As you get closer, you noticed the Warden let out a very loud snore...
                        The Warden is sleeping?... Up Straight... Eyes open... This is weird but you didn't mind it and
                        grabbed the Medallion.
                        """, """
                        You quickly grabbed the Half Medallion and fled the Warden Office, but strangely... The Warden
                        doesn't seem to be chasing you. Maybe you struck fear into him with you Morse Code Solving Ability.
                        """)));

        // Set up for Chamber Room
        chamberRoom.addPreRoom(guardRoom.getName());
        chamberRoom.setDescription("""
                                    The player enters a he air is filled with the metallic scent of blood. Chains and
                                    various torture devices hang from the walls, and a brazier glows ominously in the center.
                                    There is another prisoner chained up against the wall in the chamber.
                                    """);
        chamberRoom.setItems("The Middle Finger");
        chamberRoom.setEvent("""
                            The prisoner looked up at you.
                            What do you want to do:
                            1) Free the prisoner
                            2) Walk away
                            """);
        chamberRoom.setActions(new ArrayList<>(Arrays.asList("Grab", "Leave")));
        chamberRoom.setEventOutcome(new ArrayList<>(Arrays.asList("""
                        You unlocked the prisoner chains and freed him in hope of having a companion
                        The prisoner screamed at you
                        
                        Prisoner: YOU THINK IM STUPID, I KNOW THIS IS ONE OF YOUR TRICK I WONT FALL FOR IT
                        
                        The prisoner then proceed to lock himself back up... and give you the *BIRD*
                        * Hey at least you tried *
                        """, "You decided to leave")));

        // Set up for Alter Room
        alterRoom.addPreRoom(infirmary.getName());
        alterRoom.addPreRoom(wardenOffice.getName());
        alterRoom.addNextRoom(wardenOffice.getName());
        alterRoom.addNextRoom(supplyRoom.getName());
        alterRoom.setActionItem("The Middle Finger");
        alterRoom.setDescription("""
                                    The player entered a forgotten chapel with a crumbling altar and broken stained
                                    glass windows. The atmosphere is eerily calm.
                                    """);
        alterRoom.setEvent("""
                            There is a space for you to pray
                            What do you want to do:
                            1) Knee down and pray
                            2) Give the Bird
                            """);
        alterRoom.setActions(new ArrayList<>(Arrays.asList("Leave", "Use2")));
        alterRoom.setEventOutcome(new ArrayList<>(Arrays.asList("You prayed but nothing happen", """
                        Instead of praying you decided to do the *BIRD*
                        Suddenly a strange liquid fell onto your face. Smells kind of like spit...
                        You looked around and see nothing... perhaps a sign of higher power??
                        """)));

        // Set up for Supply Room
        supplyRoom.addPreRoom(alterRoom.getName());
        supplyRoom.addNextRoom(potionRoom.getName());
        supplyRoom.addNextRoom(trapRoom.getName());
        supplyRoom.setDescription("""
                             Shelves stacked with crates and barrels, filled with food and basic supplies.
                             The room is cluttered and dimly lit.
                             """);
        supplyRoom.setChallenge("""
                           Suddenly a magic screen pop up in front of you.
                           
                           The screen read:
                           There's a reward if you can solve this problem, a prize that awaits the clever and persistent
                           
                           What does this mean:
                           "xwemz xbwqwv"
                           """);
        supplyRoom.setAnswer("power potion");
        supplyRoom.setHint("""
                            CAESARRRRRRRRRRR 8
                            ---
                            p -> x
                            e -> m
                            ---
                            """);
        supplyRoom.setOutCome("""
                            You figured it out! the answer was "power potion".
                            The screen disappear and appeared a *Potion Key*
                            """);
        supplyRoom.setItems("Potion Key");



        // Set up for Potion Room
        potionRoom.setRequiredItems("Potion Key");
        potionRoom.addPreRoom(supplyRoom.getName());
        potionRoom.setDescription("""
                The player entered a room filled with the mingled scents of herbs, the room's shelves are
                crowded with vials and bubbling flasks. The room is also cluttered with open books, a mortar and pestle,
                and a simmering cauldron emitting green steam. Cobwebs hang from ceiling beams.
                """);
        potionRoom.setItems("Water");
        potionRoom.setEvent("""
                            You noticed all kind of potion, healing potions, strength potions, lighting in a bottle, WATER!
                            even potions that can give you god like powers. What ever potion you can think of, its all in here.
                            What Would you like to do?
                            1) Grab the potions
                            2) Walk away
                            """);
        potionRoom.setActions(new ArrayList<>(Arrays.asList("Grab", "Leave")));
        potionRoom.setEventOutcome(new ArrayList<>(Arrays.asList("""
                        As you reach out to get the potions, you accidentally knocked over all the potions, boo hoo.
                        For some miracle... or curse... I don't know. The water that you saw was still in tact.
                        You obtained WATER.
                        """," If anything you can always go back")));

        // Set up for Trap Room
        trapRoom.addPreRoom(supplyRoom.getName());
        trapRoom.addNextRoom(grandHallWay.getName());
        trapRoom.setDescription("""
                             The player entered a room filled with traps that they can't hope to cross over.
                             The traps were kittens... NO... not the kittens, how could the payer ever over come this...
                             
                             The player take a look around and find a goblin staring at the player.
                             """);
        trapRoom.setChallenge("""
                           The goblin said:
                           Yo dog, what is Jacob Badson *REAL* height?
                           1) 6'
                           2) 5'4
                           3) 5'8
                           4) 4'11
                           5) 5'5
                           """);
        trapRoom.setAnswer("4");
        trapRoom.setHint("""
                            His true height
                            """);
        trapRoom.setOutCome("""
                            You figured it out! the answer was 4'11
                            Good Job, you figured out that 5'8 was a misdirection
                            Jacob Badson's *TRUE* height is 4'11.
                            
                            The goblin gave you a *Right Half of Medallion*
                            """);
        trapRoom.setItems("Right Half of Medallion");

        // Set up for the hall way to boss room
        grandHallWay.addNextRoom(bossRoom.getName());
        grandHallWay.addPreRoom(trapRoom.getName());
        grandHallWay.setDescription("""
                The grand hallway before the boss room stretches out, illuminated by three massive chandeliers casting
                a golden glow. On either side, three towering statues of ancient warriors stand guard, their eyes
                seemingly following your every move. The marble floor is inlaid with a pattern of interlocking triangles,
                subtly hinting at the number three. At the far end, three imposing doors await, each adorned with intricate
                carvings that hint at the final challenge beyond.
                """);
        grandHallWay.setEvent("""
                           Noticed A Slot in the wall, looks like a triangle.
                           1) Take A closer look
                           2) Walk Away
                           """);
        grandHallWay.setActionItem("Cheese");
        grandHallWay.setItems("Literature");
        grandHallWay.setActions(new ArrayList<>(Arrays.asList("Explore","Leave", "Replace")));
        grandHallWay.setEventOutcome(new ArrayList<>(Arrays.asList("Just look like a triangle"," If anything you can always go back", "Who knew the cheese you had with you the whole time would unlock such power")));


        // Set up for Boss Room
        bossRoom.setRequiredItems("Right Half of Medallion");
        bossRoom.setRequiredItems("Left Half of Medallion");
        bossRoom.addPreRoom(grandHallWay.getName());
        bossRoom.setDescription("""
                       You enter a vast, circular chamber filled with arcane energy. Flickering runes cover
                       the stone walls, casting eerie light on shelves crammed with strange apparatuses.
                       In the center, Jacob Badson, the Grand Mad Wizard of STEM, stands atop a raised platform,
                       surrounded by floating line of codes and whirling scientific instruments.
                       His eyes glow with a dangerous intellect, and he wields a staff crackling with raw,
                       chaotic power.
                       """);
        bossRoom.setEvent("""
                            Text here
                            1) Fight for your survival
                            2) Walk away
                            """);
        bossRoom.setActions(new ArrayList<>(Arrays.asList("Ending", "Leave")));
        bossRoom.setEventOutcome(new ArrayList<>(Arrays.asList("","I mean yeah I guess.. the boss fight isn't important or anything, just walk away from the boss")));




    }



}
