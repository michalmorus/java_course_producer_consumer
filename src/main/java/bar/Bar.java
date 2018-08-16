package bar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bar {

    private static final Logger logger = LogManager.getLogger();

    private Client[] clients;
    private Staff staff;

    public Bar(Client[] clients, Staff staff) {
        this.clients = clients;
        this.staff = staff;
    }

    public void startParty() {
        Thread staffThread = new Thread(this.staff);
        staffThread.start();

        for (Client client : this.clients) {
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}