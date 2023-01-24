import java.util.Scanner;

/**
 * Implements the Doubly Linked Delivery List into a Delivery Driver object which provides a user interface
 * which utilized methods and classes of DeliveryList in its operations
 * @author Brice Joseph, 114588946, R03
 * Assignment: HW #2
 */
public class DeliveryDriver extends DeliveryList {
    /**
     * DeliveryList A is the first delivery list
     * DeliveryList B is the second delivery list
     * DeliveryList selected is used for user to switch between shelves and references either A or B
     * Delivery saved is the saved delivery for pasting a delivery
     */

    private static DeliveryList A;
    private static DeliveryList B;
    private static DeliveryList selected;
    private static Delivery saved;

    /**
     * Main method which initializes Delivery Lists and creates a scanner which
     * is implements in a switch statement for different menu options
     * @param args
     * Default main method parameter
     * @throws EndOfListException
     * Throws this if list is empty or at end of shelf for different operations
     * Postcondition:
     * Menu commands are executed on list
     */
    public static void main(String[] args) throws EndOfListException {


        DeliveryList A = new DeliveryList();
        DeliveryList B = new DeliveryList();
        DeliveryList selected = A;

        Scanner input = new Scanner(System.in);


        System.out.println("Welcome to the Delinquent Dollar Delivery Scheduler!");
        System.out.println();
        System.out.println("A)Add a Delivery After Cursor \nR)Remove Delivery At Cursor \nX)Cut Cursor"
                + "\nV)Paste After Cursor "
                + "\nH)Cursor to Head \nT)Cursor to Tail "
                + "\nF)Cursor Forward \nB)Cursor Backward "
                + "\nS)Switch Delivery Lists \nP)Print current list" +
                  "\nQ)Quit");


        boolean condition = true;

        while(condition){


            System.out.println("Please choose an option above: ");
            String in = input.next().toUpperCase();
            input.nextLine();
            switch (in){

                /**
                 * Case A: Creates a new delivery object and add it into the Delivery list
                 * Postcondition:
                 * New delivery is added into the list
                 */

                case "A":

                    System.out.println("Please enter a source: ");
                    String a1 = input.nextLine();
                    //input.nextLine();
                    System.out.println("Please enter a destination: ");
                    String a2 = input.nextLine();

                    System.out.println("Please enter any special instructions: ");
                    String a3 = input.nextLine();

                    Delivery newDelivery = new Delivery(a1,a2,a3);

                    if(selected.getHead() == null) {
                        selected.insertFirst(newDelivery);
                        System.out.println("Delivery Added!");
                    }
                    else{
                        selected.insertAfterCursor(newDelivery);
                        System.out.println("Delivery Added!");

                    }


                    break;
                /**
                 * Case R: Removes the delivery object referenced by the cursor
                 * Postcondition:
                 * Delivery is removed and cursor is set to next delivery if not equal to tail
                 */
                case "R":
                    try {
                        if (selected.getHead() == null) {
                            throw new EndOfListException();
                        }else {
                            selected.removeCursor();
                            System.out.println("Delivery has been removed!");
                        }
                    }catch (EndOfListException ex){
                        System.out.println("Empty List!");
                    }
                    break;
                /**
                 * Case X: Removes the delivery object referenced by the cursor and saved the removed object in temp
                 * variable
                 * Postcondition:
                 * Delivery is removed and saved into a temp variable
                 */
                case "X":
                    DeliveryListNode nodePtr = selected.getHead();
                    while(nodePtr != selected.getCursor()){
                        nodePtr = nodePtr.getNext();
                    }
                    try {
                        if (selected.getHead() != null) {

                            saved = new Delivery();
                            saved.setDest(nodePtr.getData().getDest());
                            saved.setInstruction(nodePtr.getData().getInstruction());
                            saved.setSource(nodePtr.getData().getSource());

                            selected.removeCursor();
                            System.out.println("Cursor has been cut!");
                        } else throw new EndOfListException();
                    }catch (EndOfListException ex){
                        System.out.println("Empty List!");
                    }
                    break;

                /**
                * Case V: Pastes the saved delivery from case X to the cursor position in the list
                * Postconditon:
                 * Temp variable with the saved delivery is pasted into list
                */
                case "V":


                    if(selected.getHead() ==null) {
                        selected.insertFirst(saved);
                        System.out.println("Delivery Added!");
                    }
                    else{
                        selected.insertAfterCursor(saved);
                        System.out.println("Delivery Added!");

                    }

                    System.out.println("Saved cursor has been pasted!");
                    break;

                /**
                 * Case H: Resets cursor position to the head
                 * Postcondition:
                 * Cursor is at head
                 */
                case "H":
                    if(selected.getCursor() == selected.getHead()){
                        System.out.println("Cursor is already at head!");
                    }
                    else {
                        selected.resetCursorToHead();
                        System.out.println("Cursor is at head!");
                    }
                    break;
                /**
                 * Case F: Moves cursor forward
                 * Postcondition:
                 * Cursor is moved to next position
                 */
                case "F":
                    try {
                        if(selected.getCursor() != selected.getTail()) {
                            selected.cursorForward();
                            System.out.println("Cursor moved forwards!");
                        }
                        else
                            throw new EndOfListException();
                    }catch(EndOfListException ex){
                        System.out.println("Cursor can't be moved forwards, it's at the end of the list!");
                    }
                    break;
                /**
                 * Case T: Sets cursor to tail position
                 * Postcondition:
                 * Cursor is set to tail position
                  */
                case "T":
                    if(selected.getCursor() == selected.getTail()){
                        System.out.println("Cursor is already at tail!");
                    }
                    else {
                        DeliveryListNode nodePTR = new DeliveryListNode();
                        nodePTR = selected.getHead();
                        while (nodePTR != selected.getTail()) {
                            nodePTR = nodePTR.getNext();
                        }
                        selected.setCursor(nodePTR);
                        System.out.println("Cursor is at tail!");
                    }
                    break;
                /**
                 * Case B: Moves cursor backwards
                 * Postcondition:
                 * Cursor is moved to previous condition
                 */
                case "B":
                    try {
                        if(selected.getCursor() != selected.getHead()) {
                            selected.cursorBackward();
                            System.out.println("Cursor moved backwards!");
                        }
                        else
                            throw new EndOfListException();
                    }catch(EndOfListException ex){
                        System.out.println("Cursor can't be moved backwards, it's already at the beginning!");
                    }
                    break;
                /**
                 * Case S: Switched delivery list that is being modified and looked at
                 * Postcondition:
                 * Delivery Lists are switched for user to interact with
                  */
                case "S":
                    if(selected == A){
                        selected = B;
                        System.out.println("Biz Billy's Deliveries is selected.");
                    }
                    else if(selected == B){
                        selected = A;
                        System.out.println("Money Mike's deliveries is selected.");
                    }
                    break;
                /**
                 * Case P: Print out delivery list in readable format
                 * Postcondition:
                 * Delivery list is printed out
                 */
                case "P":
                    if(selected== A){
                        System.out.println("Money Mike's Deliveries");
                    }
                    else if(selected == B){
                        System.out.println("Biz Billy's Deliveries");
                    }
                    selected.toString();
                    break;
                /**
                 * Case Q: Quits the program
                 * Postcondition:
                 * Program is terminated and scanner is closed
                 */
                case "Q":
                    System.out.println("Goodbye!");
                    condition = false;
                    input.close();
                    break;


            }


        }
    }
}
