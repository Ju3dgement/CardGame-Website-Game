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
}
