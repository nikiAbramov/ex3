import org.junit.*;

public class LongTermTest {
    private LongTermStorage testStorage;
    @BeforeClass
    public static void setUpLongTermTest(){

    }

    @Before
    public void createLongTermStorage(){
        testStorage = new LongTermStorage();
    }

    @Test
    public void testGetCapacity(){
        Assert.assertEquals(0, testStorage.getCapacity());
    }
}
