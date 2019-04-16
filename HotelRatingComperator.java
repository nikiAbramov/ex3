import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class HotelRatingComperator implements Comparator<Hotel> {
    public int compare(Hotel hotel1, Hotel hotel2){
        if (hotel1.getStarRating() > hotel2.getStarRating()){
            return 1;
        }
        else if (hotel1.getStarRating() < hotel2.getStarRating()){
            return -1;
        }

        if (0 > hotel1.getPropertyName().compareTo(hotel2.getPropertyName())){
            return 1;
        }
        if (0 < hotel1.getPropertyName().compareTo(hotel2.getPropertyName())){
            return -1;
        }

        return 0;
    }

    /**
     * kept it in the default. meaning comperators are only equal if they have the same reference
     */
    public boolean equals(Object var1){
        if (this == var1)
            return true;
        return false;
    }
}
