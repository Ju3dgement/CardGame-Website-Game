package Main;

public class ECard extends EventCard {
    public enum EventType { PLAGUE, QUEENS_FAVOR, PROSPERITY }

    private EventType eventType;

    public ECard(EventType eventType) {
        super(eventType.toString());
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
