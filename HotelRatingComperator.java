import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class HotelRatingComperator implements Comparator<Hotel> {
    /**
     * Comperator for Hotel object. Compares Hotels depending on their rating.
     */

    /**
     * Compares two hotels based on thier rating. if the first parameter has higher rating then the second one, it is
     * considered greater then the second. if both have the same rating, the greater parameter will be the hotel who
     * comes first in the alphabetic order
     * @param hotel1 The first parameter Hotel
     * @param hotel2 The second parameter Hotel
     * @return 1 if the first parameter is greater, 0 if both parameters are equal, and -1 if the second one is greater
     */
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
