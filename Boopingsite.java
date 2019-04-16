import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boopingsite {
    private Hotel[] hotels;
    private HotelRatingComperator hotelRatingComperator;
    private HotelProximityComperator hotelProximityComperator;
    public Boopingsite(String name){

        hotels = HotelDataset.getHotels(name);
        hotelRatingComperator = new HotelRatingComperator();
        hotelProximityComperator = new HotelProximityComperator();
    }

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

    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        if (latitude < -90 || latitude > 90 || longitude > 180 || longitude < -180)
            return new Hotel[0];

        List<Hotel> hotelsByProx = new ArrayList<Hotel>();
        for (int i = 0; i < hotels.length; i++) {
            hotelsByProx.add(hotels[i]);
        }

        hotelsByProx.sort(hotelProximityComperator);
        Collections.reverse(hotelsByProx);

        return hotelsByProx.toArray(Hotel[]::new);
    }

    public Hotel[] getHotelInCityByProximity(String city, double latitude, double longitude){
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

    /**
     * DELETE THIS IMMIDIATLY; MADE FOR TESTING SOME VALUES;
     */
    public double GetDistanceFrom(Hotel hotel, double latitude, double longitude){
        double distanceHotel = Math.pow((hotel.getLatitude() - latitude), 2) +
                Math.pow((hotel.getLongitude() - longitude), 2);
        distanceHotel = Math.sqrt(distanceHotel);
        return distanceHotel;
    }
}
