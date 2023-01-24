/**
 * Implements a Delivery object with provided getters and setter for data field
 * as well as toString method.
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #2
 */


public class Delivery {
    /**
     * String source is the source of the delivery object
     * String dest is the destination of the delivery object
     * String instruction provides the instruction for the delivery object
     *
     */
    private String source;
    private String dest;
    private String instruction;

    /**
     * default constructor
     */
    public Delivery(){

    }

    /**
     * A second constructor for the delivery which passes the data field
     * as parameters.
     * @param source
     * Source is the source for the delivery
     * @param dest
     * Destination is the source for the delivery
     * @param instruction
     * Instruction is the instruction for the delivery
     */
    public Delivery(String source, String dest, String instruction){

        this.source = source;
        this.dest = dest;
        this.instruction = instruction;
    }

    /**
     * A method that returns the source of the delivery.
     * @return
     * Returns the name of the source.
     * Postcondition:
     * Source of delivery is obtained
     */
    public String getSource() {
        return this.source;
    }

    /**
     * A method that allows the name of the source to be set
     * @param source
     * Source will be the name that the delivery source will be set too
     * Postcondition:
     * Soruce of delivery is set
     */
    public void setSource(String source) {
        this.source = source;
    }
    /**
     * A method that returns the destination of the delivery.
     * @return
     * Returns the destination of the source.
     * Postcondition:
     * Destination of delivery is obtained
     */
    public String getDest() {
        return this.dest;
    }
    /**
     * A method that allows the name of the destination to be set
     * @param dest
     * Destination will be the name that the delivery source will be set too
     * Postcondition:
     * Destination of delivery is set
     */
    public void setDest(String dest) {
        this.dest = dest;
    }
    /**
     * A method that returns the instruction of the delivery.
     * @return
     * Returns the name of the instruction.
     * Postcondition:
     * Instruction of delivery is obtained
     */
    public String getInstruction() {
        return this.instruction;
    }
    /**
     * A method that allows the name of the instruction to be set
     * @param instruction
     * Instruction will be the name that the delivery source will be set too
     * Postcondition:
     * Instruction of delivery is set
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;

    }

    /**
     * A method that will print out the details of the delivery
     * @return
     * Returns the details of the delivery in a readable format
     * Postcondition:
     * Prints out a string of details from the delivery
     */
    @Override
    public String toString() {


        return "To: "+getDest()+" | From: "+getSource()+"\nInstruction: "+getInstruction();
    }
}
