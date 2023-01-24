import java.util.Scanner;
/**
 * This class represents the Go To Bookmark command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class GoToBookmark implements Command{
    /**
     * String bookmark is the source for the plan route command which the user inputs
     */
    protected String bookmark;
    /**
     * Public constructor for the command
     * @param scanner
     * Uses scanner to assign the data field bookmark
     * Postcondition:
     * Command is created
     */
    public GoToBookmark(Scanner scanner){
        System.out.println("Please enter bookmark name: ");
        bookmark = scanner.nextLine();
    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the bookmark
     */
    public String getBookmark(){
        return bookmark;
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
                return false;
    }


    @Override
    /**
     * This method prints out the command for the Current screen format
     * @returns
     * Returns the Current Screen display text
     * Postcondition:
     * Command is printed
     */
    public String toShortString() {

        return "Showing results for "+getBookmark();
    }
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){
        return "F:"+getBookmark();
    }
}


