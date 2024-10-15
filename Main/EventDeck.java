package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class EventDeck {
    public List<EventCard> eventCards;
    public Stack<EventCard> discardPile;

    public EventDeck() {
        eventCards = new ArrayList<>();
        discardPile = new Stack<>();
    }
    public void initializeDeck() {
        addCards(new QCard(2), 3);
        addCards(new QCard(3), 4);
        addCards(new QCard(4), 3);
        addCards(new QCard(5), 2);
        addCards(new ECard(ECard.EventType.PLAGUE), 1);
        addCards(new ECard(ECard.EventType.QUEENS_FAVOR), 2);
        addCards(new ECard(ECard.EventType.PROSPERITY), 2);
        shuffle();
    }

    public void addCards(EventCard card, int count) {
        for (int i = 0; i < count; i++) {
            eventCards.add(card);
        }
    }

    public void shuffle() {
        Collections.shuffle(eventCards);
    }

}
