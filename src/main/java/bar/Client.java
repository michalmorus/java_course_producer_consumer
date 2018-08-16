package bar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements Runnable {

    private static final float DEFAULT_DRINK_SPEED = 10;
    private static final Logger logger = LogManager.getLogger();

    private String name;
    private Table table;
    private float capacity;
    private float speed;
    private int drinkedBeers = 0;

    public Client(String name, Table table, float capacity, float speed) {
        this.name = name;
        this.table = table;
        this.capacity = capacity;
        this.speed = speed;
        logger.info("created " + this.toString());
    }

    public void run() {
        while (this.table.isPartyOn() && this.capacity > 0) {
            Beer beer = this.table.getBeer();
            if (beer != null) {
                logger.info(name + " drinking " + beer.getSize() + "ml beer!");
                try {
                    Thread.sleep((int) (beer.getSize() * this.speed));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                capacity -= beer.getSize();
                drinkedBeers++;
                logger.info(name + " finished his " + drinkedBeers + " beer!");
            } else {
                logger.info(name + " - no beer! I'm waiting for staff!");
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info(name + " is drunk, after " + drinkedBeers + " beers, time to say goodbye!");
    }

    @Override
    public String toString() {
        return "client " + this.name + " can drink " + this.capacity + " ml beer with speed " + (this.speed * 10) + "ml/s";
    }
}
