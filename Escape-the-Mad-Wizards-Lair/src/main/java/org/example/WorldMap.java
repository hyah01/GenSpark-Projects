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

    BufferedReader getInput = new BufferedReader( new InputStreamReader(System.in));
    String input;

    public void startMap() throws IOException {
        start.addNextRoom("Potion Room");
        mapper.put(start.getName(), start);
        mapper.put(potionRoom.getName(), potionRoom);
        potionRoom.addPreRoom("Starting Room");

        while(true){
            System.out.println(currentRoom.getName());
            if (!(currentRoom.getNextRoom().isEmpty())){
                System.out.println("You can go to forward:");
                currentRoom.getNextRoom().forEach(System.out::println);
            }
            if (!(currentRoom.getPreRoom().isEmpty())){
                System.out.println("You can go backward to:");
                currentRoom.getPreRoom().forEach(System.out::println);
            }
            input = getInput.readLine();
            if (input.equals("q")){
                break;
            }
            currentRoom = mapper.get(input);
        }



    }



}
