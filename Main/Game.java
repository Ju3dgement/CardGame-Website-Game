package Main;
import java.util.*;

public class Game {
    private Deck adventureDeck;
    private Player[] players;

    public Game() {
        adventureDeck = new Deck();
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player(i, "Player " + (i + 1));
        }
        initializeDeck();
        dealInitialCards();
    }

    private void initializeDeck() {
        addFoeCards();
        addWeaponCards();
        adventureDeck.shuffle();
    }

    private void addFoeCards() {
        addCards(new FoeCard(5), 8);
        addCards(new FoeCard(10), 7);
        addCards(new FoeCard(15), 8);
        addCards(new FoeCard(20), 7);
        addCards(new FoeCard(25), 7);
        addCards(new FoeCard(30), 4);
        addCards(new FoeCard(35), 4);
        addCards(new FoeCard(40), 2);
        addCards(new FoeCard(50), 2);
        addCards(new FoeCard(70), 1);
    }

    private void addWeaponCards() {
        addCards(new WeaponCard("D", 5), 6);
        addCards(new WeaponCard("H", 10), 12);
        addCards(new WeaponCard("S", 10), 16);
        addCards(new WeaponCard("B", 15), 8);
        addCards(new WeaponCard("L", 20), 6);
        addCards(new WeaponCard("E", 30), 2);
    }

    private void addCards(Card card, int count) {
        for (int i = 0; i < count; i++) {
            adventureDeck.addCard(card);
        }
    }

    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 12; i++) {
                player.addCard(adventureDeck.drawCard());
            }
        }
        System.out.println("TESTING");
    }


}