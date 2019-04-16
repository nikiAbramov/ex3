import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Locker {
    static private LongTermStorage LONG_TERM_STORAGE = new LongTermStorage();

    final String MSG_ERROR_RMV_1 = "Error: Your request cannot be completed at this time. Problem: cannot remove a negative number of items of type ";
    final Item ITEM_BASEBALL_BAT = ItemFactory.createSingleItem("baseball bat");
    final Item ITEM_FOOTBALL = ItemFactory.createSingleItem("football");

    private Integer capacity;
    private HashMap<String, Integer> inventory;

    /**
     * This is the constructor. it creates a new object of Locker.
     * @param capacity the ammount of storge units the locker has for storing items
     */
    public Locker(int capacity){
        this.capacity = capacity;
        this.inventory = new HashMap<String, Integer>();
    }

    /**
     * This method adds an items to the locker. it can be successful or not and according to the result it will return
     * an appropriate int value. If the wanted item has contradicting item in the locker the item wont be added and
     * -2 will be returned
     * @param item the item we want to put into the locker
     * @param n the ammount of said item we want to put
     * @return 0 if the items were added to the locker
     *          -1 if the items weren't added
     *          1 if the items were added, but it caused some items in the locker to move to long-term storage
     *          -2 if a contradicting item is in the locker
     */
    public int addItem(Item item, int n){
        if (ITEM_BASEBALL_BAT.getType().equals(item.getType())){
            if (inventory.containsKey(ITEM_FOOTBALL.getType())){
                System.out.println("Error: Your request cannot becompleted at this time. Problem: the locker cannot contain items of type " + item.getType());
                return -2;
            }
        }

        if (ITEM_FOOTBALL.getType().equals(item.getType())){
            if (inventory.containsKey(ITEM_BASEBALL_BAT.getType())){
                System.out.println("Error: Your request cannot becompleted at this time. Problem: the locker cannot contain items of type " + item.getType());
                return -2;
            }
        }

        if (getAvailableCapacity() - n * item.getVolume() < 0){
            System.out.println("Error: Your request cannot be completed at this time. Problem: no room for "
                    + n + " items of type " + item.getType());
            return -1;
        }

        int currentquantityOfItem = 0;
        if (inventory.containsKey(item.getType())){
            currentquantityOfItem = inventory.get(item.getType());
            inventory.remove(item.getType());
        }

        currentquantityOfItem += n;

        if (currentquantityOfItem * item.getVolume() > 0.5 * this.capacity){
            int itemsKept = 1;
            while (0.2 * this.capacity >= itemsKept * item.getVolume()) itemsKept++;
            itemsKept--; //If the breaked outside of the while loop, we have already more then 20% of the locker storage
            if (LONG_TERM_STORAGE.addItem(item, currentquantityOfItem - itemsKept) == 0){
                this.inventory.put(item.getType(), itemsKept);
                System.out.println("Warning: Action successful, but has caused items to be moved to storage");
                return 1;
            }
            else{
                // TODO: UNDERSTAND WHAT HAPPENS IN THIS POINT, RIGHT NOW IT DOESNT ADD ITEMS
                return -1;
            }
        }

        this.inventory.put(item.getType(), currentquantityOfItem);
        return 0;
    }

    /**
     * This method removes a certain amount of items of a certain type if it is possible
     * @param item The item which is requested to be removed
     * @param n the quantity of the item to be removed
     * @return 0 if successfully removed n items. -1 if the requested amount to remove was more then actual items
     * in locker, or if the requested amount was negative
     */
    public int removeItem(Item item, int n){
        if (n < 0){
            System.out.println(MSG_ERROR_RMV_1);
            return -1;
        }

        if (!inventory.containsKey(item.getType())){
            System.out.println("Error: Your request cannot be completed atthis time.  Problem: the locker does not contain " + n + " items of type " + item.getType());
            return -1;
        }

        if ((int)inventory.get(item.getType()) < n){
            System.out.println("Error: Your request cannot be completed atthis time.  Problem: the locker does not contain " + n + " items of type " + item.getType());
            return -1;
        }

        int new_quantity = inventory.get(item.getType()) - n;
        inventory.remove(item.getType());
        if (new_quantity > 0)
            inventory.put(item.getType(), new_quantity);

        return 0;
    }

    /**
     * This method returns the amount of items of the requested type
     */
    public int getItemCount(String type){
        if (!inventory.containsKey(type)) return 0;
        return inventory.get(type);
    }

    /**
     * This method returns a map of all item types with there respective quanteties in the locker
     * @return an item of item types and their amount in the locker
     */
    public Map<String, Integer> getInventory(){
        return this.inventory;
    }

    /**
     * This method returns the locker's maximum capacity
     * @return int of the lockers capacity
     */
    public int getCapacity() { return this.capacity; }

    /**
     * This method returns the amount of space units which are unoccupied by items
     * @return int of the available space
     */
    public int getAvailableCapacity(){
        int availableCapacity = this.capacity;
        Iterator iter = inventory.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry pair = (Map.Entry)iter.next();
            int itemVolume = ItemFactory.createSingleItem(pair.getKey().toString()).getVolume();
            availableCapacity -= (int)pair.getValue() * itemVolume;
        }
        return availableCapacity;
    }
}
