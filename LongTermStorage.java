import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

import java.util.HashMap;
import java.util.Map;

public class LongTermStorage {

    final int LONG_TERM_MAX_STORAGE = 1000;
    HashMap<String, Integer> inventory;

    /**
     * constructor for the long term storage
     */
    public LongTermStorage(){
        inventory = new HashMap<String, Integer>();
    }

    /**
     * This method adds n items of the requested type to the inventory.
     * @param item the item of which the user wants to put in the storage
     * @param n the number of items the user wishes to put in the storage
     * @return 0 if successful, -1 if there was a problem and the items weren't added
     */
    public int addItem(Item item, int n){
        if (n < 0) return -1;

        if (getAvailableCapacity() < item.getVolume() * n){
            System.out.println("Error: Your request cannot be completed at this time. Problem: no room for "
                    + n + " Items of type " + item.getType());
            return -1;
        }

        int currentQuantity = 0;
        if (this.inventory.containsKey(item.getType())){
            currentQuantity = this.inventory.get(item.getType());
            this.inventory.remove(item.getType());
        }

        this.inventory.put(item.getType(), n + currentQuantity);
        return 0;
    }

    /**
     * This method resets the inventory, effectivly removing all items in it
     */
    public void resetInventory() { inventory = new HashMap<String, Integer>(); }

    /**
     * this method returns the amount of items of the requested type are in the inventory
     * @param type The type about which we ask
     * @return the number of items of said type in the inventory
     */
    public int getItemCount(String type){
        if (!this.inventory.containsKey(type)) return 0;
        return this.inventory.get(type);
    }

    /**
     * this method returns a map listing all the items in the storage and their quantity
     * @return Map containing information about the inventory
     */
    public Map<String, Integer> getInventory() { return this.inventory; }

    /**
     * returns the long term storage's total capacity
     * @return int, storage's maximum capacity
     */
    public int getCapacity() { return LONG_TERM_MAX_STORAGE; }

    /**
     * Returns the storage's available capacity. meaning space units which are unoccupied by items.
     * @return int, the free space units in the storage
     */
    public int getAvailableCapacity(){
        int occupidSpace = 0;
        Item[] allItems = ItemFactory.createAllLegalItems();
        for (int i = 0; i < allItems.length; i++){
            if (this.inventory.containsKey(allItems[i].getType())){
                occupidSpace += this.inventory.get(allItems[i].getType()) * allItems[i].getVolume();
            }
        }
        return LONG_TERM_MAX_STORAGE - occupidSpace;
    }
}
