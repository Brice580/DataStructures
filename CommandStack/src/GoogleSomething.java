import java.util.Scanner;
/**
 * This class represents the Google Something command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class GoogleSomething implements Command{
    /**
     * String query is the source for the Google Something command which the user inputs
     */
    private String query;
    /**
     * Public constructor for the command
     * @param scanner
     * Uses scanner to assign the data field destination
     * Postcondition:
     * Command is created
     */
    public GoogleSomething(Scanner scanner){
        System.out.println("Please enter a query: ");
        this.query = scanner.nextLine();

    }
    @Override
    /**
     * Checks validity of the command with the condition of the stack
     * @returns
     * returns true if its a valid command and false if not
     * Postcondition:
     * Validity is checked
     */
    public boolean validCommand(CommandStack stack) {

        if(stack.peek() instanceof SafariHome){
            return true;
        }
        return false;    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the query
     */
    public String getQuery(){
        return query;
    }
    @Override
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){
        return "->G:"+getQuery();
    }
    /**
     * This method prints out the command for the Current screen format
     * @returns
     * Returns the Current Screen display text
     * Postcondition:
     * Command is printed
     */
    public String toShortString() {

        return "Google: "+getQuery();
    }
}
