import bar.Bar;
import bar.Client;
import bar.Staff;
import bar.Table;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String... args) {
        final Table table = new Table();
        Client[] clients = {
                new Client("Józek", table, 5500, 3),
                new Client("Roman", table,1000, 15),
                new Client("Tadeusz", table,8000, 7)
        };
        Staff staff = new Staff(table);

        Bar bar = new Bar(clients, staff);
        bar.startParty();

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        table.stopParty();
                    }
                },
                60 * 1000
        );
    }
}
