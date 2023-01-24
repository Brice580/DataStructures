/**
 * An exception thrown in the class to indicate that
 * the syntax for date is invalid
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #6
 *
 */
public class InvalidSyntaxException extends Exception{

    /**
     * Second constrcutor that passes provided string
     * @param x
     * String x is the provided string passed
     * Postcondition:
     * Object is created and contains provided message
     */
    public InvalidSyntaxException(String x){
        super("Invalid Syntax!");
    }
    /**
     * Default constructor that passes a default String
     * Postcondition:
     * Object is created and contains default message
     */
    public InvalidSyntaxException(){
        super("Invalid Syntax!");
    }
}