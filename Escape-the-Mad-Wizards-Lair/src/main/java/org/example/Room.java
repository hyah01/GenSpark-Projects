package org.example;

import java.util.ArrayList;

public class Room {
    private final String name;
    private String description;
    private ArrayList<String> items;
    private ArrayList<String> requiredItems;
    private String hints;

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


}
