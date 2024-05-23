package org.example;

import java.util.ArrayList;

public class Room {
    private final String name;
    private String description;
    private String outCome;
    private String items;
    private ArrayList<String> requiredItems = new ArrayList<>();
    private String hints;
    private ArrayList<String> nextRooms = new ArrayList<>();
    private ArrayList<String> preRoomS = new ArrayList<>();
    private Boolean clear = false;

    private String challenge;
    private String answer;

    private ArrayList<String> actions = new ArrayList();

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

    public String getOutCome() {
        return outCome;
    }

    public void setOutCome(String outCome){
        this.outCome = outCome;
    }
    public void setItems(String item){
        this.items = (item);
    }
    public String getItems(){
        return this.items;
    }

    public void setRequiredItems(String item){
        this.requiredItems.add(item);
    }
    public ArrayList<String> getRequiredItems(){
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

    public Boolean isCleared(){
        return this.clear;
    }

    public void cleared(){
        this.clear = true;
    }

    public void setActions(String action){
        this.actions.add(action);
    }

    public ArrayList<String> getActions(){
        return this.actions;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setAnswer(String challenge) {
        this.answer = challenge;
    }

    public Boolean getAnswer(String answer) {
        return (answer.equalsIgnoreCase(this.answer));
    }



}
