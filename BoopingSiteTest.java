import oop.ex3.searchengine.Hotel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoopingSiteTest {
    private BoopingSite testSite;
    private Hotel[] testHotels;

    final double MUMBI_MAHARASHTRA_LATITUDE = 19.076090;
    final double MUMBI_MAHARASHTRA_LONGITUDE = 72.877426;

    final double CHIKKABALLAPURA_KARNATAKA_LATITUDE = 13.432515;
    final double CHIKKABALLAPURA_KARNATAKA_LONGITUDE = 77.727478;

    final double DELHI_LATITUDE = 28.610001;
    final double DELHI_LONGITUDE = 77.230003;

    final double ASHDOD_LATITUDE = 31.801447;
    final double ASHDOD_LONGITUDE = 34.643497;

    final double HOUSTON_TEXAS_LATITUDE = 31.801447;
    final double HOUSTON_TEXAS_LONGITUDE = -95.358421;


    @Before
    public void init(){
        testSite = new BoopingSite("hotels_dataset.txt");
    }

    @Test
    public void testGetHotelsInCityByRating(){
        /**
         * those tests checks that when we ask for hotelsInCityByRating it returns the currect number of cities,
         * represented in an array at the currect length. after that it checks that the list is indeed in the currect
         * order, meaning the first elements (hotels) have higher ranking, and hotels with same rating appear in
         * alphabetic order.
         */
        testHotels = testSite.getHotelsInCityByRating("gurgaon");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("goa");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("delhi");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("jaipur");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("manali");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("palakkad");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("guwahati");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("pune");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("alleppey");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("dalhousie");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("sawai madhopur");
        Assert.assertEquals(true, isAllSameCity(testHotels));
        Assert.assertEquals(true, isSortedByRating(testHotels));

        testHotels = testSite.getHotelsInCityByRating("chitrasani");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("chennai");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("noida");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelsInCityByRating("Gotham city");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(0, testHotels.length); // fictional city, not existing in rl

        testHotels = testSite.getHotelsInCityByRating("Cloud City from star wars");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(0, testHotels.length); // fictional city, not existing in rl

        testHotels = testSite.getHotelsInCityByRating("underwater city of rapture");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(0, testHotels.length); // fictional city, not existing in rl

        testHotels = testSite.getHotelsInCityByRating("Sigil, center of the planescape");
        Assert.assertEquals(true, isSortedByRating(testHotels));
        Assert.assertEquals(0, testHotels.length); // fictional city, not existing in rl
    }

    @Test
    public void testGetHotelsByProximity(){
        /**
         * the next asserts check for illegal values, latitude showed be in the range of -90 <= lat <= 90
         * and longitude should be between -180 and 180
         */
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(-90.01, 0));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(90.01, 0));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(0, -180.01));
        Assert.assertEquals(new Hotel[0], testSite.getHotelsByProximity(0, 180.01));

        /**
         * the next series of test checks that the array that comes from BoopingSite is indeed sorted by proximity.
         */
        testHotels = testSite.getHotelsByProximity(DELHI_LATITUDE, DELHI_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, DELHI_LATITUDE, DELHI_LONGITUDE));

        testHotels = testSite.getHotelsByProximity(CHIKKABALLAPURA_KARNATAKA_LATITUDE,
                CHIKKABALLAPURA_KARNATAKA_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, CHIKKABALLAPURA_KARNATAKA_LATITUDE,
                CHIKKABALLAPURA_KARNATAKA_LONGITUDE));

        testHotels = testSite.getHotelsByProximity(ASHDOD_LATITUDE, ASHDOD_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, ASHDOD_LATITUDE, ASHDOD_LONGITUDE));

        testHotels = testSite.getHotelsByProximity(MUMBI_MAHARASHTRA_LATITUDE, MUMBI_MAHARASHTRA_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, MUMBI_MAHARASHTRA_LATITUDE,
                MUMBI_MAHARASHTRA_LONGITUDE));

        testHotels = testSite.getHotelsByProximity(HOUSTON_TEXAS_LATITUDE, HOUSTON_TEXAS_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, HOUSTON_TEXAS_LATITUDE,
                HOUSTON_TEXAS_LONGITUDE));

        /**
         * the next assert checks we give the getHotelsByProximity method the wrong coordinates, the array shouldn't
         * be sorted in this case.
         */
        testHotels = testSite.getHotelsByProximity(HOUSTON_TEXAS_LATITUDE, HOUSTON_TEXAS_LONGITUDE);
        Assert.assertEquals(false, isSortedByProximity(testHotels, DELHI_LATITUDE,
                DELHI_LATITUDE));
    }

    @Test
    public void testGetHotelsInCityByProximity(){
        /**
         * this test checks a similar function like the regular getHoteByProximity, this time it also checks
         * all the hotels are in the same city too.
         */
        testHotels = testSite.getHotelInCityByProximity("gurgaon", MUMBI_MAHARASHTRA_LATITUDE,
                MUMBI_MAHARASHTRA_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, MUMBI_MAHARASHTRA_LATITUDE,
                MUMBI_MAHARASHTRA_LONGITUDE));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelInCityByProximity("goa", DELHI_LATITUDE, DELHI_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, DELHI_LATITUDE, DELHI_LONGITUDE));
        Assert.assertEquals(true, isAllSameCity(testHotels));

        testHotels = testSite.getHotelInCityByProximity("delhi", ASHDOD_LATITUDE, ASHDOD_LONGITUDE);
        Assert.assertEquals(true, isSortedByProximity(testHotels, ASHDOD_LATITUDE, ASHDOD_LONGITUDE));
        Assert.assertEquals(true, isAllSameCity(testHotels));
    }

    /**
     * This method checks if the hotel array sorted according to the proximity to the requested point.
     * meaning that hotels that are closer to the latitude-longitude point come earlier in the array then those who are
     * farther away. also, if two hotels are at the same distance, the one that has more POI comes earlier.
     * @param hotelArray the array that is to-be-checked
     * @param latitude the latitude of the requested point
     * @param longitude the longitude of the requested point
     * @return true if the array sorted in that way, false otherwise
     */
    private boolean isSortedByProximity(Hotel[] hotelArray, double latitude, double longitude){
        double proximityCurrent;
        double proximityNext = 999; // java asked me to initialize it in order to make use of it in the loop. value not importent.
        for (int i = 0; i < hotelArray.length - 1; i++){
            if (i == 0) proximityCurrent = getDistance(hotelArray[i], latitude, longitude);
            else proximityCurrent = proximityNext;
            proximityNext = getDistance(hotelArray[i + 1], latitude, longitude);
            if (proximityCurrent > proximityNext)
                return false;
            if (proximityCurrent == proximityNext){
                if (hotelArray[i].getNumPOI() < hotelArray[i+1].getNumPOI())
                    return false;
            }
        }

        return true;
    }

    /**
     * this function checks if the order of the array is so that if an element comes earlier in the array, its rating
     * will be higher or equal, hotels with same rating are then sorted by alphabetic order
     * @param hotelArray the array that is checked
     * @return false if the array is not sorted in that way, true if it does.
     */
    private boolean isSortedByRating(Hotel[] hotelArray){
        for (int i = 0; i < hotelArray.length - 1; i++){
            if (hotelArray[i].getStarRating() < hotelArray[i+1].getStarRating())
                return false;
            if (hotelArray[i].getStarRating() == hotelArray[i+1].getStarRating())
            {
                if (0 < hotelArray[i].getPropertyName().compareTo(hotelArray[i+1].getPropertyName()))
                    return false;
            }
        }

        return true;
    }

    /**
     * checks if all the hotels in the array have the same city.
     * @param hotelArray the array that is checked
     * @return false if one of the elements / hotels has different city. true if they all have the same city.
     */
    private boolean isAllSameCity(Hotel[] hotelArray){
        for (int i = 0; i < hotelArray.length - 1; i++){
            if (hotelArray[i].getCity().compareTo(hotelArray[i+1].getCity()) != 0)
                return false;
        }

        return true;
    }

    /**
     * This method checks the proximity of the hotel from the given point.
     */
    private double getDistance(Hotel hotel, double latitude, double longitude){
        double dist = Math.pow(hotel.getLatitude() - latitude, 2) + Math.pow((hotel.getLongitude() - longitude), 2);
        dist = Math.sqrt(dist);
        return dist;
    }
}
