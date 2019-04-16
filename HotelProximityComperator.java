import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class HotelProximityComperator implements Comparator<Hotel> {
    private double locationLatitude;
    private double locationLongitude;

    public int compare(Hotel hotel1, Hotel hotel2){
        double distanceHotel1;
        double distanceHotel2;

        distanceHotel1 = Math.pow((hotel1.getLatitude() - this.locationLatitude), 2) +
                Math.pow((hotel1.getLongitude() - this.locationLongitude), 2);
        distanceHotel1 = Math.sqrt(distanceHotel1);

        distanceHotel2 = Math.pow((hotel2.getLatitude() - this.locationLatitude), 2) +
                Math.pow((hotel2.getLongitude() - this.locationLongitude), 2);
        distanceHotel2 = Math.sqrt(distanceHotel2);

        // if hotel1 is closer, he is MORE close to the point, hence return 1.
        if (distanceHotel1 < distanceHotel2){
            return 1;
        }
        if (distanceHotel1 > distanceHotel2){
            return -1;
        }

        if (hotel1.getNumPOI() > hotel2.getNumPOI()){
            return 1;
        }

        // we were told in the forums in case the two hotels have the same distance and POIs the order doesn't matter.
        return -1;
    }

    /**
     * kept it in the default. meaning comperators are only equal if they have the same reference
     */
    public boolean equals(Object var1){
        if (this == var1)
            return true;
        return false;
    }

    public void setLocationLatitude(double locationLatitude) { this.locationLatitude = locationLatitude; }

    public void setLocationLongitude(double locationLongitude) { this.locationLongitude = locationLongitude; }
}
