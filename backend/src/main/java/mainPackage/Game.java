package mainPackage;

import java.util.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
public class Game {
    public Deck adventureDeck;
    public Player[] players;
    public EventDeck eventDeck;
    public Player hotSeat;
    public Scanner scan;
    public List<List<Card>> stageFull = new ArrayList<>();
    public QCard questCard;
    public Player questMakerPlayer;
    public List<Player> doQuestList = new ArrayList<>();
    public List<Player> activeParticipants =  new ArrayList<>();
    public Set<Player> winnerWinnerChickenDinner = new HashSet<>();

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

    public void sortAllHand(){
        for (Player player : players){
            player.sortHand();
        }
    }

    public ECard processECard(ECard eventCard, Player currentPlayer, Scanner userInput) {
        switch (eventCard.getEventType()) {
            case PLAGUE:
                System.out.println("Drew 'Plague' " + currentPlayer.getCharName() + " lost 2 shields");
                currentPlayer.removeShield();
                currentPlayer.removeShield();
                break;
            case QUEENS_FAVOR:
                System.out.println("Drew 'Queens Favor' " + currentPlayer.getCharName() + " drawing 2 cards");
                currentPlayer.addCard(adventureDeck.drawCard());
                currentPlayer.addCard(adventureDeck.drawCard());

                break;
            case PROSPERITY:
//                int currentPlayerIndex = currentPlayer.getCharId() - 1;
//                System.out.println(currentPlayer.getCharName() + " drew a 'Prosperity'. each player draws 2 cards.");
//                currentPlayer.addCard(adventureDeck.drawCard());
//                currentPlayer.addCard(adventureDeck.drawCard());
//
//
//                for (int i = 1; i < players.length; i++) {
//                    Player player = players[(currentPlayerIndex + i) % players.length];
//                    player.addCard(adventureDeck.drawCard());
//                    player.addCard(adventureDeck.drawCard());
//
//                }
//                break;

                for (Player player : this.players){
                    player.addCard(adventureDeck.drawCard());
                    player.addCard(adventureDeck.drawCard());
                }
        }
        sortAllHand();
        eventDeck.discard(eventCard);
        return eventCard;
    }


    public void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 12; i++) {
                player.addCard(adventureDeck.drawCard());
            }
        }
        sortAllHand(); // *
    }

    public void moveToNextPlayer(Scanner userInput) {
        System.out.println(hotSeat.getCharName() + " turn ended press <return> NEXT: " + hotSeat.getNextPlayerName());
        userInput.nextLine();
        int currentPlayerIndex = hotSeat.getCharId() - 1;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        hotSeat = players[currentPlayerIndex];
    }

    public boolean checkWinner(Scanner userInput) {
        boolean winningQuestionMark = false;
        for (Player player : players) {
            if (player.getShields() >= 7) {
                System.out.println(player.getCharName() + " has 7 shields and won!");
                winningQuestionMark = true;
                winnerWinnerChickenDinner.add(player);
            }
        }
        return winningQuestionMark;
    }

    public void displayWinners() {
        for (Player player : winnerWinnerChickenDinner) {
            System.out.println("Congrats on winning: " + player.getCharName() + " : " + player.getShields() + " Shields");
        }
    }


    public boolean sponsorQuest(Player player, QCard questCard, Scanner userInput) {
        player.printHand();
        System.out.println("QCard: " + questCard.toString());
        System.out.println(player.getCharName() + " do you want to sponsor this card (0 = Yes, 1 = No): ");
        int userInputText = userInput.nextInt();
        clearScreen();
        return userInputText == 0;
    }

    public boolean attemptSponsorship(Player currentPlayer, QCard questCard, Scanner userInput) {
        if (sponsorQuest(currentPlayer, questCard, userInput)) {
            this.eventDeck.discard(questCard);
            this.questCard = questCard;
            this.questMakerPlayer = currentPlayer;
            return true;

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

    public void drawQCardInfoSetter(Player player, QCard questCard){
        this.questCard = questCard;
        this.questMakerPlayer = player;
    }

    public void drawECardInfoSetter(Player player, QCard questCard){
    }

    public boolean processQCard(QCard questCard, Player currentPlayer, Scanner userInput) {
        this.questCard = questCard;
        questMakerPlayer = currentPlayer;
        if (attemptSponsorship(currentPlayer, questCard, userInput)) {
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

    public void makeQuest(Player questMakerPlayer, QCard questCard, Scanner userInput) {
        List<List<Card>> stageFull = new ArrayList<>();

        int previousStage = 0;
        for (int i = 0; i < this.questCard.getStages(); i++) {
            List<Card> stage = new ArrayList<>();
            questMakerPlayer.printHand();
            System.out.print("Stage : " + (i + 1) + " | Pick a 'FOE' card:");

            // ADDED
            String foePick = userInput.next();
            if (foePick.equalsIgnoreCase("Quit")) {
                System.out.println("A stage cannot be empty or is not a foe card");
                continue;
            }

            int foeCardPick = Integer.parseInt(foePick);
            Card foeCard = questMakerPlayer.getHand().get(foeCardPick);

            questMakerPlayer.removeCardHand(foeCard);
            stage.add(foeCard);
            questMakerPlayer.sponsorCardDiscarded++;
            Set<String> usedWeapons = new HashSet<>();
            while (true) {
                questMakerPlayer.printHand();
                System.out.println("Chose weapon card ('Quit' to continue): ");
                String weaponHand = userInput.next();
                if (weaponHand.equalsIgnoreCase("Quit")) {
                    System.out.println("Stage " + (i + 1) + " cards: ");
                    System.out.print(foeCard.toString() + ", ");
                    for (String nameWeapon : usedWeapons) {
                        System.out.print(nameWeapon + ", ");
                    }
                    break;
                }

                Card weaponCard = questMakerPlayer.getHand().get(Integer.parseInt(weaponHand));
                if (usedWeapons.contains(weaponCard.toString())) {
                    System.out.println(weaponCard + " - can't use same weapon type");
                    continue;
                } else {
                    stage.add(weaponCard);
                    usedWeapons.add(weaponCard.toString());
                    questMakerPlayer.removeCardHand(weaponCard);
                    questMakerPlayer.sponsorCardDiscarded++;
                }
                if (questMakerPlayer.getHand().isEmpty()) {
                    break;
                }
            }
            int stageValue = calculateStageValue(stage);
            stageFull.add(stage);

            if (stageValue <= previousStage) {
                System.out.println("Insufficient value for this stage, stage value lower than previous, pick cards again from Stage 1");
                for (List<Card> listCards : stageFull) {
                    for (Card card : listCards) {
                        questMakerPlayer.addCard(card);
                        questMakerPlayer.sponsorCardDiscarded--;
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


    public int calculateStageValue(List<Card> stage) {
        int stagevalue = 0;
        for (Card card : stage) {
            stagevalue += card.value;
        }
        return stagevalue;
    }


    public void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
    }

    public void askLeaveHotSeat(Player player, Scanner userInput) {
        System.out.println(player.getCharName() + " please leave the 'Hot Seat' for the next player <" + player.getNextPlayerName() + "> :");
        userInput.nextLine();
        clearScreen();
    }


    public boolean askDoQuest(Player playerWhoMadeQuest, Scanner userInput) {
        List<Player> doQuestList = new ArrayList<>();

        for (Player player : getOtherPlayers(playerWhoMadeQuest)) {
            clearScreen();
            System.out.println("Stages: " + questCard.toString());
            System.out.println(player.getCharName() + " do quest? (0 = Yes | 1 = No):");
            int doQuest = userInput.nextInt();
            if (doQuest == 0) {
                doQuestList.add(player);
            }
        }

        this.doQuestList = doQuestList;
        if (doQuestList.isEmpty()) {
            System.out.println("Nobody wanted to do quest");
            return false;
        }
        return true;
    }

    public boolean askParticipateStage(Scanner userInput, Player player) {
        System.out.println(player.getCharName() + " Do you want to participate on this stage(0 = Yes, 1 = No): ");
        int response = userInput.nextInt();
        return response == 0;
    }
    public void sponsorDraw(Player currentPlayer, int numberStages, Scanner userInput){

        while (currentPlayer.sponsorCardDiscarded != 0){
            currentPlayer.addCard(adventureDeck.drawCard());
            currentPlayer.sponsorCardDiscarded--;
        }
        for (int i = 0; i < numberStages; i++){
            currentPlayer.addCard(adventureDeck.drawCard());
        }
        System.out.println(currentPlayer.getCharName() + " " + "Sponsor draw");
        sortAllHand();
    }
    public boolean doingAStage(Player player, int stageValue, Scanner userInput) {
        if (askParticipateStage(userInput, player)) {
            return true;
        }
        return false;
    }


    public void resolutionFloor(int stageValue){
        List<Player> deleteActive = new ArrayList<>();
        for (Player player : this.activeParticipants){
            if (player.getCurrentDamage() < stageValue){
                deleteActive.add(player);
            }
        }

        for (Player player : deleteActive){
            this.activeParticipants.remove(player);
        }
    }

    public boolean doingFloor(List<Player> activeParticipants, Scanner userInput, int stageValue){
        List<Player> deleteActive = new ArrayList<>();
        for (Player player : activeParticipants) {
            if (doingAStage(player, stageValue, userInput)){
                System.out.println("Drew a card...");
                player.addCard(adventureDeck.drawCard());
                player.reduceHand12(userInput);
                continue;
            }
            deleteActive.add(player);
        }

        for (Player player : deleteActive){
            this.activeParticipants.remove(player);
        }
        //
        for (Player player : this.activeParticipants) {
            player.attack(userInput);
        }
        resolutionFloor(stageValue);

        for (Player player : this.activeParticipants) {
            System.out.println("Resolution + " + player.getCharName() + " : SUCCESS");
        }

        return false;
    }

    public void earnShields(QCard questCard){
        for (Player shieldGiver : this.activeParticipants) {
            System.out.println(shieldGiver.getCharName() + " got " + questCard.getStages() + " shield(s)");
            shieldGiver.addShield(questCard.getStages());
            if (shieldGiver.getShields() >= 7){
                this.winnerWinnerChickenDinner.add(shieldGiver);
            }
        }


    }
    public void doQuest(List<List<Card>> stageFull, List<Player> doQuestList, int shields, Scanner userInput) {
        List<Player> activeParticipants = new ArrayList<>(doQuestList);
        this.activeParticipants = activeParticipants;
//        this.activeParticipants = new ArrayList<>(doQuestList);

        for (List<Card> stage : stageFull) {
            int stageValue = calculateStageValue(stage);
            if (activeParticipants.isEmpty()) {
                return;
//                break;
            }

            boolean floorCompleted = doingFloor(this.activeParticipants, userInput, stageValue);
            if (!floorCompleted) {
                activeParticipants = this.activeParticipants;
            }

        }
        earnShields(this.questCard);
    }

//    public void doQuest(List<List<mainPackage.Card>> stageFull, List<mainPackage.Player> doQuestList, int shields, Scanner userInput) {
//        List<mainPackage.Player> activeParticipants = new ArrayList<>(doQuestList);
//        List<mainPackage.Player> winLoseResult = new ArrayList<>();
//        for (List<mainPackage.Card> stage : stageFull) {
//            int stageValue = calculateStageValue(stage);
//            if (activeParticipants.isEmpty()) {
//                break;
//            }
//            Iterator<mainPackage.Player> iterator = activeParticipants.iterator();
//            while (iterator.hasNext()) {
//                mainPackage.Player participant = iterator.next();
//                if (doingAStage2(participant, stageValue, userInput)) {
//                    iterator.remove();
//                    participant.setWinLose(true);
//                    winLoseResult.add(participant);
//                } else {
//                    participant.setWinLose(false);
//                    winLoseResult.add(participant);
//                }
//                askLeaveHotSeat(participant, userInput);
//            }
//
//            System.out.println("Stage Value:" + stageValue);
//            boolean continueNextStage = false;
//            for (mainPackage.Player printResult : winLoseResult){
//                if (printResult.getWinLose()) {
//                    System.out.println(printResult.getCharName() + " Hit with a value of " + printResult.getCurrentDamage() + " and WON!");
//                    continueNextStage = true;
//                } else {
//                    System.out.println(printResult.getCharName() + " Hit with a value of " + printResult.getCurrentDamage() + " and LOST!");
//                }
//            }
//            if (!continueNextStage){
//                System.out.println("Everybody died, exiting... <Return>: ");
//                userInput.nextLine();
//                return;
//            }
//
//
//            winLoseResult.clear();
//            System.out.println("<Return> to continue:");
//            userInput.nextLine();
//        }
//        for (mainPackage.Player shieldGiver : activeParticipants) {
//            System.out.println(shieldGiver.getCharName() + " got " + shields + " shield(s)");
//            shieldGiver.addShield(shields);
//        }
//    }


    public Map<String, List<String>> getAllPlayerHands() {
        Map<String, List<String>> playerHands = new HashMap<>();
        for (Player player : players) {
            // Convert player's cards to a list of their names or descriptions
            List<String> hand = new ArrayList<>();
            for (Card card : player.getHand()) {
                hand.add(card.toString()); // Assume `mainPackage.Card` class has a `getName()` method
            }
            playerHands.put(player.getCharName(), hand);
        }
        return playerHands;
    }

    public void playGame(boolean rig){
        if (!rig) {
            initializeAdventureDeck();
            eventDeck.initializeDeck();
            dealInitialCards();
        }

        while (!checkWinner(scan)) {
            System.out.println("Hot Seat: " + hotSeat.getCharName());
            EventCard currentEvent = eventDeck.drawCard();
            if (currentEvent instanceof ECard) {
                ECard EventGotCard = processECard((ECard) currentEvent, hotSeat, scan);
                switch (EventGotCard.getEventType()){
                    case PLAGUE:
                        break;
                    case PROSPERITY:
                        for (Player trimHand : players) {
                            trimHand.reduceHand12(scan);
                        }
                    case QUEENS_FAVOR:
                        hotSeat.reduceHand12(scan);
                }

                System.out.println("Press <Return> to continue");
                scan.nextLine();

            } else if (currentEvent instanceof QCard) {
                if (processQCard((QCard) currentEvent, hotSeat, scan)){
                    makeQuest(questMakerPlayer, questCard, scan);
                    if (askDoQuest(questMakerPlayer, scan)) {
                        doQuest(stageFull, doQuestList, this.questCard.getStages(), scan);
                        sponsorDraw(questMakerPlayer, questCard.getStages(), scan);
                        questMakerPlayer.reduceHand12(scan);
                    }
                } else{
                    System.out.println("Nobody wanted to sponsor: " + this.questCard);
                }
            }
            moveToNextPlayer(scan);
        }


    }

    public void rigA1(){
        initializeAdventureDeck();
        eventDeck.initializeDeck();

        adventureDeck.riggedClearHand(players[0].getHand());
        adventureDeck.riggedClearHand(players[1].getHand());
        adventureDeck.riggedClearHand(players[2].getHand());
        adventureDeck.riggedClearHand(players[3].getHand());

        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        // + F10
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        // + F30
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));


        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 15)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 15)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 40)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 15)));
        // + F30
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        // - AXE 15
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20


        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(5)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(40)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        // - AXE 15
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));
        eventDeck.rigDeckTop(riggedCards);

        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(30),
                new WeaponCard("S", 10),
                new WeaponCard("B", 15),

                new FoeCard(10),
                new WeaponCard("L", 20),
                new WeaponCard("L", 20),

                new WeaponCard("B", 15),
                new WeaponCard("S", 10),

                new FoeCard(30),
                new WeaponCard("L", 20),

                new FoeCard(70)
        );
        adventureDeck.rigDeckTop(riggedCardsAdventure);

        playGame(true);
    }

    public void rigA2(){
        initializeAdventureDeck();
        eventDeck.initializeDeck();

        adventureDeck.riggedClearHand(players[0].getHand());
        adventureDeck.riggedClearHand(players[1].getHand());
        adventureDeck.riggedClearHand(players[2].getHand());
        adventureDeck.riggedClearHand(players[3].getHand());

        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(10)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(10)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 40)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 50)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));

        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(50)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(70)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));
        eventDeck.rigDeckTop(riggedCards);

        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(40),
                new FoeCard(10),

                new FoeCard(10),
                new FoeCard(30),

                new FoeCard(30),
                new FoeCard(15),

                new FoeCard(15),
                new FoeCard(20),

                new FoeCard(5), // Sponsor draw
                new FoeCard(10),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(20),
                new FoeCard(20),
                new FoeCard(20),
                new FoeCard(20),
                new FoeCard(25),
                new FoeCard(25),
                new FoeCard(30),

                new WeaponCard("D", 5),
                new WeaponCard("D", 5),

                new FoeCard(15),
                new FoeCard(15),

                new FoeCard(25),
                new FoeCard(25),

                new FoeCard(20), // Sponsor Draw
                new FoeCard(20),
                new FoeCard(25),
                new FoeCard(30),
                new WeaponCard("S", 10),
                new WeaponCard("B", 15),
                new WeaponCard("B", 15),
                new WeaponCard("L", 20)

        );
        adventureDeck.rigDeckTop(riggedCardsAdventure);
        sortAllHand();
        playGame(true);
    }

    public void rigOneWinner(){
        initializeAdventureDeck();
        eventDeck.initializeDeck();

        adventureDeck.riggedClearHand(players[0].getHand());
        adventureDeck.riggedClearHand(players[1].getHand());
        adventureDeck.riggedClearHand(players[2].getHand());
        adventureDeck.riggedClearHand(players[3].getHand());

        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard( 5)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(10)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(10)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(15)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(20)));
        players[0].addCard(adventureDeck.riggedDraw(new FoeCard(20)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        players[0].addCard(adventureDeck.riggedDraw(new WeaponCard("D", 5)));

        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 25)));
        players[1].addCard(adventureDeck.riggedDraw(new FoeCard( 30)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[1].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 25)));
        players[2].addCard(adventureDeck.riggedDraw(new FoeCard( 30)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[2].addCard(adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(25)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(30)));
        players[3].addCard(adventureDeck.riggedDraw(new FoeCard(70)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        players[3].addCard(adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new ECard(ECard.EventType.PLAGUE),
                new ECard(ECard.EventType.PROSPERITY),
                new ECard(ECard.EventType.QUEENS_FAVOR),
                new QCard(3));
        eventDeck.rigDeckTop(riggedCards);

        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(10),
                new FoeCard(20),

                new FoeCard(15),
                new FoeCard(5),
                new FoeCard(25),

                new FoeCard(5),
                new FoeCard(10),
                new FoeCard(20),

                new FoeCard(5),
                new FoeCard(10),
                new FoeCard(20),

                new FoeCard(5), // Sponsor draw
                new FoeCard(5),
                new FoeCard(10),
                new FoeCard(10),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(15),

                new FoeCard(25),
                new FoeCard(25),
                new WeaponCard("H", 10),
                new WeaponCard("S", 10),
                new WeaponCard("B", 15),
                new FoeCard(40),
                new WeaponCard("D", 5),
                new WeaponCard("D", 5),

                new FoeCard(30),
                new FoeCard(25),

                new WeaponCard("B", 15),
                new WeaponCard("H", 10),
                new FoeCard(50),

                new WeaponCard("S", 10),
                new WeaponCard("S", 10),

                new FoeCard(40),
                new FoeCard(50),

                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new FoeCard(35)

        );
        adventureDeck.rigDeckTop(riggedCardsAdventure);
        sortAllHand();
        playGame(true);


    }

}

