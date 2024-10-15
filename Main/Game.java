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

    public List<Player> winnerWinnerChickenDinner = new ArrayList<>();
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

    public void processECard(ECard eventCard, Player currentPlayer, Scanner userInput) {
        switch (eventCard.getEventType()) {
            case PLAGUE:
                System.out.println("Drew 'Plague' " + currentPlayer.getCharName() + " lost 2 shields");
                currentPlayer.removeShield();
                currentPlayer.removeShield();
//                askLeaveHotSeat(currentPlayer, userInput);
                break;
            case QUEENS_FAVOR:
                System.out.println("Drew 'Queens Favor' " + currentPlayer.getCharName() + " drawing 2 cards");
                currentPlayer.addCard(adventureDeck.drawCard());
                currentPlayer.addCard(adventureDeck.drawCard());
//                currentPlayer.reduceHand12(userInput);
//                askLeaveHotSeat(currentPlayer, userInput);
                break;
            case PROSPERITY:
                int currentPlayerIndex = currentPlayer.getCharId() - 1;
                System.out.println(currentPlayer.getCharName() + " drew a 'Prosperity'. each player draws 2 cards.");
                currentPlayer.addCard(adventureDeck.drawCard());
                currentPlayer.addCard(adventureDeck.drawCard());
//                currentPlayer.reduceHand12(userInput);
//                askLeaveHotSeat(currentPlayer, userInput);

                for (int i = 1; i < players.length; i++) {
                    Player player = players[(currentPlayerIndex + i) % players.length];
                    System.out.println(currentPlayer.getCharName() + " drew a 'Prosperity' each player draws 2 cards");
                    player.addCard(adventureDeck.drawCard());
                    player.addCard(adventureDeck.drawCard());
//                    player.reduceHand12(userInput);
//                    askLeaveHotSeat(player, userInput);
                }
                break;
        }
        eventDeck.discard(eventCard);
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
//        userInput.nextLine();
        int currentPlayerIndex = hotSeat.getCharId() - 1;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        hotSeat = players[currentPlayerIndex];
    }

    public boolean checkWinner(Scanner userInput) {
        boolean winningQuestionMark = false;
        for (Player player : players){
            if (player.getShields() >= 7){
                System.out.println(player.getCharName() + " has 7 shields and won!");
                winningQuestionMark = true;
                winnerWinnerChickenDinner.add(player);
            }
        }
        return winningQuestionMark;
    }

    public void displayWinners(){
        for (Player player : winnerWinnerChickenDinner){
            System.out.println("Congrats on winning: " + player.getCharName() + " : " + player.getShields() + " Shields");
        }
    }
    public void playGame(){
        while (!checkWinner(scan)) {
            System.out.println("Hot Seat: " + hotSeat.getCharName());
            initializeAdventureDeck();
            eventDeck.initializeDeck();
            moveToNextPlayer(scan);
        }

        displayWinners();
    }
}