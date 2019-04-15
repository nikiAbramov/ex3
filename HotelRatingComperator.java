import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class HotelRatingComperator implements Comparator<Hotel> {
    public int compare(Hotel var1, Hotel var2){
        if (var1.getStarRating() > var2.getStarRating()){
            return 1;
        }
        else if (var1.getStarRating() < var2.getStarRating()){
            return -1;
        }

        if (0 > var1.getPropertyName().compareTo(var2.getPropertyName())){
            return 1;
        }
        if (0 < var1.getPropertyName().compareTo(var2.getPropertyName())){
            return -1;
        }

        return 0;
    }

    /**
     * TODO: not sure if i even want to implement this as its unnessisery for the excercise. ask TA. for
     * TODO: now keep it default
     * @param var1
     * @return
     */
    public boolean equals(Object var1){
        if (this == var1)
            return true;
        return false;
    }
}
