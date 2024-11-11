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

    public void discard(EventCard card) {
        discardPile.push(card);
    }
    public void shuffle() {
        Collections.shuffle(eventCards);
    }

    public int countSpecificCard(EventCard cardToCheck) {
        int count = 0;
        for (EventCard card : eventCards) {
            if (card.toString().equals(cardToCheck.toString())) {
                count++;
            }
        }
        return count;
    }

    public EventCard riggedDraw(EventCard card) {
        reshuffle();
        for (int i = 0; i < eventCards.size(); i++) {
            if (card.toString().equals(eventCards.get(i).toString())) {
                eventCards.remove(i);
                break;
            }
        }
        return card;
    }
    public void reshuffle() {
        while (!discardPile.isEmpty()) {
            eventCards.add(discardPile.pop());
        }
        shuffle();
    }
    public EventCard drawCard() {
        if (eventCards.isEmpty()) {
            reshuffle();
        }
        EventCard card = eventCards.removeLast();
        System.out.println("Drawn:" + card.toString());

        return card;
    }

    public void rigDeckTop(List<QCard> riggedCards) {
        for (int i = riggedCards.size() - 1; i >= 0; i--) {
            eventCards.add(riggedCards.get(i));  // Add each card to the end of the deck
            eventCards.remove(i);
        }
    }
}
