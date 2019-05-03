package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Bolivia's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Peru extends Railway {
	/**
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Peru() throws SetUpError {
		super("Peru",new Delay(0.1,0.3));
	}

    /**
     * Shared basket. If 0 - Bolivia has priority. If 1 - Peru has priority. If 2 - Chile has priority.
     */
    public void runTrain() throws RailwaySystemError {
    	Clock clock = getRailwaySystem().getClock();
		Railway nextRailway = getRailwaySystem().getNextRailway(this);
    	while (!clock.timeOut()) {
    	    this.getBasket().putStone(this);
    		choochoo();
    		while (nextRailway.getBasket().hasStone(this)){
                if (getSharedBasket().stoneCount(this) == 0){ // If Peru has priority.
                    this.getBasket().takeStone(this);
                    while (!getSharedBasket().hasStone(this));
                    this.getBasket().putStone(this);
                }
    		}
    		crossPass();
    		this.getBasket().takeStone(this);
    		getSharedBasket().putStone(this);
    	}
    }
}