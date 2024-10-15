package Main;
import java.util.*;

public class Deck {
    public List<Card> cards;
    public Stack<Card> discardPile;

    public Deck() {
        cards = new ArrayList<>();
        discardPile = new Stack<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            reshuffle();
        }
        return cards.removeLast();

    }
    public void discard(Card card) {
        discardPile.push(card);
    }

    public void reshuffle() {
        while (!discardPile.isEmpty()) {
            cards.add(discardPile.pop());
        }
        shuffle();
    }


}