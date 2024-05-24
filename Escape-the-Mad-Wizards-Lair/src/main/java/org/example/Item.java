package org.example;
import java.util.HashSet;

/*
* This class is the inventory system for the game, allow for editing of the inventory by adding items, removing,
* printing the inventory, and checking
*  if the item is in inventory.
* */
public class Item {
    HashSet<String> inventory = new HashSet<>();

    public Item(){
        this.inventory.add("Cheese");
    }

    public void giveItem(String item){
        this.inventory.add(item);
    }

    public Boolean hasItem(String item){
        return this.inventory.contains(item);
    }

    public void removeItem(String item){
        this.inventory.remove(item);
    }

    public void getInventory(){
        System.out.println(this.inventory);
    }


}
