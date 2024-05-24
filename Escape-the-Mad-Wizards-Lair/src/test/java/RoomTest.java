import org.example.Item;
import org.example.Room;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.*;

public class RoomTest {
    @Test
    public void TestRoomConstructor(){
        Room test = new Room("Room 1");
        assertEquals("Room 1",test.getName());
        Room test2 = new Room("Room 2");
        assertEquals("Room 2",test2.getName());
    }
    @Test
    public void TestSetDescription() {
        Room test = new Room("Test Room");
        test.setDescription("This is a test room");
        assertEquals("This is a test room", test.getDescription());
    }

    @Test
    public void TestSetChallenge() {
        Room test = new Room("Test Room");
        test.setChallenge("this is a challenge");
        assertEquals("this is a challenge", test.getChallenge());
    }

    @Test
    public void TestSetAnswer() {
        Room test = new Room("Test Room");
        test.setAnswer("testing");
        assertTrue(test.getAnswer("testing"));
    }

    @Test
    public void TestSetOutCome() {
        Room test = new Room("Test Room");
        test.setOutCome("true");
        assertEquals("true", test.getOutCome());
    }

    @Test
    public void TestSetItems() {
        Room test = new Room("Test Room");
        test.setItems("Wand");
        assertEquals("Wand", test.getItems());
    }

    @Test
    public void TestSetRequiredItems() {
        Room test = new Room("Test Room");
        test.setRequiredItems("Wand");
        test.setRequiredItems("Stick");

        assertArrayEquals(new String[]{"Wand","Stick"}, test.getRequiredItems().toArray());
    }

    @Test
    public void TestSetActionItem() {
        Room test = new Room("Test Room");
        test.setActionItem("Wand");
        test.setActionItem("Stick");

        assertArrayEquals(new String[]{"Wand","Stick"}, test.getActionItem().toArray());
    }

    @Test
    public void TestSetHint() {
        Room test = new Room("Test Room");
        test.setHint("hint");
        assertEquals("hint",test.getHints());
    }

    @Test
    public void TestAddNextRoom() {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        room1.addNextRoom(room2.getName());
        room1.addNextRoom(room3.getName());
        room2.addNextRoom(room3.getName());
        assertEquals(new ArrayList<>(Arrays.asList(room2.getName(),room3.getName())),room1.getNextRoom());
        assertEquals(new ArrayList<>(Collections.singletonList(room3.getName())),room2.getNextRoom());

    }

    @Test
    public void TestAddPreRoom() {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        room1.addPreRoom(room2.getName());
        room1.addPreRoom(room3.getName());
        room2.addPreRoom(room3.getName());
        assertEquals(new ArrayList<>(Arrays.asList(room2.getName(),room3.getName())),room1.getPreRoom());
        assertEquals(new ArrayList<>(Collections.singletonList(room3.getName())),room2.getPreRoom());
    }

    @Test
    public void TestCleared() {
        Room test = new Room("Test Room");
        assertFalse(test.isCleared());
        test.cleared();
        assertTrue(test.isCleared());
    }

    @Test
    public void TestSetEvent() {
        Room test = new Room("Test Room");
        test.setEvent("this is an event");
        assertEquals("this is an event", test.getEvent());
    }

    @Test
    public void TestSetEventOutcome() {
        Room test = new Room("Test Room");
        test.setEventOutcome(new ArrayList<>(Arrays.asList("event1","event2")));
        assertEquals(new ArrayList<>(Arrays.asList("event1","event2")), test.getEventOutcome());
    }

    @Test
    public void TestSetActions() {
        Room test = new Room("Test Room");
        test.setActions(new ArrayList<>(Arrays.asList("action1","action2")));
        assertEquals(new ArrayList<>(Arrays.asList("action1","action2")), test.getActions());
    }

    @Test
    public void TestDoAction() {
        Room room = new Room("Room");
        //Room room2 = new Room("Room 2");
        Item player = new Item();
        room.setItems("Stick");
        room.setActionItem("Stick");
        // Test if it returns true
        assertTrue(room.doAction(player,"Grab"));
        // Test if the room is cleared after using Grab
        assertTrue(room.isCleared());
        // Test if it gives the player a Stick
        assertTrue(player.hasItem("Stick"));
        room.setItems("Stone");
        room.setActionItem("Stone");
        // Test if it returns false since player don't have stone
        assertFalse(room.doAction(player,"Use"));
        room.doAction(player,"Grab");
        // Test if it returns true since player should have stone now
        assertTrue(room.doAction(player,"Use"));
        // Test if it returns false since the player shouldn't have stick and stone
        assertFalse(player.hasItem("Stick"));
        assertFalse(player.hasItem("Stone"));
        // Test if it returns false since player don't have stone to replace
        assertFalse(room.doAction(player,"Replace"));
        player.giveItem("Stone");
        player.giveItem("Stick");
        room.setItems("Paper");
        assertTrue(room.doAction(player,"Replace"));
        assertTrue(player.hasItem("Paper"));
        assertFalse(player.hasItem("Stone"));
        assertFalse(player.hasItem("Stick"));

    }
}
