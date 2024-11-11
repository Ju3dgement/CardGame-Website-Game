
import io.cucumber.java.en.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

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
        // 4 should be at the top of deck, so it draws first then the second would be 3
        List<QCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));
        game.eventDeck.rigDeckTop(riggedCards);


        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(30),
                new WeaponCard("S", 10),
                new WeaponCard("B",15),

                new FoeCard(10),
                new WeaponCard("L", 20),
                new WeaponCard("L",20),

                new WeaponCard("B",15),
                new WeaponCard("S", 10),

                new FoeCard(30),
                new WeaponCard("L", 20)
                );

        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
    }

    @And ("{string} validate hand {string}")
    public void validateHand(String player, String inputtedHand){
        StringBuilder compareString = new StringBuilder();
        List<Card> hand = stringToPlayerObject(player).getHand();
        compareString.append(hand);
        String compare = compareString.toString().replaceAll("\\W", "").replaceAll("]","");

        inputtedHand = inputtedHand.toUpperCase().replaceAll("\\W", "");
        assertEquals(compare, inputtedHand);
    }
    @And ("{string} validate shield {int}")
    public void validateShield(String player, int shields){
        assertEquals(stringToPlayerObject(player).getShields(), shields);
    }
    @Then("{string} decline to sponsor quest")
//    @And("{string} decline to sponsor quest")
    public void askSponsorQuestNo(String player){
        game.drawQCardInfoSetter(stringToPlayerObject(player), game.questCard);
        game.attemptSponsorship(this.game.questMakerPlayer, this.game.questCard, new Scanner("1\n"));
    }
    @Then("{string} accept to sponsor quest")
//    @And("{string} accept to sponsor quest")
    public void askSponsorQuestYes(String player){
        game.drawQCardInfoSetter(stringToPlayerObject(player), game.questCard);
        game.attemptSponsorship(this.game.questMakerPlayer, this.game.questCard, new Scanner("0\n"));
    }

    public String createQuestInputFunction(Player player, String inputText){
        StringBuilder returnString = new StringBuilder();
        String[] text = inputText.split(",");
        List<Card> HardCopyHands = new ArrayList<>(player.getHand());
        for (String command : text){
            int index = 0;
            for (Card card : HardCopyHands){
                if (command.toUpperCase().equals(card.toString())) {
                    returnString.append(index);
                    returnString.append("\n");
                    break;
                }
                index++;
            }
            if (command.equals("Quit") || command.equals("quit")) {
                returnString.append("Quit");
                returnString.append("\n");
                continue;
            }
            HardCopyHands.remove(index);
        }
        return returnString.toString();
    }
    @And("{string} builds quest {string}")
    public void buildsQuest(String player, String inputText){
        String createQuestInput = createQuestInputFunction(stringToPlayerObject(player), inputText);
        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner(createQuestInput));
//        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("0\n6\nQuit\n1\n4\nQuit\n1\n2\n3\nQuit\n1\n2\nQuit\n"));
    }

    public Player stringToPlayerObject(String stringPlayer){
        for (Player player : game.players){
            if (player.toString().equalsIgnoreCase(stringPlayer)){
                return player;
            }
        }
        return game.players[0];
    }
    @When("{string} draws a card with {string}")
    public void drawsQCard(String player, String data){
        EventCard currentEvent = game.eventDeck.drawCard();
        Player objectPlayer = stringToPlayerObject(player);
        if (currentEvent instanceof QCard) {
            game.drawQCardInfoSetter(objectPlayer, (QCard) currentEvent);
        }

//        EventCard currentEvent = game.eventDeck.riggedDraw(new QCard(4));
//        game.processQCard((QCard) currentEvent, game.players[0], new Scanner("1\n\n0\n"));
    }
    @And("{string} accept quest discard {string}")
    public void acceptsQuestDrawsDiscards(String player, String info){
        String scannerInput = createQuestInputFunction(stringToPlayerObject(player), info);
        String accepts = "0\n" + scannerInput;
        game.doingAStage(stringToPlayerObject(player), 0, new Scanner(accepts));
    }

    @And("{string} accept quest")
    public void acceptsQuestDrawsOnly(String player){
        game.doingAStage(stringToPlayerObject(player), 0, new Scanner("0\n"));
    }

    public int stringToIntPlayer(String playerString){
        for (Player player : game.players){
            if (player.toString().equalsIgnoreCase(playerString)){
                return player.getCharId() - 1;
            }
        }
        return 0;
    }
    @And("{string} builds attack {string}")
        public void attackMobs(String player, String info){
            String scannerInput = createQuestInputFunction(stringToPlayerObject(player), info);
            game.players[stringToIntPlayer(player)].attack(new Scanner(scannerInput));
        }

    @And("resolution stage {int}")
    public void resolution(int stage){
        int stageValue = game.calculateStageValue(game.stageFull.get(stage-1));
        game.resolutionFloor(stageValue);
    }

    @And("give shields stage {int}")
    public void giveShields(int hello){
        game.earnShields(game.questCard);
        System.out.println("OK");
    }

    @And("{string} sponsor draws stage {int}")
    public void sponsorDraw(String player, int stage){
        game.sponsorDraw(stringToPlayerObject(player), stage, new Scanner(""));
        assertEquals(12, stringToPlayerObject(player).getHand().size());
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







}
