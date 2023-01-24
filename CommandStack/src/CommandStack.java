import java.util.Stack;

/**
 * This class represents a command stack and extends Stack from the Java API
 *
 * @author Brice Joseph, 114588946, R03
 */
public class CommandStack extends Stack<Command>{
    /**
     * Prints the current screen display printing
     * @return
     * String version of Command stack Current Screen
     */
    public String getScreenCommand(){

        return
                "Current Screen: " ;
    }

    /**
     * Command is returned in printable version
     * @return
     * Commands are printed
     */
    public String toShortString() {

        return null;
    }



}
