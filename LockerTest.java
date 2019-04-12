import junit.framework.Assert;
import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;

public class LockerTest {
    private Locker testLocker;
    private static ItemFactory itemFactory;

    final int TEST_LOCKER_SIZE_1 = 20;

    @BeforeClass
    public static void createItemFactory(){
        itemFactory = new ItemFactory();
    }

    @Before
    public void createTestLocker(){
        testLocker = new Locker(TEST_LOCKER_SIZE_1);
        Item item = itemFactory.createSingleItem("baseball bat");
        testLocker.addItem(item, 2);
    }

    @Test
    public void testGetCapacity(){
        Assert.assertEquals("didn't get expected capacity",TEST_LOCKER_SIZE_1, testLocker.getCapacity());
    }

    @Test
    public void testGetAvailableCapacity(){
        Assert.assertEquals(TEST_LOCKER_SIZE_1 - 4, testLocker.getAvailableCapacity());
        testLocker.addItem(itemFactory.createSingleItem("baseball bat"), 2);
        Assert.assertEquals(TEST_LOCKER_SIZE_1 - 8, testLocker.getAvailableCapacity());
    }

    @Ignore
    public void testAddItem(){
        Item item = itemFactory.createSingleItem("baseball bat");
        Assert.assertEquals("addItem didn't react as expected",0, testLocker.addItem(item, 1));
        Assert.assertEquals("addItem didn't react as expected",0, testLocker.addItem(item, 1));
        Assert.assertEquals("addItem didn't react as expected",-1, testLocker.addItem(item, 9));
    }
}
