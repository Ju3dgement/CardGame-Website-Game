package mainPackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Controller
public class OutputController {
    private static StringBuilder consoleOutput = new StringBuilder();
    private static PipedInputStream pipedInputStream;
    private static PipedOutputStream pipedOutputStream;
    private static Game game;

    static {
        try {
            pipedInputStream = new PipedInputStream();
            pipedOutputStream = new PipedOutputStream(pipedInputStream);
            System.setIn(pipedInputStream); // Redirect System.in
        } catch (IOException e) {
            throw new RuntimeException("Failed to set up piped streams for System.in", e);
        }
    }

    public static void createGame() {
        game = new Game();
    }

    public static void playGame(){
        game.playGame(false);
    }

    public static void rigA1(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        // + F10
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        // + F30
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));


        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 40)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        // + F30
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        // - AXE 15
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20


        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(40)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        // + SWORD 10
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        // - AXE 15
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        // - LANCE 20
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));
        game.eventDeck.rigDeckTop(riggedCards);

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
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
        game.sortAllHand();
        game.playGame(true);
    }
    public static void rig2Winner(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 40)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 50)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(50)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(70)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));
        game.eventDeck.rigDeckTop(riggedCards);

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
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
        game.sortAllHand();
        game.playGame(true);
    }
    public static void rig1Winner(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));

        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 25)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 30)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 25)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 30)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(25)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(30)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(70)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new ECard(ECard.EventType.PLAGUE),
                new ECard(ECard.EventType.PROSPERITY),
                new ECard(ECard.EventType.QUEENS_FAVOR),
                new QCard(3));
        game.eventDeck.rigDeckTop(riggedCards);

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

                new FoeCard(25),
                new FoeCard(30),

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
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
        game.sortAllHand();
        game.playGame(true);
    }
    public static void rig0Winner(){
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 50)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 70)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));


        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 20)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 25)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 30)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 30)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 40)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(25)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(25)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(30)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(40)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 25)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 25)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 30)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard( 50)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.adventureDeck.reshuffle();

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(2));
        game.eventDeck.rigDeckTop(riggedCards);

        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(15),
                new FoeCard(10),

                new FoeCard(5),
                new FoeCard(10),
                new FoeCard(15),
                new WeaponCard("D", 5),
                new WeaponCard("D", 5),
                new WeaponCard("D", 5),
                new WeaponCard("D", 5),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new WeaponCard("H", 10)

                );
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
        game.sortAllHand();
        game.playGame(true);
    }


    public static void redirectSystemOutput() {
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) {
                consoleOutput.append((char) b);
            }
        };
        System.setOut(new PrintStream(outputStream, true));
    }

    @GetMapping("/console-output")
    @ResponseBody
    public String getConsoleOutput() {
        return consoleOutput.toString();
    }

    @PostMapping("/clear-console")
    @ResponseBody
    public void clearConsoleOutput() {
        consoleOutput.setLength(0); // Clear the console output
    }

    @PostMapping("/submit-input")
    @ResponseBody
    public void submitPlayerInput(@RequestParam String input) {
        try {
            pipedOutputStream.write((input + "\n").getBytes());
            pipedOutputStream.flush();
        } catch (IOException ignored) {
        }
    }

    @GetMapping("/player-hand/{playerId}")
    @ResponseBody
    public String getPlayerHand(@PathVariable int playerId) {
        if (game != null && playerId >= 1 && playerId <= 4) {
            game.players[playerId - 1].sortHand();
            List<Card> hand = game.players[playerId - 1].getHand();
            StringBuilder printHand = new StringBuilder();
            for (Card card : hand) {
                printHand.append(card.toString()).append(" | ");
            }

            printHand.append("\n\nShields: ").append(game.players[playerId - 1].getShields());

            if (game.players[playerId - 1].getShields() >= 7){
                printHand.append("\n\nWINNER");
            }
            return printHand.toString();
        }
        return "No data available for player " + playerId;
    }
}