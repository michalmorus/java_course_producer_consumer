package bar;

public class Beer {

    private float size;

    public Beer(BeerSize beerSize) {
        this.size = beerSize.getSize();
    }

    public float getSize() {
        return this.size;
    }
}

