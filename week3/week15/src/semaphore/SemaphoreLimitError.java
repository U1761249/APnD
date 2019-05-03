package semaphore;


/**
 * Report bounded semaphores exceeding their limits.
 * 
 * @author Hugh Osborne
 * @version January 2019
 */
public class SemaphoreLimitError extends Exception
{
    public SemaphoreLimitError(SemaphoreInterface semaphore)
    {   
        super(Thread.currentThread().getName() + " caused " + semaphore.getName() + " to exceed its upper limit");
    }
}
