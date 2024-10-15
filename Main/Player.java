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
    public int numberTrimNeeded(){
        if (hand.size() > 12){
            return hand.size() - 12;
        } else{
            return 0;
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



    private int compareWeaponType(String one, String two){
        List<String> typeOrder = Arrays.asList("S", "H");
        int index1 = typeOrder.indexOf(one);
        int index2 = typeOrder.indexOf(two);
        return Integer.compare(index1, index2);
    }

    public void reduceHand12(Scanner scannerInput){
        while (getHand().size() > 12) {
            printHand();
            System.out.println(name + " need to reduce hand size to 12 pick to discard(int):");
            int indexDelete = scannerInput.nextInt();
            removeCardHand(getHand().get(indexDelete));
        }
        sortHand();
    }
    public void printHand(){
        sortHand();
        System.out.println("========================");
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++){
            System.out.print(i + ":" + hand.get(i) + " | ");
        }
        System.out.println("\n");
    }

    public void sortHand(){
        List<Card> sortedIncreasing = new ArrayList<>();
        List<Card> FoeCards = new ArrayList<>();
        List<Card> WeaponCards = new ArrayList<>();

        for (Card card : hand){
            if (card.type.startsWith("F")){
                FoeCards.add(card);
            }
        }
        //Bubble sort by value increasing order
        for (int i = 0; i < FoeCards.size() - 1; i++) {
            for (int j = 0; j < FoeCards.size() - i - 1; j++) {
                if (FoeCards.get(j).value > FoeCards.get(j + 1).value) {
                    Card temp = FoeCards.get(j);
                    FoeCards.set(j, FoeCards.get(j + 1));
                    FoeCards.set(j + 1, temp);
                }
            }
        }

        for (Card card : hand){
            if (!card.type.startsWith("F")){
                WeaponCards.add(card);
            }
        }

        //Bubble sort by value increasing order and THEN by weapon if there are multiple values that are the same according to assignment rule
        for (int i = 0; i < WeaponCards.size() - 1; i++) {
            for (int j = 0; j < WeaponCards.size() - i - 1; j++) {
                if (WeaponCards.get(j).value > WeaponCards.get(j + 1).value) {
                    Card temp = WeaponCards.get(j);
                    WeaponCards.set(j, WeaponCards.get(j + 1));
                    WeaponCards.set(j + 1, temp);
                }
                else if (WeaponCards.get(j).value == WeaponCards.get(j+1).value){
                    WeaponCard w1 = (WeaponCard) WeaponCards.get(j);
                    WeaponCard w2 = (WeaponCard) WeaponCards.get(j + 1);

                    if (compareWeaponType(w1.type, w2.type) > 0){
                        Card temp = WeaponCards.get(j+1);
                        WeaponCards.set(j+1, temp);

                    }
                }
            }
        }

        sortedIncreasing.addAll(FoeCards);
        sortedIncreasing.addAll(WeaponCards);
        hand = sortedIncreasing;
    }
    public void removeCardHand(Card card){
        hand.remove(card);
    }


}