import org.junit.jupiter.api.*;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
public class TestCases {
    private Game game;
    private Player player;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;

    @BeforeEach
    void makeNew(){
        game = new Game();
        player = game.players[0];

        p1 = game.players[0];
        p2 = game.players[1];
        p3 = game.players[2];
        p4 = game.players[3];

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Test initalization of event and adventure deck")
    public void RESP_1_TEST_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        assertEquals(100, game.adventureDeck.cards.size());
        assertEquals(17, game.eventDeck.eventCards.size());
    }

    @Test
    @DisplayName("Test check cards inside")
    public void RESP_1_TEST_2(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();

        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("D", 5)),6 );
        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("H", 10)),12 );
        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("S", 10)),16 );
        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("B", 15)),8 );
        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("L", 20)),6 );
        assertEquals(game.adventureDeck.countSpecificCard(new WeaponCard("E", 30)),2 );

        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(5)),8);
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(10)),7 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(15)),8 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(20)),7 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(25)),7 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(30)),4 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(35)),4 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(40)),2 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(50)),2 );
        assertEquals(game.adventureDeck.countSpecificCard(new FoeCard(70)),1 );

        assertEquals(game.eventDeck.countSpecificCard(new QCard(2)), 3 );
        assertEquals(game.eventDeck.countSpecificCard(new QCard(3)), 4 );
        assertEquals(game.eventDeck.countSpecificCard(new QCard(4)), 3 );
        assertEquals(game.eventDeck.countSpecificCard(new QCard(5)), 2 );
        assertEquals(game.eventDeck.countSpecificCard(new ECard(ECard.EventType.PLAGUE)), 1 );
        assertEquals(game.eventDeck.countSpecificCard(new ECard(ECard.EventType.QUEENS_FAVOR)), 2 );
        assertEquals(game.eventDeck.countSpecificCard(new ECard(ECard.EventType.PROSPERITY)), 2);
    }
    @Test
    @DisplayName("Test if 12 cards sent to each player and check deck at appropiate size after")
    public void RESP_2_TEST_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        for (Player player : game.players){
            assertEquals(12, player.getHand().size());
        }
        assertEquals(52, game.adventureDeck.cards.size());
    }

    @Test
    @DisplayName("Test winner at end turn")
    public void RESP_3_TEST_1(){
        p1.addShield(8);
        p3.addShield(2);
        p4.addShield(66);
        assertTrue(game.checkWinner(new Scanner("")));
    }

    @Test
    @DisplayName("Test no winner at all at end turn")
    public void RESP_3_TEST_2(){
        p1.addShield(3);
        p2.addShield(2);
        p3.addShield(6);
        p4.addShield(1);
        assertFalse(game.checkWinner(new Scanner("")));
    }
    @Test
    @DisplayName("Test display winner and terminates")
    public void RESP_4_TEST_1(){
        p1.addShield(3);
        p2.addShield(9);
        p3.addShield(8);
        p4.addShield(1);
        game.checkWinner(new Scanner(""));
        game.displayWinners();
        String output = outputStream.toString();
        assertTrue(output.contains("Congrats on winning: P2"));
        assertTrue(output.contains("Congrats on winning: P3"));
    }

    @Test
    @DisplayName("The game ‘draws’ (i.e., displays) the next event card")
    public void RESP_5_TEST_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        EventCard card = game.eventDeck.drawCard();
        if (card instanceof EventCard) {
            assert(true);
        } else{
            assert(false);
        }
    }

    @Test
    @DisplayName("Test drawing a 'Plague Card Event'")
    public void RESP_6_TEST_1() {
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        String inputScanner = "\n";
        Player player = game.players[0];
        player.addShield(4);
        int initialShields = player.getShields();

        ECard plagueCard = new ECard(ECard.EventType.PLAGUE);
        game.processECard(plagueCard, player, new Scanner(inputScanner));

        assertEquals(initialShields - 2, player.getShields());
    }
    @Test
    @DisplayName("Test drawing a 'Queens Favor Card Event'")
    public void RESP_6_TEST_2() {
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        Player player = game.players[0];
        int initialHandSize = player.getHand().size();

        ECard queensFavorCard = new ECard(ECard.EventType.QUEENS_FAVOR);
        game.processECard(queensFavorCard, player, new Scanner(System.in));

        assertEquals(14, initialHandSize + 2, player.getHand().size());
    }
    @Test
    @DisplayName("Test drawing a 'Prosperity Card Event'")
    public void RESP_6_TEST_3() {
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        Player[] players = game.players;
        int[] initialHandSizes = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            initialHandSizes[i] = players[i].getHand().size();
        }

        ECard prosperityCard = new ECard(ECard.EventType.PROSPERITY);
        game.processECard(prosperityCard, players[0], new Scanner(System.in));

        for (int i = 0; i < players.length; i++) {
            assertEquals(14, initialHandSizes[i] + 2, players[i].getHand().size());
        }
    }

    @Test
    @DisplayName("Test game indicate turn ended")
    public void RESP_7_TEST_1() {
        String previousHotSeat = p1.getCharName();
        String input = "\n";
        Scanner autoInput = new Scanner(input);
        game.moveToNextPlayer(autoInput);
        String output = outputStream.toString();
        assertTrue(output.contains("turn ended"));
        assertFalse(Boolean.parseBoolean(previousHotSeat), game.hotSeat.getCharName()); // Agree when P1 != P2
        assertEquals(p2.getCharName(), game.hotSeat.getCharName()); // P2 == P2
    }

    @Test
    @DisplayName("The game computes n, the number of cards to discard by that player")
    public void RESP_8_TEST_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();
        ECard queensFavorCard = new ECard(ECard.EventType.QUEENS_FAVOR);
        game.processECard(queensFavorCard, p1, new Scanner(System.in));

        assertEquals(14, p1.getHand().size());

        int excessCards = player.getHand().size() - 12;
        int numberOfTrim = player.numberTrimNeeded();
        assertEquals(excessCards, numberOfTrim);
    }

    @Test
    @DisplayName("displays the hand of the player that needs to be discarded")
    public void RESP_9_TEST_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));

        int numberOfTrim = player.numberTrimNeeded();

        String input = "0\n";
        for (int i = 0; i < numberOfTrim; i++) {
            input += input;
        }
        player.reduceHand12(new Scanner(input));

        String output = outputStream.toString();
        assertTrue(output.contains("'s hand"));
    }

    @Test
    @DisplayName("Prompt uaser to pick valid position")
    public void RESP_9_TEST_2(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        // 13 cards
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));
        game.players[0].addCard(new WeaponCard("S", 10));

        int numberOfTrim = player.numberTrimNeeded();

        String input = "0\n";  // Entering a valid position
        for (int i = 0; i < numberOfTrim; i++) {
            input += input;
        }

        player.reduceHand12(new Scanner(input));

        String output = outputStream.toString();
        assertTrue(output.contains("pick to discard(int):"));
    }
    @Test
    @DisplayName("Test delets the card and display the new hand")
    public void RESP_9_TEST_3(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        // 14 cards added
        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard( 20));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("L", 20));

        int numberOfTrim = p1.numberTrimNeeded();

        String input = "0\n0\n";
        for (int i = 0; i < numberOfTrim; i++) {
            input += input;
        }

        player.reduceHand12(new Scanner(input));

        assertEquals(12, player.getHand().size());
    }

    @Test
    @DisplayName("Test ask user if want to sponsor quest")
    void RESP_10_TEST_01(){
        QCard card = new QCard(3);
        String input = "0\n";
        Scanner autoInput = new Scanner(input);
        game.sponsorQuest(player, card, autoInput);
        boolean assertion = outputStream.toString().contains("do you want to sponsor this card (0 = Yes, 1 = No):");
        assertTrue(assertion);
    }

    @Test
    @DisplayName("A sponsor has been found")
    void RESP_11_TEST_01(){
        QCard card = new QCard(3);
        String input = "0\n";
        Scanner autoInput = new Scanner(input);
        boolean result = game.processQCard(card, p1, autoInput);
        assertTrue(result);
    }
    @Test
    @DisplayName("All decline sponsor quest")
    void RESP_11_TEST_02(){
        QCard card = new QCard(3);
        String input = "1\n1\n1\n1\n";
        Scanner autoInput = new Scanner(input);
        boolean result = game.processQCard(card, p1, autoInput);
        assertFalse(result);
    }
    @Test
    @DisplayName("Sets up a valid quest")
    void RESP_12_TEST_01(){
        String questSetup = "0\n1\nQuit\n";
        Scanner input = new Scanner(questSetup);
        QCard questCard = new QCard(2);
        game.questMakerPlayer = p1;

        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard( 20));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("L", 20));

        game.processQCard(questCard, game.questMakerPlayer, input);

        // 5FOE -> dagger -> Quit -> 5FOE -> BattleAxe -> Quit
        Scanner makeQuestInput = new Scanner("0\n4\nQuit\n0\n9\nQUit\n");
        game.makeQuest(game.questMakerPlayer, questCard, makeQuestInput);

        String output = outputStream.toString();
        assertTrue(output.contains("Pick a 'FOE' card:"));
        assertEquals(questCard, game.questCard);
        assertFalse(game.stageFull.isEmpty());
    }

    @Test
    @DisplayName("until the selected card is valid ")
    void RESP_13_TEST_01(){
        String questSetup = "0\n1\nQuit\n";
        Scanner input = new Scanner(questSetup);
        QCard questCard = new QCard(2);
        game.questMakerPlayer = p1;

        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard( 20));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("L", 20));
        game.processQCard(questCard, game.questMakerPlayer, input);

        // 5FOE -> dagger -> Quit -> 5FOE -> BattleAxe -> Quit
        Scanner makeQuestInput = new Scanner("0\n4\nQuit\n0\n9\nQUit\n");
        game.makeQuest(game.questMakerPlayer, questCard, makeQuestInput);
        String output = outputStream.toString();
        assertTrue(output.contains("Stage 1 cards: "));
    }

    @Test
    @DisplayName("Test A stage cannot be empty ")
    void RESP_13_TEST_02(){
        String questSetup = "0\n1\nQuit\n";
        Scanner input = new Scanner(questSetup);
        QCard questCard = new QCard(2);
        game.questMakerPlayer = p1;

        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard(15));
        p1.addCard(new FoeCard( 20));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("S", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("H", 10));
        p1.addCard(new WeaponCard("B", 15));
        game.processQCard(questCard, game.questMakerPlayer, input);

        // 5FOE -> dagger -> Quit -> 5FOE -> BattleAxe -> Quit
        Scanner makeQuestInput = new Scanner("Quit\n0\n4\nQuit\n0\n8\nQUit\n");
        game.makeQuest(game.questMakerPlayer, questCard, makeQuestInput);
        String output = outputStream.toString();
        assertTrue(output.contains("A stage cannot be empty"));
    }

    @Test
    @DisplayName("Test insufficent value previous stage ")
    void RESP_13_TEST_03(){
        String questSetup = "0\n1\nQuit\n";
        Scanner input = new Scanner(questSetup);
        QCard questCard = new QCard(2);
        game.questMakerPlayer = p1;

        p1.addCard(new FoeCard( 5));
        p1.addCard(new FoeCard( 10));
        p1.addCard(new FoeCard(15));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("B", 15));
        game.processQCard(questCard, game.questMakerPlayer, input);

        // 10FOE -> Quit -> 5FOE -> QUIT(INSUFF) -> <Start stage 1> -> 5FOE -> Quit -> 10FOE -> QUIT
        Scanner makeQuestInput = new Scanner("1\nQuit\n0\nQuit\n0\nQuit\n0\nQuit\n");
        game.makeQuest(game.questMakerPlayer, questCard, makeQuestInput);
        String output = outputStream.toString();
        assertTrue(output.contains("Insufficient value for this stage"));
    }

    @Test
    @DisplayName("displays the hand when attacking")
    void RESP_14_TEST_01(){
        game.questMakerPlayer = p1;
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("E", 30));
        p1.addCard(new WeaponCard("E", 30));

        Scanner attackInput = new Scanner("0\nQuit\n");
        p1.attack(attackInput);
        String output = outputStream.toString();
        assertTrue(output.contains("Pick your weapon(s) card"));
    }

    @Test
    @DisplayName("repeat show error while attacking")
    void RESP_15_TEST_01(){
        game.questMakerPlayer = p1;
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("E", 30));
        p1.addCard(new WeaponCard("E", 30));

        Scanner attackInput = new Scanner("0\n0\n3\nQuit");
        p1.attack(attackInput);
        String output = outputStream.toString();
        assertTrue(output.contains("Weapon already used!"));
    }
    @Test
    @DisplayName("Quit is Entered and weapons are displayed")
    void RESP_15_TEST_02(){
        game.questMakerPlayer = p1;
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("D", 5));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("B", 15));
        p1.addCard(new WeaponCard("E", 30));
        p1.addCard(new WeaponCard("E", 30));

        Scanner attackInput = new Scanner("0\nQuit\n");
        p1.attack(attackInput);
        String output = outputStream.toString();
        assertTrue(output.contains("Pick your weapon(s) card"));
    }

    @Test
    @DisplayName("A-TEST JP-Scenario")
    public void A_TEST_JP_Scenario(){
        // 1)
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        // 2)
        game.adventureDeck.riggedClearHand(p1.getHand());
        game.adventureDeck.riggedClearHand(p2.getHand());
        game.adventureDeck.riggedClearHand(p3.getHand());
        game.adventureDeck.riggedClearHand(p4.getHand());

        p1.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p1.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        // + F10
        p1.addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        p1.addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        // + F30
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        p1.addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));


        p2.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p2.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p2.addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        p2.addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        p2.addCard(game.adventureDeck.riggedDraw(new FoeCard( 40)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        p2.addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        p3.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p3.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p3.addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        p3.addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        // + F30
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        // - AXE 15
        p3.addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20


        p4.addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        p4.addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        p4.addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        p4.addCard(game.adventureDeck.riggedDraw(new FoeCard(40)));
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        // - AXE 15
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20
        p4.addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.adventureDeck.reshuffle();

        // 3) 4)
        EventCard currentEvent = game.eventDeck.riggedDraw(new QCard(4));
        String autoInput = "1\n\n0\n";
        Scanner autoInputScanner = new Scanner(autoInput);
        game.processQCard((QCard) currentEvent, p1, autoInputScanner);

        // 5)
        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("0\n6\nQuit\n1\n4\nQuit\n1\n2\n3\nQuit\n1\n2\nQuit\n"));

        // 6)
        List<Card> riggedCards = Arrays.asList(new FoeCard(30), new WeaponCard("S", 10), new WeaponCard("B",15));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor1 = game.stageFull.get(0);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n0\n0\n4\n4\nQuit\n4\n3\nQuit\n3\n5\nQuit\n"), game.calculateStageValue(floor1));

        //7)
        riggedCards = Arrays.asList(new FoeCard(10), new WeaponCard("L", 20), new WeaponCard("L",20));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor2 = game.stageFull.get(1);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n0\n0\n6\n5\nQuit\n8\n3\nQuit\n5\n5\nQuit\n"), game.calculateStageValue(floor2));

        assertEquals(0, p1.getShields());
        assertEquals(p1.getHand().get(0).toString(), new FoeCard(5).toString());
        assertEquals(p1.getHand().get(1).toString(), new FoeCard(10).toString());
        assertEquals(p1.getHand().get(2).toString(), new FoeCard(15).toString());
        assertEquals(p1.getHand().get(3).toString(), new FoeCard(15).toString());
        assertEquals(p1.getHand().get(4).toString(), new FoeCard(30).toString());
        assertEquals(p1.getHand().get(5).toString(), new WeaponCard("H", 10).toString());
        assertEquals(p1.getHand().get(6).toString(), new WeaponCard("B", 15).toString());
        assertEquals(p1.getHand().get(7).toString(), new WeaponCard("B", 15).toString());
        assertEquals(p1.getHand().get(8).toString(), new WeaponCard("L", 20).toString());

        // 8)
        riggedCards = Arrays.asList(new WeaponCard("B",15), new WeaponCard("S", 10));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor3 = game.stageFull.get(2);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n8\n5\n3\nQuit\n6\n4\n6\nQuit\n"), game.calculateStageValue(floor3));

        // 9)
        riggedCards = Arrays.asList(new FoeCard(30), new WeaponCard("L", 20));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor4 = game.stageFull.get(3);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n6\n5\n5\nQuit\n3\n3\n4\n4\nQuit\n"), game.calculateStageValue(floor4));

        assertEquals(0, p3.getShields());
        assertEquals(p3.getHand().get(0).toString(), new FoeCard(5).toString());
        assertEquals(p3.getHand().get(1).toString(), new FoeCard(5).toString());
        assertEquals(p3.getHand().get(2).toString(), new FoeCard(15).toString());
        assertEquals(p3.getHand().get(3).toString(), new FoeCard(30).toString());
        assertEquals(p3.getHand().get(4).toString(), new WeaponCard("H", 10).toString());

//        assertEquals(4, p4.getShields());
        assertEquals(p4.getHand().get(0).toString(), new FoeCard(15).toString());
        assertEquals(p4.getHand().get(1).toString(), new FoeCard(15).toString());
        assertEquals(p4.getHand().get(2).toString(), new FoeCard(40).toString());
        assertEquals(p4.getHand().get(3).toString(), new WeaponCard("L", 20).toString());


        //10) 16 CARDS
        game.sponsorDraw(game.questMakerPlayer, game.questCard.getStages(), new Scanner("0\n0\n0\n0\n"));
        assertEquals(12, p2.getHand().size());

    }



    @Test
    @DisplayName("Nothing")
    public void a2(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));
        game.adventureDeck.reshuffle();

        EventCard currentEvent = game.eventDeck.riggedDraw(new QCard(4));
        game.processQCard((QCard) currentEvent, game.players[0], new Scanner("0\n"));

        // Built quest stage value 10/15/20/25 and stage 1 has 1 foe and 0 weapon
        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("2\nQuit\n0\n4\nQuit\n1\nQuit\n0\n0\n3\nQuit\n"));

        List<Card> riggedCards = Arrays.asList(new FoeCard( 5), new FoeCard( 5), new FoeCard( 5));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor1 = game.stageFull.get(0);
        // P2 Accepts floor req, draws F5 ~ Discard F5 == 2 0\n
        // P3 Accepts floor req, draws F5 ~ Discard F5 == 2 0\n
        // P4 Accepts floor req, draws F5 ~ Discard F5 == 2 0\n
        // P2 Uses Dagger + Sword to attack = 0\n0\nQuit\n
        // P3 Uses Dagger to attack = 3\nQuit\n
        // P4 Uses Dagger + Sword to attack = 1\n2\nQuit\n
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n0\n0\n0\n0\nQuit\n3\nQuit\n1\n2\nQuit\n"), game.calculateStageValue(floor1));

        // P2 Accepts floor 1 req, draws F10 = 0\n
        // P4 Accepts floor 1 req, draws F10 = 0\n
        // P2 Uses Sword + Horse to attack = 1\n3\nQuit\n
        // P4 Uses Sword + Horse to attack = 3\n4\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new FoeCard( 10), new FoeCard( 10)));
        List<Card> floor2 = game.stageFull.get(1);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n1\n3\nQuit\n3\n4\nQuit\n"), game.calculateStageValue(floor2));


        // P2 Accepts floor 3 req, draws S10 = 0\n
        // P4 Accepts floor 3 req, draws S10 = 0\n
        // P2 Uses Sword + Horse to attack = 1\n2\nQuit\n
        // P4 Uses Sword + Horse to attack = 3\n3\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new WeaponCard("S", 10), new WeaponCard("S", 10)));
        List<Card> floor3 = game.stageFull.get(2);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n1\n2\nQuit\n3\n3\nQuit\n"), game.calculateStageValue(floor3));


        // P2 Accepts floor 4 req, draws H10 = 0\n
        // P4 Accepts floor 4 req, draws H10 = 0\n
        // P2 Uses Sword + BattleAxe to attack = 1\n3\nQuit\n
        // P4 ExeBlade to attack = 7\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new WeaponCard("H", 10), new WeaponCard("H", 10)));
        List<Card> floor4 = game.stageFull.get(3);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n1\n3\nQuit\n8\nQuit\n"), game.calculateStageValue(floor4));

        // ====== P2 and P4 earns 4 shields ======
        game.earnShields((QCard)currentEvent);


        game.moveToNextPlayer(new Scanner("\n"));
        // P2 Declines = 1\n
        // Ask user hand P3 = \n
        // P3 Accepts = 0\n
        currentEvent = game.eventDeck.riggedDraw(new QCard(3));
        game.processQCard((QCard) currentEvent, game.hotSeat, new Scanner("1\n1\n\n0\n"));

        // Built quest stage value 5/10/15
        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("0\nQuit\n0\nQuit\n0\nQuit\n"));

        // P1 Declines = 1\n
        // P2 Accepts + draw F10 = 0\n
        // P4 Accepts + draw F10 = 0\n
        // P2 Uses Sword = 2\nQuit\n
        // P4 Uses Sword = 4\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new FoeCard( 10), new FoeCard( 10)));
        floor1 = game.stageFull.get(0);
        game.doingFloor(game.activeParticipants, new Scanner("1\n0\n0\n2\nQuit\n4\nQuit\n"), game.calculateStageValue(floor1));

        // P2 Accepts + draw Sword = 0\n
        // P4 Accepts + draw Sword = 0\n
        // P2 Uses Sword + Horse = 2\n2\nQuit\n
        // P4 Uses Dagger + Sword = 3\n3\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new WeaponCard("S", 10), new WeaponCard("S", 10)));
        floor2 = game.stageFull.get(1);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n2\n2\nQuit\n3\n3\nQuit\n"), game.calculateStageValue(floor2));


        // P2 Accepts + draw Horse = 0\n
        // P4 Accepts + draw Horse = 0\n
        // P2 Uses Horse + BattleAxe + Lance = 2\n2\n3\nQuit\n
        // P4 Uses Sword + Horse + BattleAxe + Lance + ExeBlade = 3\n3\n3\n3\n3\nQuit\n
        game.adventureDeck.rigDeckTop(Arrays.asList(new WeaponCard("H", 10), new WeaponCard("H", 10)));
        floor3 = game.stageFull.get(2);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n2\n2\n3\nQuit\n3\n3\n3\n3\n3\nQuit\n"), game.calculateStageValue(floor3));

        game.earnShields((QCard)currentEvent);
        System.out.println("DONE");
        assertEquals(game.players[1].getShields(), 7);
        assertEquals(game.players[3].getShields(), 7);
        assertTrue(game.checkWinner(game.scan));
    }
}
