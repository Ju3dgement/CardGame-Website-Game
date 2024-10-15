package Main;

import java.util.*;
import java.util.Scanner;
public class Game{
    public Deck adventureDeck;
    public Player[] players;
    public EventDeck eventDeck;
    public Player hotSeat;

    public Scanner scan;
    public List<List<Card>> stageFull = new ArrayList<>();
    public QCard questCard;
    public Player questMakerPlayer;
    public List<Player> doQuestList = new ArrayList<>();

    public List<Player> activeParticipants;

    public Game() {
        adventureDeck = new Deck();
        eventDeck = new EventDeck();
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player(i + 1, "P" + (i + 1));
        }
        hotSeat = players[0];
        scan = new Scanner(System.in);
    }


    public void addFoeCards() {
        // 50 cards
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
    public void addWeaponCards() {
        addCards(new WeaponCard("D", 5), 6);
        addCards(new WeaponCard("H", 10), 12);
        addCards(new WeaponCard("S", 10), 16);
        addCards(new WeaponCard("B", 15), 8);
        addCards(new WeaponCard("L", 20), 6);
        addCards(new WeaponCard("E", 30), 2);
    }

    public void addCards(Card card, int count) {
        for (int i = 0; i < count; i++) {
            adventureDeck.addCard(card);
        }
    }
    public void initializeAdventureDeck() {
        addFoeCards();
        addWeaponCards();
        adventureDeck.shuffle();
    }

    public void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 12; i++) {
                player.addCard(adventureDeck.drawCard());
            }
        }
    }

    public void moveToNextPlayer(Scanner userInput){
        System.out.println(hotSeat.getCharName() + " turn ended press <return>");
        userInput.nextLine();
        int currentPlayerIndex = hotSeat.getCharId() - 1;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        hotSeat = players[currentPlayerIndex];
    }

    public boolean checkWinner() {
        boolean winningQuestionMark = false;
        for (Player player : players){
            if (player.getShields() >= 7){
                System.out.println(player.getCharName() + " has 7 shields and won!");
                winningQuestionMark = true;
            }
        }
        return winningQuestionMark;
    }
    public void playGame(){
        while (!checkWinner()) {
            System.out.println("Hot Seat: " + hotSeat.getCharName());
            initializeAdventureDeck();
            eventDeck.initializeDeck();
            moveToNextPlayer(scan);
        }

    }
}