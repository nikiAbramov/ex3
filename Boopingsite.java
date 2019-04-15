import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boopingsite {
    private Hotel[] hotels;
    private HotelRatingComperator hotelRatingComperator;
    public Boopingsite(String name){

        hotels = HotelDataset.getHotels(name);
        hotelRatingComperator = new HotelRatingComperator();
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
        return null;
    }

    public Hotel[] getHotelInCityByProximity(String city, double latitude, double longitude){
        return null;
    }
}
