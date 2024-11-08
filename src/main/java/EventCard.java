public abstract class EventCard {
    protected String type;

    public EventCard(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
