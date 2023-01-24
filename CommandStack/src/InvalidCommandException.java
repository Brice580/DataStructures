/**
 * An exception thrown in the class to indicate that
 * the command is invalid for given stack
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #3
 *
 */
public class InvalidCommandException extends Exception {
    /**
     * Default constructor that passes a default String
     * Postcondition:
     * Object is created and contains default message
     */
    public InvalidCommandException(){
        super("Invalid Command!");

    }
    /**
     * Second constrcutor that passes provided string
     * @param x
     * String x is the provided string passed
     * Postcondition:
     * Object is created and contains provided message
     */
    public InvalidCommandException(String x){
        super(x);
    }
}
