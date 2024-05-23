import org.example.Item;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testItem(){
        Item test = new Item();
        assertTrue(test.hasItem("Cheese"));
    }
    @Test
    public void testGiveItem(){
        Item test = new Item();
        test.giveItem("Wand");
        test.giveItem("Healing Potion");
        assertTrue(test.hasItem("Healing Potion"));
        assertTrue(test.hasItem("Wand"));
    }

    @Test
    public void testRemoveItem(){
        Item test = new Item();
        test.giveItem("Wand");
        assertTrue(test.hasItem("Wand"));
        test.removeItem("Wand");
        assertFalse(test.hasItem("Wand"));
    }
}
