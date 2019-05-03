package FittingRooms;

public class Customer extends Thread {

    private int id;


    public Customer(int id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Customer " + id;
    }



}
