/**
 * Implements a Delivery Doubly Linked List with Delivery List Nodes using data from Delivery objects.
 * Provides a cursor as well as a head and tail whose values can be obtained and edited.
 * Also allows cursor to move freely among the list and provides methods to add and remove deliveries.
 * @author Brice Joseph, 114588946, R03
 *
 * Assignement: HW #2
 */

public class DeliveryList extends DeliveryListNode {
    /**
     * DeliveryListNode head is the head of the Linked list
     * DeliveryListNode tail is the tail of the list
     * DeliveryListNode cursor is the cursor which can move in the list
     * Int length is the length of the list
     */
    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int length;

    /**
     * Default constructor that initializes the head, tail, cursor, and length values;
     * Postcondition:
     * Head, tail, and cursor are intialized and length is set to 0
     */public DeliveryList(){

        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.length = 0;

    }


    /**
     * A method that gets the number of deliveries in the list at any given time
     * @return
     * Returns the number of deliveries in the list
     * Postcondition:
     * The number of deliveries is returned
     */
    public int numDeliveries(){

        return length;
    }

    /**
     * A method that allows the head of the list to be modified
     * @param node
     * Node is the node which the head will be set to
     * Postconditon:
     * Head is set to a node
     */
    public void setHead(DeliveryListNode node){
        this.head = node;
    }
    /**
     * A method that allows the tail of the list to be modified
     * @param node
     * Node is the node which the tail will be set to
     * Postconditon:
     * Tail is set to a node
     */
    public void setTail(DeliveryListNode node){
        this.tail = node;
    }
    /**
     * A method that allows the cursor of the list to be modified
     * @param node
     * Node is the node which the cursor will be set to
     * Postconditon:
     * Cursor is set to a node
     */
    public void setCursor(DeliveryListNode node){
        this.cursor = node;
    }

    /**
     * A method that gets the delivery at the cursor
     * @return
     * Returns the delivery at the position cursor is at
     * Postcondition:
     * Cursor is retrieved and unchanged
     */
    public Delivery getCursor(){
        if(cursor == null){
            return null;
        }
        else{
            return cursor;
        }
    }

    /**
     * A method that gets the delivery at the head of the list
     * @return
     * Returns the delivery at the head
     * Postcondition:
     * Head is obtained and unchanged
     */
    public DeliveryListNode getHead(){

        return head;
    }
    /**
     * A method that gets the delivery at the tail of the list
     * @return
     * Returns the delivery at the tail
     * Postcondition:
     * Tail is obtained and unchanged
     */
    public DeliveryListNode getTail(){

        return tail;
    }

    /**
     * A method that will reset the position of the cursor to the head of the list by
     * first checking if head is null or not
     * Postcondition:
     *Cursor is set to head position, if head is null, then cursor, tail, and head are set to null
     */
    public void resetCursorToHead(){

        if(head != null){
            cursor = head;
        }
        else{
            cursor = null;
            head=null;
            tail=null;
        }
    }

    /**
     * A method that will move the position of the cursor forwards
     * @throws EndOfListException
     * Throws this exception if cursor reaches the last delivery in the list
     * Postcondition:
     * Position of cursor is moved forward
     */
    public void cursorForward() throws EndOfListException {

        try {
            if (cursor != tail) {
                cursor = cursor.getNext();
            } else {
                throw new EndOfListException();
            }
        }catch(EndOfListException ex){
            System.out.println("You've reached the end of the list!");
        }
    }

    /**
     * A method that moves the position of the cursor backwards
     * @throws EndOfListException
     * Throws this exception if the cursor is at the beginning of the list
     * Postcondition:
     * Cursor is moved backward
     */
    public void cursorBackward() throws EndOfListException{

        try {
            DeliveryListNode nodePTR = head;

            while(nodePTR != cursor){
                nodePTR = nodePTR.getNext();
            }
            if (cursor != head) {
                cursor = nodePTR.getPrev(); //node ptr?
            } else {
                throw new EndOfListException();
            }
        }catch(EndOfListException ex){
            System.out.println("You've reached the end of the list!");
        }
    }

    /**
     * A method that will insert the first delivery into the list and set it as the head
     * @param newDelivery
     * This is the newDelivery that will be inserted into the list
     * Postcondition:
     * A new Delivery is inserted only into the head of the list
     */
    public void insertFirst(Delivery newDelivery){

        DeliveryListNode node1 = new DeliveryListNode(newDelivery);
        node1.setPrev(null);
        node1.setNext(null);
        this.setHead(node1);
        this.setTail(node1);
        this.setCursor(node1);
        length++;
    }

    /**
     * A method that will insert a new delivery after the position of the cursor
     * @param newDelivery
     * This is the new Delivery that will be inserted into the list
     * @throws IllegalArgumentException
     * Throws this exception if newDelivery is null
     * Postcondition:
     * Delivery is added into the list after the cursor
     */
    public void insertAfterCursor(Delivery newDelivery) throws IllegalArgumentException{

        DeliveryListNode node = new DeliveryListNode(newDelivery);
        try {

            if(cursor == tail){
                cursor.setNext(node);
                node.setPrev(cursor);
                node.setNext(null);
                tail = node;
                //cursor = cursor.getNext();
            }

                else if(cursor != null && cursor.getNext() != null) {


                    node.setNext(cursor.getNext());
                    cursor.getNext().setPrev(node);
                    cursor.setNext(node);
                    node.setPrev(cursor);

                }
                else if(newDelivery == null){
                    throw new IllegalArgumentException();
                }

        }catch(IllegalArgumentException ex){
            System.out.println("Invalid Argument, You're at the End!");
        }

    }

    /**
     * A method that appends a delivery to the tail of the list
     * @param newDelivery
     * This is the new Delivery that will be inserted into the list
     * @throws IllegalArgumentException
     * Throws this exception if newDelivery is null
     * Postcondition:
     * Delivery is added to the tail of the list
     */
    public void appendToTail(Delivery newDelivery) throws IllegalArgumentException{

        DeliveryListNode node = new DeliveryListNode(newDelivery);
        try {
            if(tail == null) {

                head = node;
                tail = node;
                cursor = node;
                length++;

            } else {
                node.setNext(tail.getNext());
                node.setPrev(tail);
                tail = node;
                length++;
            }
            if(tail == head){
                node.setPrev(head);
                node.setNext(head.getNext());
                tail=node;
                length++;
            }
        }catch(IllegalArgumentException ex){
            System.out.println("Invalid Argument!");
        }

    }

    /**
     * A method that removes the delivery node at the position of the cursor
     * @return
     * Returns the delivery that was removed
     * @throws EndOfListException
     * Throws this exception if there is no delivery left
     * Postconditon:
     * Delivery at cursor is removed and set to the next delivery
     */
    public Delivery removeCursor() throws EndOfListException{

        DeliveryListNode saved = new DeliveryListNode(cursor.getData());

       DeliveryListNode nodePTR = head;
       while(nodePTR != cursor) {

               nodePTR = nodePTR.getNext();

       }
            if(nodePTR == head && head.getNext() != null){

               head = head.getNext(); //maybe replace cursor w node ptr
               head.setPrev(null);
               cursor = head;
               length--;
           }
           else if(nodePTR.getNext()==null && nodePTR.getPrev() == null){

               head = null;
               tail = null;
               cursor = null;
               length--;
           }
           else if(nodePTR == tail && tail.getPrev() != null){

               tail = cursor.getPrev();
               cursor = cursor.getPrev();
               cursor.setNext(null);
               length--;
           }

           else {
               cursor = cursor.getNext();
               cursor.setPrev(nodePTR.getPrev());
               nodePTR.getPrev().setNext(cursor);
               length--;
           }


        if(nodePTR == null){
            try {
                throw new EndOfListException();
            }catch(EndOfListException ex){
                System.out.println("End of List!");
            }
        }

        return saved;
    }

    /**
     * A method that prints out a table with the details of the deliveries in the list
     * @return
     * Returns a table of the current deliveries
     * Postcondition:
     * DeliveryList is printed out
     */
    public String toString(){

    DeliveryListNode nodePTR = head;

        System.out.println("---------------------------------");

    while(nodePTR != null){

        if(nodePTR == cursor){
            System.out.println("->");
        }
        else {

            System.out.println("~");
        }
            System.out.println(nodePTR.getData());
            nodePTR = nodePTR.getNext();


    }
        System.out.println("---------------------------------");
        return "";
    }


}
