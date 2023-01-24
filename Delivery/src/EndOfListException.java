/**
 * An exception thrown in the class to indicate that
 * the list is empty or its at the end
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #2
 *
 */
public class EndOfListException extends Exception {
    /**
     * Default constructor that passes a default String
     * Postcondition:
     * Object is created and contains default message
     */
    public EndOfListException(){


        super("You've reached the end of the list!");
    }
    /**
     * Second constrcutor that passes provided string
     * @param x
     * String x is the provided string passed
     * Postcondition:
     * Object is created and contains provided message
     */
    public EndOfListException(String x){


        super(x);
    }
}
