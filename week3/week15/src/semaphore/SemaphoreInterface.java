package semaphore;


/**
 * A semaphore interface that matches the nethod names used in the lecture.
 *
 * @author Hugh Osborne
 * @version January 2019
 */

public interface SemaphoreInterface
{
    public void poll() throws InterruptedException;
    public void vote() throws InterruptedException, SemaphoreLimitError;
    public String getName();
}
