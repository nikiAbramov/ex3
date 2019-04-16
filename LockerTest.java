import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;

/**
 * Tests class for the locker
 */
public class LockerTest {
    private Locker testLocker;
    private Locker testLockerExtra;

    final int TEST_LOCKER_SIZE_DEFAULT = 100;
    final int BASEBALL_BAT_VOLUME = 2;
    final int HELMET_1_VOLUME = 3;
    final int HELMET_3_VOLUME = 5;
    final int SPORES_ENGINE_VOLUME = 10;
    final Item ITEM_BASEBALL_BAT = ItemFactory.createSingleItem("baseball bat");
    final Item ITEM_HELMET_1 = ItemFactory.createSingleItem("helmet, size 1");
    final Item ITEM_HELMET_3 = ItemFactory.createSingleItem("helmet, size 3");
    final Item ITEM_SPORES_ENGINE = ItemFactory.createSingleItem("spores engine");
    final Item ITEM_FOOTBALL = ItemFactory.createSingleItem("football");

    @Before
    public void createTestLocker() { testLocker = new Locker(TEST_LOCKER_SIZE_DEFAULT); }

    @Test
    public void testGetCapacity(){
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT, testLocker.getCapacity());
        testLocker = new Locker(1000);
        Assert.assertEquals(1000, testLocker.getCapacity());
        testLocker = new Locker(0);
        Assert.assertEquals(0, testLocker.getCapacity());
    }

    @Test
    public void testGetAvailableCapacity(){
        int expectedAvailabeCapacity = TEST_LOCKER_SIZE_DEFAULT;
        /**
         * In the next series of command lines we check that if we add items in small quantities it returns expected
         * values
         */
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

        /**
         * In the next series of command lines we check that if we remove items in small quantities it returns expected
         * values, also what if we remove below the limit. meaning we remove items when there are no items of that sort
         */
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
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        /**
         * The next asserts check that items are not added through the addItems method, meaning that the actual
         * available capacity is shown.
         */
        testLocker.addItem(ITEM_SPORES_ENGINE, 11);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());
        testLocker.addItem(ITEM_BASEBALL_BAT, 51);
        testLocker.addItem(ITEM_HELMET_1, 34);
        testLocker.addItem(ITEM_HELMET_3, 21);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());


        /**
         * The next series of commands checks what happens when we put over 50% of a certain amount and that the
         * getAvailable capacity does return expected (reduced after item transfer) amount.
         */
        testLocker.addItem(ITEM_BASEBALL_BAT, 30);
        expectedAvailabeCapacity = (int)(TEST_LOCKER_SIZE_DEFAULT * 0.8);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_3, 12);
        expectedAvailabeCapacity = (int)(TEST_LOCKER_SIZE_DEFAULT * 0.6);
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        testLocker.addItem(ITEM_HELMET_1, 17);
        expectedAvailabeCapacity -= 18; // since 7 helmets will take 21 volume, we store only 6 (18 volume)
        Assert.assertEquals(expectedAvailabeCapacity, testLocker.getAvailableCapacity());

        /**
         * The next asserts check stuff related to football-baseball bat interactions. added after the surprise section.
         */
        testLocker = new Locker(TEST_LOCKER_SIZE_DEFAULT);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT, testLocker.getAvailableCapacity());
        testLocker.addItem(ITEM_BASEBALL_BAT, 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - ITEM_BASEBALL_BAT.getVolume(),
                testLocker.getAvailableCapacity());
        testLocker.addItem(ITEM_FOOTBALL, 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - ITEM_BASEBALL_BAT.getVolume(),
                testLocker.getAvailableCapacity());
        testLocker.removeItem(ITEM_BASEBALL_BAT, 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT, testLocker.getAvailableCapacity());
        testLocker.addItem(ITEM_FOOTBALL, 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - ITEM_FOOTBALL.getVolume(),
                testLocker.getAvailableCapacity());
        testLocker.addItem(ITEM_BASEBALL_BAT, 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - ITEM_FOOTBALL.getVolume(),
                testLocker.getAvailableCapacity());

    }

    @Test
    public void testRemoveItem(){

        /**
         * test that check that both removing negative number of items and removing items that aren't there result in
         * -1, failure to remove.
         */
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 1));

        /**
         * next test checks that removing negetive numbers and removing more items then there actually is result in -1,
         * a bit similar to last assert series but this time with some items in the lockers
         */
        testLocker.addItem(ITEM_BASEBALL_BAT, 5);
        testLocker.addItem(ITEM_HELMET_1, 3);
        testLocker.addItem(ITEM_HELMET_3, 2);
        testLocker.addItem(ITEM_SPORES_ENGINE, 1);

        Assert.assertEquals(-1, testLocker.removeItem(ITEM_BASEBALL_BAT, 6));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_1, 4));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_HELMET_3, -1));
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_SPORES_ENGINE, 2));

        /**
         * next test check successful series of removes, beside the last one which is removing an item when there are no
         * items reutrns -1
         */
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

        /**
         * added after surprise
         */
        testLockerExtra = new Locker(10);
        testLocker.addItem(ITEM_BASEBALL_BAT, 1);
        testLocker.addItem(ITEM_FOOTBALL, 1);
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_FOOTBALL, 1));
        testLockerExtra.addItem(ITEM_FOOTBALL, 1);
        Assert.assertEquals(-1, testLocker.removeItem(ITEM_FOOTBALL, 1));
        Assert.assertEquals(0, testLockerExtra.removeItem(ITEM_FOOTBALL, 1));

    }

    @Test
    public void testAddItem(){
        /**
         * adding more then max capacity should'nt add any items.
         */
        Assert.assertEquals(-1, testLocker.addItem(ITEM_BASEBALL_BAT, 51));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_1, 34));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_3, 21));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_SPORES_ENGINE, 11));

        /**
         * next asserts that adding items returns 0 if they are added normally, and there is no 50% of the max capacity
         * of any type of item. but adding items which makes the locker go over the limit returns -1.
         */
        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_1, 10));
        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_3, 6));
        Assert.assertEquals(0, testLocker.addItem(ITEM_SPORES_ENGINE, 3));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_BASEBALL_BAT, 6));

        /**
         * next series of asserts checks the 'over 50% of a certain type' function. we remove all items beside the
         * engines which take about 30% of space, add 30% more making it go over 50% -> and drop to 20%.
         * Then add helmets that take over 50% -> droping them too to 20%. those actions should return 1.
         */
        testLocker.removeItem(ITEM_HELMET_1, 10);
        testLocker.removeItem(ITEM_HELMET_3, 6);
        Assert.assertEquals(1, testLocker.addItem(ITEM_SPORES_ENGINE, 3));
        Assert.assertEquals(1, testLocker.addItem(ITEM_HELMET_3, 12));
        Assert.assertEquals(0, testLocker.addItem(ITEM_HELMET_1, 10));
        Assert.assertEquals(-1, testLocker.addItem(ITEM_HELMET_1, 11));

        /**
         * next asserts where added after the surprise. they check both baseball-football cases and that adding items
         * shouldn't effect different lockers.
         */
        testLockerExtra = new Locker(TEST_LOCKER_SIZE_DEFAULT);
        testLocker = new Locker(TEST_LOCKER_SIZE_DEFAULT);
        Assert.assertEquals(0, testLockerExtra.addItem(ITEM_BASEBALL_BAT, 1));
        Assert.assertEquals(-2, testLockerExtra.addItem(ITEM_FOOTBALL, 1));
        Assert.assertEquals(0, testLocker.addItem(ITEM_FOOTBALL, 1));
        Assert.assertEquals(-2, testLocker.addItem(ITEM_BASEBALL_BAT, 1));
        Assert.assertEquals(0, testLocker.removeItem(ITEM_FOOTBALL, 1));
        Assert.assertEquals(0, testLockerExtra.removeItem(ITEM_BASEBALL_BAT, 1));

        Assert.assertEquals(0, testLocker.addItem(ITEM_BASEBALL_BAT, 20));
        Assert.assertEquals(0, testLockerExtra.addItem(ITEM_BASEBALL_BAT, 20));
        Assert.assertEquals(1, testLocker.addItem(ITEM_BASEBALL_BAT, 20));

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

    @Test
    public void testAddItemContradicting(){
        testLocker.addItem(ITEM_BASEBALL_BAT, 1);
        Assert.assertEquals(-2, testLocker.addItem(ITEM_FOOTBALL, 1));

        testLocker.removeItem(ITEM_BASEBALL_BAT, 1);
        Assert.assertEquals(0, testLocker.addItem(ITEM_FOOTBALL, 1));
        Assert.assertEquals(-2, testLocker.addItem(ITEM_BASEBALL_BAT, 1));
    }

    @AfterClass
    public static void testFullLongTermStorage(){
        /**
         * this test done after all since it effects the long term storage, which we can not reset from locker API.
         * so I made it AfterClass
         */
        Item ITEM_SPORES_ENGINE = ItemFactory.createSingleItem("spores engine");

        Locker testLocker = new Locker(100);
        Assert.assertEquals(0, testLocker.addItem(ITEM_SPORES_ENGINE, 2));
        for (int i = 0; i < 9; i++){
            Assert.assertEquals(1, testLocker.addItem(ITEM_SPORES_ENGINE, 8));
        }
        Assert.assertEquals(-1, testLocker.addItem(ITEM_SPORES_ENGINE, 4));
        Assert.assertEquals(0, testLocker.addItem(ITEM_SPORES_ENGINE, 3));
    }
}
