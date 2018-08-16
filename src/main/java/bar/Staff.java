package bar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Staff implements Runnable {

    private static final Logger logger = LogManager.getLogger();

    private Table table;

    public Staff(Table table) {
        this.table = table;
    }

    public void run() {
        while (this.table.isPartyOn()) {
            if (!this.table.isBeerOnTable()) {
                Beer[] beers = this.getBeers();
                logger.info("Staff - no beer on table, adding " + beers.length + " beers!");
                this.table.addBeers(beers);
            } else {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Beer[] getBeers() {
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        Beer[] beers = new Beer[count];

        for (int i = 0; i < count; i++) {
            beers[i] = new Beer(BeerSize.getRandomBeer());
        }

        return beers;
    }
}
