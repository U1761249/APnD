package resourceManager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockResourceManager extends BasicResourceManager {

    final Lock lock = new ReentrantLock();
    final Condition notFree = lock.newCondition();
    private ArrayList<Integer> buffer;
    boolean free = true;

    /**
     *
     * Each resource manager has a resource.
     */


    /**
     * Set the resource and initialise the numbers of waiting processes, and the number of users, to zero.
     *
     * @param resource the resource managed by this manager
     * @param maxUses  the maximum number of uses permitted for this manager's resource.
     */
    public LockResourceManager(Resource resource, int maxUses) {
        super(resource, maxUses);
    }

    @Override
    synchronized public void requestResource(int priority) throws ResourceError {
            if (free) {
                lock.lock();
                free = false;
            }
            else{
                try {
                    buffer.add(priority);
                    Collections.sort(buffer, Collections.reverseOrder());
                    notFree.await();
                } catch (InterruptedException e) {e.printStackTrace(); }

            }
    }



    @Override
    synchronized public int releaseResource() throws ResourceError {
        if (!free){
            free = true;
            try{
                int priority;
                if(buffer.isEmpty()){priority = -1;}
                else{
                    priority = buffer.get(0);
                    buffer.remove(0);
                }
                return priority;
            }
            finally {
                lock.unlock();
            }
        }
        else{throw new ResourceError("Attempted to release a free resource " + getResourceName());}

    }
}
