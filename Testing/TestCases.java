package Testing;

import Main.*;
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

}
