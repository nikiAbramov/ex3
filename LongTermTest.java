import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;

import java.util.HashMap;

public class LongTermTest {
    final int TEST_LOCKER_SIZE_DEFAULT = 100;
    final int BASEBALL_BAT_VOLUME = 2;
    final int HELMET_1_VOLUME = 3;
    final int HELMET_3_VOLUME = 5;
    final int SPORES_ENGINE_VOLUME = 10;
    final Item ITEM_BASEBALL_BAT = ItemFactory.createSingleItem("baseball bat");
    final Item ITEM_HELMET_1 = ItemFactory.createSingleItem("helmet, size 1");
    final Item ITEM_HELMET_3 = ItemFactory.createSingleItem("helmet, size 3");
    final Item ITEM_SPORES_ENGINE = ItemFactory.createSingleItem("spores engine");

    private LongTermStorage testStorage;
    @BeforeClass
    public static void setUpLongTermTest(){

    }

    @Before
    public void createLongTermStorage(){
        testStorage = new LongTermStorage();
    }

    @Ignore
    public void testGetInventory(){
        HashMap<String, Integer> testInventory = new HashMap<String, Integer>();
        Assert.assertEquals(testInventory, testStorage.getInventory());

        testStorage.addItem(ITEM_BASEBALL_BAT, 1);
        testStorage.resetInventory();
        Assert.assertEquals(testInventory, testStorage.getInventory());

        testStorage.addItem(ITEM_BASEBALL_BAT, 5);
        testStorage.addItem(ITEM_HELMET_1, 5);
        testInventory.put(ITEM_BASEBALL_BAT.getType(), 5);
        testInventory.put(ITEM_HELMET_1.getType(), 5);
        Assert.assertEquals(testInventory, testStorage.getInventory());

        testStorage.addItem(ITEM_HELMET_3, 5);
        testStorage.addItem(ITEM_SPORES_ENGINE, 5);
        testStorage.resetInventory();
        Assert.assertEquals(new HashMap<String, Integer>(), testStorage.getInventory());

    }


    @Ignore
    public void testGetCapacity(){
        Assert.assertEquals(1000, testStorage.getCapacity());

        testStorage.addItem(ITEM_BASEBALL_BAT, 5);
        testStorage.addItem(ITEM_HELMET_1, 10);
        testStorage.addItem(ITEM_HELMET_3, 4);
        testStorage.addItem(ITEM_SPORES_ENGINE, 5);
        Assert.assertEquals(900, testStorage.getCapacity());
    }

    @Test
    public void testAddItem(){
        Assert.assertEquals(-1, testStorage.addItem(ITEM_BASEBALL_BAT, -1));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_HELMET_1, -1));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_HELMET_3, -1));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_SPORES_ENGINE, -1));

        Assert.assertEquals(-1, testStorage.addItem(ITEM_BASEBALL_BAT, 501));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_HELMET_1, 334));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_HELMET_3, 201));
        Assert.assertEquals(-1, testStorage.addItem(ITEM_SPORES_ENGINE, 101));

        Assert.assertEquals(0, testStorage.addItem(ITEM_BASEBALL_BAT, 50));
        Assert.assertEquals(900, testStorage.getAvailableCapacity());

        Assert.assertEquals(0, testStorage.addItem(ITEM_HELMET_1, 100));
        Assert.assertEquals(600, testStorage.getAvailableCapacity());

        Assert.assertEquals(0, testStorage.addItem(ITEM_HELMET_3, 20));
        Assert.assertEquals(500, testStorage.getAvailableCapacity());

        Assert.assertEquals(0, testStorage.addItem(ITEM_SPORES_ENGINE, 10));
        Assert.assertEquals(400, testStorage.getAvailableCapacity());

        Assert.assertEquals(0, testStorage.addItem(ITEM_BASEBALL_BAT, 7));
        Assert.assertEquals(0, testStorage.addItem(ITEM_HELMET_1, 2));
        Assert.assertEquals(390, testStorage.getAvailableCapacity());
        testStorage.resetInventory();

        Assert.assertEquals(0, testStorage.addItem(ITEM_BASEBALL_BAT, 500));
        testStorage.resetInventory();

        Assert.assertEquals(0, testStorage.addItem(ITEM_HELMET_1, 333));
        testStorage.resetInventory();

        Assert.assertEquals(0, testStorage.addItem(ITEM_HELMET_3, 200));
        testStorage.resetInventory();

        Assert.assertEquals(0, testStorage.addItem(ITEM_SPORES_ENGINE, 100));
        testStorage.resetInventory();


    }
}
