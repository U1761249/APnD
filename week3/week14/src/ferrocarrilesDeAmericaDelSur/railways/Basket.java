package ferrocarrilesDeAmericaDelSur.railways;


import ferrocarrilesDeAmericaDelSur.errors.ProgrammingError;

/**
 * Models baskets for the railway system.
 * Baskets can have stones put in them or removed from them.
 * @author hugh
 *
 */
public class Basket {
	private String name; // the name of this basket, for tracing
	private int stones = 0; // the number of stones in the basket
	
	public Basket(String name) {
		this.name = name;
	}
	
	/**
	 * Put a stone in the basket.  The capacity of baskets is unlimited.
	 */
	private synchronized void putStone() {
		stones++;
		stones = stones % 3;
	}
	
	/**
	 * Put a stone in the basket.  The capacity of baskets is unlimited.
	 * This is the method called by a railway, and includes a delay and a trace.
	 * @param railway the railway putting the stone in this basket.
	 * @throws ProgrammingError if the specified railway is not registered with a railway system.
	 */
	public void putStone(Railway railway) throws ProgrammingError {
		
		railway.getRailwaySystem().trace(railway.name() + ": adding stone to " + name + " (" + stones + " stone" + (stones == 1 ? "" : "s") + " in the basket)");
		railway.delay();
		putStone();
	}
	
	/**
	 * Remove a stone from the basket.
	 * @throws ProgrammingError if there is no stone to remove.
	 */
	private synchronized void takeStone() throws ProgrammingError {
		if (stones > 0) {
			stones--;
		} else {
			throw new ProgrammingError("Cannot remove a stone from " + name + ".  The basket is empty");
		}
	}
	
	/**
	 * Remove a stone from the basket.
	 * This is the method called by a railway, and includes a delay and a trace.
	 * @param railway the railway taking a stone from this basket.
	 * @throws ProgrammingError if the specified railway is not registered with a railway system.
	 */
	public void takeStone(Railway railway) throws ProgrammingError {
		railway.getRailwaySystem().trace(railway.name() + ": removing stone from " + name + " (" + stones + " stone" + (stones == 1 ? "" : "s") + " in the basket)");
		railway.delay();
		takeStone();
	}
	
	/**
	 * Check if the basket contains at least one stone.
	 * @return true iff there is at least one stone in this basket.
	 */
	private synchronized boolean hasStone() {
		return stones > 0;
	}
	
	/**
	 * Check if the basket contains at least one stone.
	 * This is the method called by a railway, and includes a delay and a trace.
	 * @param railway the railway checking this basket.
	 * @return true iff there is at least one stone in this basket.
	 * @throws ProgrammingError if the specified railway is not registered with a railway system.
	 */
	public boolean hasStone(Railway railway) throws ProgrammingError {
		railway.getRailwaySystem().trace(railway.name() + ": checking " + name + " for stones");
		railway.delay();
		return hasStone();
	}
	/**
	 * Check if the basket contains at least one stone.
	 * @return true if there is at least one stone in this basket.
	 */
	private synchronized int stoneCount() {
		return stones;
	}

	/**
	 * Check if the basket contains at least one stone.
	 * This is the method called by a railway, and includes a delay and a trace.
	 * @param railway the railway checking this basket.
	 * @return true iff there is at least one stone in this basket.
	 * @throws ProgrammingError if the specified railway is not registered with a railway system.
	 */
	public int stoneCount(Railway railway) throws ProgrammingError {
		railway.getRailwaySystem().trace(railway.name() + ": checking " + name + " for stones");
		railway.delay();
		return stoneCount();
	}

}
