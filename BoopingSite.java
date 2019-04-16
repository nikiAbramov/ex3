import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoopingSite {
    private Hotel[] hotels;
    private HotelRatingComperator hotelRatingComperator;
    private HotelProximityComperator hotelProximityComperator;

    /**
     * The constructor for the BoopingSit object. it gets the hotels from a file, and makes to comperators which
     * its methods use.
     * @param name the name of the file from which it gets the dataset of hotels
     */
    public BoopingSite(String name){
        hotels = HotelDataset.getHotels(name);
        hotelRatingComperator = new HotelRatingComperator();
        hotelProximityComperator = new HotelProximityComperator();
    }

    /**
     * This method gets a city name, and returns an Hotel[] array that is sorted by the rating of the hotels.
     * Hotels with good rating come first, if two hotels have the same rating, those two will be sorted alphabeticly
     * instead.
     * @param city The city about which we get the hotel data
     * @return a sorted array of hotels
     */
    public Hotel[] getHotelsInCityByRating(String city){
        ArrayList<Hotel> hotelsInCity = new ArrayList<Hotel>();
        for(int i = 0; i < hotels.length; i++){
            if (hotels[i].getCity().equals(city))
                hotelsInCity.add(hotels[i]);
        }

        hotelsInCity.sort(hotelRatingComperator);
        Collections.reverse(hotelsInCity);

        Hotel[] hotelsInCityByRating = hotelsInCity.toArray(Hotel[]::new);

        return hotelsInCityByRating;
    }

    /**
     * This method gets a latitude and longitude values, then returns an array with hotels sorted by proximity to the
     * given point. hotels that are closer to the point will come first. If two hotels have the same proximity, those
     * two will be sorted according to their points of interst instead, meaning the one with more POI will come first.
     * @param latitude the latitude of the requested point
     * @param longitude the longitude of the requested point
     * @return a sorted array of hotels
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        if (latitude < -90 || latitude > 90 || longitude > 180 || longitude < -180)
            return new Hotel[0];

        List<Hotel> hotelsByProx = new ArrayList<Hotel>();
        for (int i = 0; i < hotels.length; i++) {
            hotelsByProx.add(hotels[i]);
        }

        hotelProximityComperator.setLocationLatitude(latitude);
        hotelProximityComperator.setLocationLongitude(longitude);
        hotelsByProx.sort(hotelProximityComperator);
        Collections.reverse(hotelsByProx);

        return hotelsByProx.toArray(Hotel[]::new);
    }

    /**
     * Similar to getHotelsByProximity, but now it only sorts and returns array of hotels which are in the same city
     * @param city the city from which the hotels are checked
     * @param latitude the latitude of the requested point
     * @param longitude the longitude of the requested point
     * @return a sorted array of hotels
     */
    public Hotel[] getHotelInCityByProximity(String city, double latitude, double longitude){
        if (latitude < -90 || latitude > 90 || longitude > 180 || longitude < -180)
            return new Hotel[0];

        Hotel[] hotelsInCityByProx = getHotelsInCityByRating(city);
        hotelProximityComperator.setLocationLatitude(latitude);
        hotelProximityComperator.setLocationLongitude(longitude);

        List<Hotel> hotelsByProx = new ArrayList<Hotel>();
        for (int i = 0; i < hotelsInCityByProx.length; i++) {
            hotelsByProx.add(hotelsInCityByProx[i]);
        }

        hotelsByProx.sort(hotelProximityComperator);
        Collections.reverse(hotelsByProx);

        return hotelsByProx.toArray(Hotel[]::new);
    }
}
