package Main;

import java.util.*;

public class Player {
    private final int id;
    private final String name;
    private int shields;
    private List<Card> hand;
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.shields = 0;
        this.hand = new ArrayList<>();
    }
    public void discardExcessCards() {
        while (hand.size() > 12) {
            hand.remove(hand.size() - 1);
        }
    }

    public void removeShield(){
        shields--;
        if (shields < 0) {
            shields = 0;
        }
    }


    public void addShield(int numberShields) {
        shields += numberShields;
    }
    public void addCard(Card card) {hand.add(card);}

    // Getters
    public int getShields() {return shields;}
    public List<Card> getHand() {return hand;}
    public int getCharId() {return id;}
    public String getCharName() {return name;}
}