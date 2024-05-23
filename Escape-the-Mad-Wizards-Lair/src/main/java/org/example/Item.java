package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
