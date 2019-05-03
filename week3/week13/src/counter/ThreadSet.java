package counter;

import java.util.Set;

/**
 * A thread set is a set of Threads which provides a facility for running all the threads in the set concurrently.
 * 
 * @author Hugh Osborne 
 * @version January 2019
 */
public interface ThreadSet<T extends Thread> extends Set<T>
{
	/**
	 * Run all the threads in this thread set in parallel, and wait for them to finish.
	 * @throws InterruptedException if an interrupt occurs while witing for the trheads to finish.
	 */
	void runSet() throws InterruptedException;
}
