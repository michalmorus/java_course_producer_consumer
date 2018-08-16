package bar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private static final Logger logger = LogManager.getLogger();
    private ArrayList<Beer> beers = new ArrayList<Beer>();
    private boolean party = true;
    private int totalBeers = 0;
    private double totalCapacity = 0;
    synchronized void addBeers(Beer[] beers) {
        this.beers.addAll(Arrays.asList(beers));
        notifyAll();
    }

    synchronized Beer getBeer() {
        if (this.isBeerOnTable()) {
            Beer beer = this.beers.get(0);
            totalBeers++;
            totalCapacity += beer.getSize();
            this.beers.remove(0);

            return beer;
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    synchronized boolean isBeerOnTable() {
        return !this.beers.isEmpty();
    }

    public void stopParty() {
        this.party = false;
        logger.info("Party was finished, drinked: " + totalBeers + " beers with summary capacity " + totalCapacity);
    }

    public boolean isPartyOn() {
        return this.party;
    }
}
