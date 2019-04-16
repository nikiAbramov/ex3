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
        Assert.assertEquals(3997, hotelnCityByRating.length);
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
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(-90.01, 0));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(90.01, 0));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(0, -180.01));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(0, 180.01));

        testSite.getHotelsByProximity(45.0, 45.0);

        Hotel[] hotelsTESTING = testSite.getHotelsByProximity(0.0, 0.0);

        for (int i = 0; i < hotelsTESTING.length - 1; i++){
            Assert.assertFalse(testSite.GetDistanceFrom(hotelsTESTING[i], 0.0, 0.0) >
                    testSite.GetDistanceFrom(hotelsTESTING[i + 1], 0.0, 0.0));
        }

    }

    @Test
    public void testGetHotelsInCityByProximity(){
        Hotel[] hotelsTESTING = testSite.getHotelInCityByProximity("delhi", 28.0, 77.0);

        for (int i = 0; i < hotelsTESTING.length - 1; i++){
            Assert.assertFalse(testSite.GetDistanceFrom(hotelsTESTING[i], 28.0, 77.0) >
                    testSite.GetDistanceFrom(hotelsTESTING[i + 1], 28.0, 77.0));
        }
    }
}
