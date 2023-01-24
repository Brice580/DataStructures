/**
 * This class represents the Start Navigation command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class StartNavigation implements Command{
    /**
     * String source is the source for the Start Navigation command which the user inputs
     * String destination is the destination for the Start Navigation command which the user inputs
     */
    protected String source;
    protected String destination;
    /**
     * Public constructor for the command
     * @param commandStack
     * Uses commandStack to assign the data field destination by using the previous commands instructions
     * Postcondition:
     * Command is created
     */
    public StartNavigation (CommandStack commandStack) {

    if(commandStack.peek() instanceof PlanRoute) {
        PlanRoute temp = (PlanRoute) commandStack.peek();
        source = temp.getSource();
        destination = temp.getDestination();
    }
    else if(commandStack.peek() instanceof FindPlace){
        FindPlace temp = (FindPlace) commandStack.peek();
        source = null;
        destination = temp.getDest();
    }


    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the destination
     */
    public String getDestination(){
        return destination;
    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the source
     */
    public String getSource(){
        return source;
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

        if(stack.peek() instanceof MapsHome)
            return true;

        return false;
    }
    /**
     * This method prints out the command for the Current screen format
     * @returns
     * Returns the Current Screen display text
     * Postcondition:
     * Command is printed
     */
    public String toShortString(){

        if(source == null){
            return "Navigating to "+getDestination();
        }
        return "Navigating from "+getSource()+" to "+getDestination();
    }
    @Override
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString() {

        if(source == null){
            return "->N:"+getDestination()+".";
        }

        return "->N:"+getSource()+"-"+getDestination()+".";
    }
}
