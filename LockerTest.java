import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;

import java.util.HashMap;

public class LockerTest {
    private Locker testLocker;
    private static ItemFactory itemFactory;

    final int TEST_LOCKER_SIZE_DEFAULT = 100;
    final int BASEBALL_BAT_VOLUME = 2;
    final int HELMET_1_VOLUME = 3;
    final int HELMET_3_VOLUME = 5;
    final int SPORES_ENGINE_VOLUME = 10;
    final Item ITEM_BASEBALL_BAT = itemFactory.createSingleItem("baseball bat");
    final Item ITEM_HELMET_1 = itemFactory.createSingleItem("helmet, size 1");
    final Item ITEM_HELMET_3 = itemFactory.createSingleItem("helmet, size 3");
    final Item ITEM_SPORES_ENGINE = itemFactory.createSingleItem("spores engine");

    @BeforeClass
    public static void createItemFactory(){
        itemFactory = new ItemFactory();
    }

    @Before
    public void createTestLocker(){
        testLocker = new Locker(TEST_LOCKER_SIZE_DEFAULT);
    }

    @Test
    public void testGetCapacity(){
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT, testLocker.getCapacity());
        testLocker = new Locker(100);
        Assert.assertEquals(100, testLocker.getCapacity());
        testLocker = new Locker(0);
        Assert.assertEquals(0, testLocker.getCapacity());
    }

    @Ignore
    public void testGetAvailableCapacity(){
        int expectedAvailabeCapacity = TEST_LOCKER_SIZE_DEFAULT;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_BASEBALL_BAT, 2);
        expectedAvailabeCapacity -= 2 * BASEBALL_BAT_VOLUME;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_1, 1);
        expectedAvailabeCapacity -= HELMET_1_VOLUME;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_3, 3);
        expectedAvailabeCapacity -= 3 * HELMET_3_VOLUME;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_SPORES_ENGINE, 2);
        expectedAvailabeCapacity -= 2 * SPORES_ENGINE_VOLUME;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.removeItem(ITEM_BASEBALL_BAT, 1);
        expectedAvailabeCapacity += BASEBALL_BAT_VOLUME;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.removeItem(ITEM_HELMET_3, 3);
        expectedAvailabeCapacity += 3 * HELMET_3_VOLUME;

        testLocker.removeItem(ITEM_BASEBALL_BAT, 1);
        testLocker.removeItem(ITEM_HELMET_1, 1);
        testLocker.removeItem(ITEM_SPORES_ENGINE, 2);
        expectedAvailabeCapacity = TEST_LOCKER_SIZE_DEFAULT;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.removeItem(ITEM_BASEBALL_BAT, 1);
        testLocker.removeItem(ITEM_HELMET_1, 1);
        testLocker.removeItem(ITEM_SPORES_ENGINE, 1);
        expectedAvailabeCapacity = TEST_LOCKER_SIZE_DEFAULT;
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_BASEBALL_BAT, 30);
        expectedAvailabeCapacity = (int)(TEST_LOCKER_SIZE_DEFAULT * 0.2);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_3, 12);
        expectedAvailabeCapacity = (int)(TEST_LOCKER_SIZE_DEFAULT * 0.4);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_1, 17);
        expectedAvailabeCapacity -= 18; // since 7 helmets will take 21 volume, we store only 6 (18 volume)
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());
    }

    @Test
    public void testRemoveItem(){

        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));

        testLocker.addItem(ITEM_BASEBALL_BAT, 5);
        testLocker.addItem(ITEM_HELMET_1, 3);
        testLocker.addItem(ITEM_HELMET_3, 2);
        testLocker.addItem(ITEM_SPORES_ENGINE, 1);

        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, 6));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 4));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_3, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_SPORES_ENGINE, 2));


        Assert.assertEquals(0, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(0, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(0, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));

        Assert.assertEquals(0, testLocker.removeItem(ITEM_HELMET_3, 2));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_3, 1));


        Assert.assertEquals(0, testLocker.removeItem(ITEM_BASEBALL_BAT, 2));
        Assert.assertEquals(0, testLocker.removeItem(ITEM_BASEBALL_BAT, 3));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, 1));


        Assert.assertEquals(-1, testLocker.removeItem(ITEM_SPORES_ENGINE, 2));
        Assert.assertEquals(0, testLocker.removeItem(ITEM_SPORES_ENGINE, 1));
    }

    @Test
    public void testAddItem(){
        Assert.assertEquals(-1, testLocker.addItem(ITEM_BASEBALL_BAT, 51));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_1, 34));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_3, 21));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_SPORES_ENGINE, 11));

        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_1, 10));
        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_3, 6));
        Assert.assertEquals(0, testLocker.addItem(ITEM_SPORES_ENGINE, 3));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_BASEBALL_BAT, 6));

        testLocker.removeItem(ITEM_HELMET_1, 10);
        testLocker.removeItem(ITEM_HELMET_3, 6);
        Assert.assertEquals(1, testLocker.addItem(ITEM_SPORES_ENGINE, 3));
        Assert.assertEquals(1, testLocker.addItem(ITEM_HELMET_3, 12));
        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_1, 10));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_1, 11));

    }

    @Test
    public void testItemCount(){

        Assert.assertEquals(0, testLocker.getItemCount(ITEM_BASEBALL_BAT.getType()));
        Assert.assertEquals(0, testLocker.getItemCount(ITEM_HELMET_1.getType()));
        Assert.assertEquals(0, testLocker.getItemCount(ITEM_HELMET_3.getType()));
        Assert.assertEquals(0, testLocker.getItemCount(ITEM_SPORES_ENGINE.getType()));

        testLocker.addItem(ITEM_BASEBALL_BAT, 2);
        testLocker.addItem(ITEM_HELMET_1, 3);
        testLocker.addItem(ITEM_HELMET_3, 4);
        testLocker.addItem(ITEM_SPORES_ENGINE, 1);

        Assert.assertEquals(2, testLocker.getItemCount(ITEM_BASEBALL_BAT.getType()));
        Assert.assertEquals(3, testLocker.getItemCount(ITEM_HELMET_1.getType()));
        Assert.assertEquals(4, testLocker.getItemCount(ITEM_HELMET_3.getType()));
        Assert.assertEquals(1, testLocker.getItemCount(ITEM_SPORES_ENGINE.getType()));
    }

    @Test
    public void testGetInventory(){
        HashMap<String, Integer> testMap = new HashMap<String, Integer>();
        testMap.put("helmet, size 1", 5);
        testMap.put("baseball bat", 10);
        testMap.put("spores engine", 1);
        testMap.put("helmet, size 3", 1);
        testLocker.addItem(ITEM_BASEBALL_BAT, 10);
        testLocker.addItem(ITEM_HELMET_1, 5);
        testLocker.addItem(ITEM_HELMET_3, 1);
        testLocker.addItem(ITEM_SPORES_ENGINE, 1);

        Assert.assertEquals(testMap, testLocker.getInventory());
    }
}
