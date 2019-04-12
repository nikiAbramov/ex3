import junit.framework.Assert;
import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;

public class LockerTest {
    private Locker testLocker;
    private static ItemFactory itemFactory;

    final int TEST_LOCKER_SIZE_DEFAULT = 100;

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

    @Test
    public void testGetAvailableCapacity(){
        //Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT, testLocker.getAvailableCapacity());
        testLocker.addItem(itemFactory.createSingleItem("baseball bat"), 2);
        //Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - 4, testLocker.getAvailableCapacity());
        testLocker.addItem(itemFactory.createSingleItem("helmet, size 1"), 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - 7, testLocker.getAvailableCapacity());
        testLocker.addItem(itemFactory.createSingleItem("helmet, size 3"), 2);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - 17, testLocker.getAvailableCapacity());
        testLocker.addItem(itemFactory.createSingleItem("spores engine"), 2);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - 37, testLocker.getAvailableCapacity());
        testLocker.removeItem(itemFactory.createSingleItem("baseball bat"), 1);
        Assert.assertEquals(TEST_LOCKER_SIZE_DEFAULT - 35, testLocker.getAvailableCapacity());
    }

    @Ignore
    public void testAddItem(){
        Item item = itemFactory.createSingleItem("baseball bat");
        Assert.assertEquals("addItem didn't react as expected",0, testLocker.addItem(item, 1));
        Assert.assertEquals("addItem didn't react as expected",0, testLocker.addItem(item, 1));
        Assert.assertEquals("addItem didn't react as expected",-1, testLocker.addItem(item, 9));
    }
}
