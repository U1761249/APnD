package FittingRooms;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FittingRoomManager {
    final Lock lock = new ReentrantLock();
    final Condition nowFree = lock.newCondition();
    private ArrayList<Customer> buffer;
    boolean free = true;

    public void useFittingRoom(Customer customer) throws InterruptedException {
        if (free){
            free = false;
            lock.lock();
        }
        else{
            buffer.add(customer);
            nowFree.await();
        }
    }

    public void releaseFittingRoom(Customer customer){
        if (!free){
            try{
                free = true;
                nowFree.signal();
            }
            finally {
                lock.unlock();
            }
        }
        else{throw new IllegalStateException("A customer tried to leave the fitting room before entering! What sorcery is this?");}

    }

}
