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
        userInput.nextLine();
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



    public boolean sponsorQuest(Player player, QCard questCard, Scanner userInput) {
        player.printHand();
        System.out.println("QCard: " + questCard.toString());
        System.out.println(player.getCharName() + " do you want to sponsor this card (0 = Yes, 1 = No): ");
        int userInputText = userInput.nextInt();
        if (userInputText == 0) {
            return true;
        } else {
//            askLeaveHotSeat(player, userInput);
            return false;
        }
    }

    public boolean attemptSponsorship(Player player, QCard questCard, Scanner userInput) {
        if (sponsorQuest(player, questCard, userInput)) {
//            if (player.checkEnoughFoe(questCard.getStages())) {
                return true;
//            } else {
//                showNotEnoughFoeMessage(questCard);
//                return false;
//            }
        }
        return false;
    }
    public Player[] getOtherPlayers(Player currentPlayer) {
        Player[] otherPlayers = new Player[players.length - 1];
        int index = 0;
        for (Player player : players) {
            if (!player.equals(currentPlayer)) {
                otherPlayers[index] = player;
                index++;
            }
        }
        return otherPlayers;
    }
    public boolean processQCard(QCard questCard, Player currentPlayer, Scanner userInput) {
        this.questCard = questCard;
        questMakerPlayer = currentPlayer;
        if (attemptSponsorship(currentPlayer, questCard, userInput)) {
            eventDeck.discard(questCard);
            this.questCard = questCard;
            questMakerPlayer = currentPlayer;
            return true;
        }
        for (Player player : getOtherPlayers(currentPlayer)) {
            if (attemptSponsorship(player, questCard, userInput)) {
                this.questCard = questCard;
                questMakerPlayer = player;
                return true;
            }
        }
        eventDeck.discard(questCard);
        return false;
    }

    public void makeQuest(Player questMakerPlayer, QCard questCard, Scanner userInput){
        List<List<Card>> stageFull = new ArrayList<>();

        int previousStage = 0;
        for (int i = 0; i < this.questCard.getStages(); i++) {
            List<Card> stage = new ArrayList<>();
            questMakerPlayer.printHand();
            System.out.print("Stage : " + (i + 1) + " | Pick a 'FOE' card:");

            // ADDED
            String foePick = userInput.next();
            if (foePick.equalsIgnoreCase("Quit")){
                System.out.println("A stage cannot be empty");
                continue;
            }

            int foeCardPick = Integer.parseInt(foePick);
            Card foeCard = questMakerPlayer.getHand().get(foeCardPick);

            questMakerPlayer.removeCardHand(foeCard);
            stage.add(foeCard);

            Set<String> usedWeapons = new HashSet<>();
            while (true){
                questMakerPlayer.printHand();
                System.out.println("Chose weapon card ('Quit' to continue): ");
                String weaponHand = userInput.next();
                if (weaponHand.equalsIgnoreCase("Quit")){
                    System.out.println("Stage " + (i+1) + " cards: ");
                    for (String nameWeapon : usedWeapons){
                        System.out.print(nameWeapon + ", ");
                    }

                    break;
                }

                Card weaponCard = questMakerPlayer.getHand().get(Integer.parseInt(weaponHand));
                if (usedWeapons.contains(weaponCard.toString())){
                    System.out.println(weaponCard + " - can't use same weapon type");
                    continue;
                } else {
                    stage.add(weaponCard);
                    usedWeapons.add(weaponCard.toString());
                    questMakerPlayer.removeCardHand(weaponCard);
                }
                if (questMakerPlayer.getHand().isEmpty()){
                    break;
                }
            }
            int stageValue = calculateStageValue(stage);
            stageFull.add(stage);

            if (stageValue <= previousStage){
                System.out.println("Insufficient value for this stage, stage value lower than previous, pick cards again from Stage 1");
                for (List<Card> listCards : stageFull){
                    for (Card card : listCards){
                        questMakerPlayer.addCard(card);
                    }
                }
                this.stageFull = new ArrayList<>();

                i = -1;
                previousStage = 0;
                continue;
            }
            previousStage = stageValue;
        }
        System.out.println(questMakerPlayer.getCharName() + " Stage has been setup");
        this.questCard = questCard;
        this.questMakerPlayer = questMakerPlayer;
        this.stageFull = stageFull;

        this.activeParticipants = new ArrayList<>(Arrays.asList(getOtherPlayers(questMakerPlayer)));
    }

    public int calculateStageValue(List<Card> stage){
        int stagevalue = 0;
        for (Card card: stage){
            stagevalue += card.value;
        }
        return stagevalue;
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