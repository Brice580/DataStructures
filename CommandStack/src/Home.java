/**
 * This class represents the Home command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class Home implements Command{

    /**
     * Public constructor for the command
     * Postcondition:
     * Command is created
     */
    public Home(){

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

        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){
        return "Home";
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
        return "Home Screen";
    }
}
