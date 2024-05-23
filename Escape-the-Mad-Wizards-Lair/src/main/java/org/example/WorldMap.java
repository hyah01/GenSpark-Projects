package org.example;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    Room start = new Room("Starting Room");
    Room potionRoom = new Room("Potion Room");

    Map <String, Room> mapper = new HashMap<>();
    Room currentRoom = start;

    Item player = new Item();

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

    public void startMap() throws IOException, InterruptedException {
        start.addNextRoom("Potion Room");
        start.setDescription("You're in prison boi");
        start.setActions("1) Pick Up Key\n 2) Cancel");
        start.setChallenge("You noticed a man, well... half of a man talking to you. He told you that the only way to get out of this prison is to answer his question\n"+
                           "\nQuestion: What is 1 + 1\n1) 1\n2) 2\n3) 3");
        start.setAnswer("2");
        start.setOutCome("He rewarded you with a Prison Key, and died * look at what you did *\n");
        potionRoom.setRequiredItems("Prison Key");
        start.setItems("Prison Key");
        potionRoom.addPreRoom("Starting Room");
        potionRoom.setDescription("You're in potion room boi");
        mapper.put(start.getName(), start);
        mapper.put(potionRoom.getName(), potionRoom);

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
                    } else {
                        System.out.println(start.getChallenge());
                        while(true){
                            input = getInput.readLine();
                            if (currentRoom.getAnswer(input)){
                                if(!(currentRoom.getItems().isEmpty())){
                                    player.giveItem(currentRoom.getItems());
                                    System.out.println(currentRoom.getOutCome());
                                }
                                break;
                            } else {
                                System.out.println("WRONG");
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
                                        currentRoom.cleared();
                                        currentRoom = mapper.get(input);
                                        break;
                                    }
                                }
                                break;
                            }
                        } catch (NullPointerException e){
                            System.out.println("That's not right, spell it right dummy\n");
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
