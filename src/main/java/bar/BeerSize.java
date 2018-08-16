package bar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BeerSize {
    SMALL(100),
    MEDIUM(250),
    LARGE(500);

    private float size;
    private static final List<BeerSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    BeerSize(float size) {
        this.size = size;
    }

    public float getSize() {
        return this.size;
    }



    public static BeerSize getRandomBeer() {
        Random random = new Random();
        return VALUES.get(random.nextInt(SIZE));
    }
}
