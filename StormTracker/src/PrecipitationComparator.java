import java.io.Serializable;
import java.util.Comparator;
/**
 * This class is used a comparator for the precipitation as the storm, including one method which compares the precipitation of
 * two storms. This class implements comparator as well as serializable
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #6
 */
public class PrecipitationComparator implements Comparator<Object>, Serializable {

    @Override
    /**
     * This method acts as a compare method for the two storms being compared and returns a int value depending on comparison
     * @param left
     * left object in the hashmap
     * @param right
     * right object in the hashmap
     * @return
     * Returns an int value of 0, 1, or -1 depending on whether they are equal, one is greater, or neither, respectively
     * Postcondition:
     * Precipitations are compared
     */
    public int compare(Object left, Object right) {
        Storm storm1 = (Storm) left;
        Storm storm2 = (Storm) right;

        if(storm1.getPrecipitation() == storm2.getPrecipitation())
            return 0;
        else if(storm1.getPrecipitation() > storm2.getPrecipitation())
            return 1;

        else return -1;
    }
}
