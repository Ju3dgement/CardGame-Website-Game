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
    public void RESP_1_Test_1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        assertEquals(100, game.adventureDeck.cards.size());
        assertEquals(17, game.eventDeck.eventCards.size());
    }

    @Test
    @DisplayName("Test check cards inside")
    public void RESP_1_Test_2(){
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
    public void RESP_2_Test_1(){
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
    public void RESP_3_Test_1(){
        p1.addShield(8);
        p3.addShield(2);
        p4.addShield(66);
        assertTrue(game.checkWinner());
    }

//    @Test
//    @DisplayName("Test no winner at all at end turn")
//    public void RESP_3_Test_2(){
//        p1.addShield(3);
//        p2.addShield(2);
//        p3.addShield(6);
//        p3.addShield(1);
//        assertFalse(game.checkWinner());
//    }


}
