package Main;

import java.util.*;

abstract class Card {
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

class FoeCard extends Card {
    public FoeCard(int value) {
        super("F", value);
    }
}

class WeaponCard extends Card {
    public WeaponCard(String type, int value) {
        super(type, value);
    }
}

class Deck {
    private List<Card> cards;
    private Stack<Card> discardPile;

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
        return cards.remove(cards.size() - 1);
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