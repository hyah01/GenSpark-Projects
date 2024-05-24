package org.example;
import java.util.ArrayList;

public class Room {
    // Initializing all the variable we need
    private final String name;
    private String description;
    private String outCome;
    private String items = "";
    private final ArrayList<String> requiredItems = new ArrayList<>();
    private final ArrayList<String> ActionItem = new ArrayList<>();
    private String hints;
    private final ArrayList<String> nextRooms = new ArrayList<>();
    private final ArrayList<String> preRoomS = new ArrayList<>();
    private Boolean clear = false;
    private String challenge = "";
    private String answer;
    private String event = "";
    private ArrayList<String> eventOutCome = new ArrayList<>();
    private ArrayList<String> actions = new ArrayList<>();


    // Constructor of name and get name
    public Room(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    // Set and get Description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    // Set up for a singular challenge that have 1 answer the user either need to choose to type out
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
    public String getOutCome() {
        return outCome;
    }
    public void setOutCome(String outCome){
        this.outCome = outCome;
    }

    // Provide the room with an item that the player can obtain
    public void setItems(String item){
        this.items = (item);
    }
    public String getItems(){
        return this.items;
    }

    // Set up a requirement or "key(s)" the player need to have before they can go into that room
    public void setRequiredItems(String item){
        this.requiredItems.add(item);
    }
    public ArrayList<String> getRequiredItems(){
        return this.requiredItems;
    }

    // Set up a requirement or "key(s)" the player need to have before they DO something in that room
    public void setActionItem(String item){
        this.ActionItem.add(item);
    }
    public ArrayList<String> getActionItem(){
        return this.ActionItem;
    }

    // Hint system for player if they want to use it
    public String getHints() {
        return hints;
    }
    public void setHint(String hint){
        this.hints = hint;
    }

    // Ways for map traversal, allowing the code to know which room can it go into from where they currently are
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

    /*
    * Let the code know if the room is cleared with the item(s) already obtained, it doesn't have to reprint the same
    line again
    * */
    public Boolean isCleared(){ return this.clear;}
    public void cleared(){ this.clear = true;}

    // Similarly to challenge but rather than having 1 right answers, each of the answer will have something to tell the player
    public void setEvent(String event){this.event = event;}
    public String getEvent(){return this.event;}
    public void setEventOutcome(ArrayList<String> event){this.eventOutCome = event;}
    public ArrayList<String> getEventOutcome(){return this.eventOutCome;}
    public void setActions(ArrayList<String> action){this.actions = action;}
    public ArrayList<String> getActions(){return this.actions;}

    // This takes care of the actions based on what kind of GENERAL action is given by game master
    public boolean doAction(Item player, String action){
        switch(action){
            // give the item after they picked the choice
            case "Grab":
                player.giveItem(this.getItems());
                this.cleared();
                return true;
            // use the item if they have it and remove them, if not tell them they don't got it
            case "Use":
                for (String item: this.getActionItem()){
                    if (!(player.hasItem(item))){
                        System.out.println("You don't have the required item(s)");
                        return false;
                    }
                }
                for (String item: this.getActionItem()){
                    player.removeItem(item);
                }
                this.cleared();
                return true;
            // use the item if they have it and not removed it, if not tell them they don't got it
            case "Use2":
                for (String item: this.getActionItem()){
                    if (!(player.hasItem(item))){
                        System.out.println("You don't have the required item(s)");
                        return false;
                    }
                }
                if (!(this.getItems().isEmpty())){
                    player.giveItem(this.getItems());
                }
                this.cleared();
                return true;

            // Trade in the item they have for something else
            case "Replace":
                for (String item: this.getActionItem()){
                    if (!(player.hasItem(item))){
                        System.out.println("...");
                        return false;
                    }
                }
                for (String item: this.getActionItem()){
                    player.removeItem(item);
                    player.giveItem(this.getItems());
                }
                this.cleared();
                return true;
            case "Ending":
                if (player.hasItem("Literature")){
                    System.out.println("""
                    Jacob Badson seems to noticed an unknown power within you...
                    He let out an immense screech " IS THAT LITERATURE "
                    Jacob Badson, the master of STEM, holding the 4 foundation Subject
                    SCIENCE, ENGINEERING, TECHNOLOGY, and MATHEMATICS
                    couldn't comprehend the power of literature, exploded into million of pieces
                    
                    Atlas the door out of this retches place was opened
                    You my friend are a winner winner chicken dinner
                    
                    --- Fin ---
                    """);
                } else {
                    System.out.println("""
                    Jacob Badson, the master of STEM, holding the 4 foundation Subject
                    SCIENCE, ENGINEERING, TECHNOLOGY, and MATHEMATICS
                    couldn't help but to laughed at your pathetic display of "power".
                    He simply flex on you with his 4 degrees and you fall down to the ground.
                    He used his most power programing language out of the many programing languages that he knows.
                    * Hy *
                    
                    While it was a powerful programing language, Jacob Badson didn't accounts that the power and blast
                    energy of the Hy programing language could send you flying outside his lair.
                    
                    Atlas you were sent flying outside of his evil lair
                    You my has escaped by some miracle, but your pride was wounded in the process.
                    
                    --- Fin ---
                    """);
                }
                this.cleared();
                return true;
            default:
                return true;
        }
    }



}
