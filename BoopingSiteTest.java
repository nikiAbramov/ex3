import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoopingSiteTest {
    private Boopingsite testSite;

    @Before
    public void init(){
        testSite = new Boopingsite("hotels_dataset.txt");
    }

    @Test
    public void getAllHotels(){
        Hotel[] hotelnCityByRating;
        hotelnCityByRating = testSite.getHotelsInCityByRating("gurgaon");
        Assert.assertEquals(100, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("goa");
        Assert.assertEquals(220, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("delhi");
        Assert.assertEquals(137, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("jaipur");
        Assert.assertEquals(70, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("manali");
        Assert.assertEquals(70, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("palakkad");
        Assert.assertEquals(5, hotelnCityByRating.length);
        hotelnCityByRating = testSite.getHotelsInCityByRating("guwahati");
        Assert.assertEquals(10, hotelnCityByRating.length);


        hotelnCityByRating = testSite.getHotelsByProximity(25, 25);
        Assert.assertEquals(100, hotelnCityByRating.length);
    }

    @Test
    public void testGetHotelsByCity(){
        Hotel[] hotelnCityByRating = testSite.getHotelsInCityByRating("gurgaon");
        for (int i = 0; i < hotelnCityByRating.length; i++){
            Assert.assertEquals("gurgaon", hotelnCityByRating[i].getCity());
            if (i < hotelnCityByRating.length - 1) {
                Assert.assertFalse(hotelnCityByRating[i].getStarRating() < hotelnCityByRating[i + 1].getStarRating());
                if (hotelnCityByRating[i].getStarRating() == hotelnCityByRating[i + 1].getStarRating()){
                    Assert.assertFalse(0 < hotelnCityByRating[i].getPropertyName().compareTo(hotelnCityByRating[i + 1].getPropertyName()));
                }
            }
        }

        hotelnCityByRating = testSite.getHotelsInCityByRating("goa");
        hotelnCityByRating = testSite.getHotelsInCityByRating("delhi");
        hotelnCityByRating = testSite.getHotelsInCityByRating("jaipur");
        hotelnCityByRating = testSite.getHotelsInCityByRating("manali");
        hotelnCityByRating = testSite.getHotelsInCityByRating("palakkad");
        hotelnCityByRating = testSite.getHotelsInCityByRating("guwahati");

    }

    @Test
    public void testGetHotelsByProximity(){

    }

    @Test
    public void testGetHotelsInCityByProximity(){

    }
}
