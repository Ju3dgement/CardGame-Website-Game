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

    public int countSpecificCard(Card cardToCheck) {
        int count = 0;
        for (Card card : cards) {
            if (card.toString().equals(cardToCheck.toString())){
                count++;
            }
        }
        return count;
    }

    public Card riggedDraw(Card card) {
        reshuffle();
        for (int i = 0; i < cards.size(); i++) {
            if (card.equals(cards.get(i))) {
                cards.remove(i);
                break;
            }
        }
        return card;
    }
    public void riggedClearHand(List<Card> playerHand){
        while (!playerHand.isEmpty()) {
            Card card = playerHand.removeLast();
            discard(card);
        }
    }
    public void rigDeckTop(List<Card> riggedCards) {

        for (int i = riggedCards.size() - 1; i >= 0; i--) {
            cards.add(riggedCards.get(i));  // Add each card to the end of the deck
            cards.remove(i);
        }

    }

}