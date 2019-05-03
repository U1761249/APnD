package carpark;

/**
 * Models a stream of cars entering a car park.
 *
 * @author Hugh Osborne
 * @version February 2019
 */
public class Arrivals extends Thread {
	
	CarParkControl control;  // The car park is controlled by this control.

	/**
	 * @param control the car park controller the car park the cars are entering.
	 */
	public Arrivals(CarParkControl control) {
		this.control = control;
	}

	/**
	 * A continuous stream of cars entering the car park.
	 * The car park control ensures that a car cannot enter a full car park.
	 */
	public void run() {
		try {
			while (true) {
				control.enter();
			}
		} catch (InterruptedException e) {}
	}
}
