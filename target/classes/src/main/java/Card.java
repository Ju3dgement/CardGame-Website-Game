public abstract class Card {
    protected String type;
    protected int value;

    public Card(String type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + value;
    }
}
