/**
 *Implements a Delivery node for the Delivery Linked List using the Delivery object data.
 * Also provides getters and setters as well as a toString method.
 * @author Brice Joseph, 114588946, R03
 */

public class DeliveryListNode extends Delivery {
    /**
     * Delivery Data stores the data of the delivery
     * DeliveryListNode next is used as a reference to the next node in the list
     * DeliveryListNode prev is used a reference to the previous node in the list
      */

    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

    /**
     * Default constructor
     */
    public DeliveryListNode(){

    }

    /**
     * A second default constructor that passes a new Delivery object
     * @param initData
     * init data is the initial data of the new node
     * @throws IllegalArgumentException
     * Throws this exception if the data of the delivery is null
     * Postcondition:
     * Data is set to initdata, next is set to null, prev is set to null, list is initialized
     */
    public DeliveryListNode(Delivery initData) throws IllegalArgumentException {

        try {
            this.data = initData;
            this.next = null;
            this.prev = null;

        } catch (IllegalArgumentException ex) {
            System.out.println("Illegal Argument! Data is null");
        }

    }
    /**
     * A method that returns the data of the delivery.
     * @return
     * Returns the data of the delivery object.
     * Postcondition:
     * Details about the data of the delivery is obtained
     */
    public Delivery getData() {
        return data;
    }
    /**
     * A method that allows the data of the delivery to be set
     * @param newData
     * newData will be the data that the delivery will be set too
     * Postcondition:
     * Data of delivery is set
     */
    public void setData(Delivery newData){

        data = newData;
    }
    /**
     * A method that returns the next object of the delivery list.
     * @return
     * Returns the next object of the delivery list.
     * Postcondition:
     * Next object of the delivery list is obtained
     */
    public DeliveryListNode getNext() {

        return next;
    }
    /**
     * A method that allows the next object of the delivery list to be set
     * @param newNext
     * newNext will be the next delivery object/node that the delivery list or cursor will be set too
     * Postcondition:
     * The next object/node of delivery list is set
     */
    public void setNext(DeliveryListNode newNext) {

        next = newNext;
    }

    /**
     * A method that returns the previous object of the delivery list.
     * @return
     * Returns the previous object of the delivery list.
     * Postcondition:
     * Previous object of the delivery list is obtained
     */
    public DeliveryListNode getPrev() {
        return prev;
    }
    /**
     * A method that allows the previous object of the delivery list to be set
     * @param newPrev
     * newPrev will be the previous delivery object/node that the delivery list or cursor will be set too
     * Postcondition:
     * The previous object/node of delivery list is set
     */
    public void setPrev(DeliveryListNode newPrev){

        prev = newPrev;
    }
}
