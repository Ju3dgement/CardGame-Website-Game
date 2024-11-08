
import io.cucumber.java.en.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameSteps{
    private Game game;
    @Given("Game starts")
    public void A1_Step_1(){
        game = new Game();
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
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
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(40)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));
        game.adventureDeck.reshuffle();
    }
    @When("P1 draws quest with 4 stages and declines")
    public void A1_Step_2(){
        EventCard currentEvent = game.eventDeck.riggedDraw(new QCard(4));
        game.processQCard((QCard) currentEvent, game.players[0], new Scanner("1\n\n0\n"));
    }
    @Then("P2 creates quest")
    public void A1_Step_3(){
        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("0\n6\nQuit\n1\n4\nQuit\n1\n2\n3\nQuit\n1\n2\nQuit\n"));
    }
    @And("completes stage 1")
    public void A1_Step_4(){
        List<Card> riggedCards = Arrays.asList(new FoeCard(30), new WeaponCard("S", 10), new WeaponCard("B",15));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor1 = game.stageFull.get(0);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n0\n0\n4\n4\nQuit\n4\n3\nQuit\n3\n5\nQuit\n"), game.calculateStageValue(floor1));
    }
    @And("completes stage 2")
    public void A1_Step_5(){
        List<Card> riggedCards = Arrays.asList(new FoeCard(10), new WeaponCard("L", 20), new WeaponCard("L",20));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor2 = game.stageFull.get(1);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n0\n0\n6\n5\nQuit\n8\n3\nQuit\n5\n5\nQuit\n"), game.calculateStageValue(floor2));

        assertEquals(0, game.players[0].getShields());
        assertEquals(game.players[0].getHand().get(0).toString(), new FoeCard(5).toString());
        assertEquals(game.players[0].getHand().get(1).toString(), new FoeCard(10).toString());
        assertEquals(game.players[0].getHand().get(2).toString(), new FoeCard(15).toString());
        assertEquals(game.players[0].getHand().get(3).toString(), new FoeCard(15).toString());
        assertEquals(game.players[0].getHand().get(4).toString(), new FoeCard(30).toString());
    }
    @And("completes stage 3")
    public void A1_Step_6(){
        List<Card> riggedCards = Arrays.asList(new WeaponCard("B",15), new WeaponCard("S", 10));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor3 = game.stageFull.get(2);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n8\n5\n3\nQuit\n6\n4\n6\nQuit\n"), game.calculateStageValue(floor3));
    }
    @And("completes stage 4")
    public void A1_Step_7(){
        List<Card> riggedCards = Arrays.asList(new FoeCard(30), new WeaponCard("S", 10), new WeaponCard("B",15));
        game.adventureDeck.rigDeckTop(riggedCards);
        List<Card> floor4 = game.stageFull.get(3);
        game.doingFloor(game.activeParticipants, new Scanner("0\n0\n0\n0\n6\n5\n5\nQuit\n3\n3\n4\n4\nQuit\n"), game.calculateStageValue(floor4));
    }
    @And("game ends assert number of cards for p2")
    public void A1_Step_8(){
        game.sponsorDraw(game.questMakerPlayer, game.questCard.getStages(), new Scanner("0\n0\n0\n0\n"));
        assertEquals(12, game.players[1].getHand().size());
    }













    @Given("game starts 2winner_game_2winner_quest")
    public void game_start_2winner_game_2winner_quest(){
        game = new Game();
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(20)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
//        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
//        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
//        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard( 40)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard( 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));

        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
//        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
//        game.players[3].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("L", 20)));
        game.players[3].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));
        game.adventureDeck.reshuffle();
    }


}
