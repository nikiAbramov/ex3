import oop.ex3.spaceship.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Locker {
    final String MSG_ERROR_RMV_1 = "Error: Your request cannot be completed at this time. Problem: cannot remove a negative number of items of type ";

    private Integer capacity;
    private HashMap<String, Integer> items;

    /**
     * This is the constructor. it creates a new object of Locker.
     * @param capacity the ammount of storge units the locker has for storing items
     */
    public Locker(int capacity){
        this.capacity = capacity;
        this.items = new HashMap<String, Integer>();
    }

    /**
     * This method adds an items to the locker. it can be successful or not and according to the result it will return
     * an appropriate int value
     * @param item the item we want to put into the locker
     * @param n the ammount of said item we want to put
     * @return 0 if the items were added to the locker
     *          -1 if the items weren't added
     *          1 if the items were added, but it caused some items in the locker to move to long-term storage
     */
    public int addItem(Item item, int n){
        if (items.containsKey(item.getType())){
            int currentquantityOfItem = items.get(item.getType());
            currentquantityOfItem += n;
            items.put(item.getType(), currentquantityOfItem);
        }
        else{
            items.put(item.getType(), n);
        }
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

        if (!items.containsKey(item.getType())){
            System.out.println("Error: Your request cannot be completed atthis time.  Problem: the locker does not contain " + n + " items of type " + item.getType());
            return -1;
        }

        if ((int)items.get(item.getType()) < n){
            System.out.println("Error: Your request cannot be completed atthis time.  Problem: the locker does not contain " + n + " items of type " + item.getType());
            return -1;
        }

        int new_quantity = items.get(item.getType()) - n;
        items.remove(item.getType());
        items.put(item.getType(), new_quantity);

        return 0;
    }

    /**
     * This method returns the amount of items of the requested type
     */
    public int getItemCount(String type){
        if (!items.containsKey(type)) return 0;
        return items.get(type);
    }

    /**
     * This method returns a map of all item types with there respective quanteties in the locker
     * @return an item of item types and their amount in the locker
     */
    public Map<String, Integer> getInventory(){
        return this.items;
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
        Iterator iter = items.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry pair = (Map.Entry)iter.next();
            int itemVolume = 0;
            switch (pair.getKey().toString()){
                case "baseball bat":
                    itemVolume = 2;
                    break;
                case "helmet, size 1":
                    itemVolume = 3;
                    break;
                case "helmet, size 3":
                    itemVolume = 5;
                    break;
                case "spores engine":
                    itemVolume = 10;
            }
            availableCapacity -= (int)pair.getValue() * itemVolume;
        }
        return availableCapacity;
    }
}
