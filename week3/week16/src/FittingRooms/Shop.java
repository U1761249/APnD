package FittingRooms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Shop implements Runnable {

    private static final int FITTINGROOM_COUNT = 5;
    ArrayList<Customer> customers = new ArrayList<Customer>();

    FittingRoom fittingRoom = new FittingRoom();
    FittingRoomManager manager = new FittingRoomManager();



    public void addCustomer(Customer customer){
        customers.add(customer);
    }



    public void run() {
        for (Customer customer: customers) {
            System.out.println("Starting " + customer);
            customer.start();
        }

        for (Customer customer: customers) {

            try {
                Random random = new Random();
                customer.sleep(random.nextInt(1000));
                System.out.println(customer + " wants to use the fitting room.");
                manager.useFittingRoom(customer);
                customer.sleep(random.nextInt(10000 ));
                manager.releaseFittingRoom(customer);
                System.out.println(customer + " is done with the fitting room.");
                customer.join();
                } catch (InterruptedException e) {e.printStackTrace();}

                System.out.println(customer + " has finished");
            }

        System.out.println("All customers finished");
    }

}
