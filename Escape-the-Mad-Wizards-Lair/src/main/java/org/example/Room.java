package org.example;

import java.util.ArrayList;

public class Room {
    private final String name;
    private String description;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<String> requiredItems = new ArrayList<>();
    private String hints;
    private ArrayList<String> nextRooms = new ArrayList<>();
    private ArrayList<String> preRoomS = new ArrayList<>();

    public Room(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setItems(String item){
        this.items.add(item);
    }
    public ArrayList<String> getItems(String item){
        return this.items;
    }

    public void setRequiredItems(String item){
        this.requiredItems.add(item);
    }
    public ArrayList<String> getRequiredItems(String item){
        return this.requiredItems;
    }

    public String getHints() {
        return hints;
    }

    public void setHint(String hint){
        this.hints = hint;
    }

    public void addNextRoom(String room){
        this.nextRooms.add(room);
    }
    public ArrayList<String> getNextRoom(){
        return this.nextRooms;
    }

    public void addPreRoom(String room){
        this.preRoomS.add(room);
    }
    public ArrayList<String> getPreRoom(){
        return this.preRoomS;
    }


}
