import oop.ex3.spaceship.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Locker {
    private Integer capacity;
    private HashMap<String, Integer> items;

    public Locker(int capacity){
        this.capacity = capacity;
        this.items = new HashMap<String, Integer>();
    }

    public int addItem(Item item, int n){
        if (items.containsKey(item.getType())){
            int currentInventory = items.get(item.getType());
            currentInventory += n;
            items.put(item.getType(), currentInventory);
        }
        else{
            items.put(item.getType(), n);
        }
        return 0;
    }

    public int getCapacity() { return this.capacity; }

    public int getAvailableCapacity(){
        int availableCapacity = this.capacity;
        Iterator iter = items.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry pair = (Map.Entry)iter.next();
            availableCapacity -= (int)pair.getValue() * 2;
        }
        return availableCapacity;
    }
}
