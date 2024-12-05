
import io.cucumber.java.en.*;
import mainPackage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class GameSteps{
    public Game game;
    @Given("Game starts")
    public void A1_Step_1() {
        game = new Game();
        game.initializeAdventureDeck();
        game.eventDeck.initializeDeck();
        game.dealInitialCards();

        game.adventureDeck.riggedClearHand(game.players[0].getHand());
        game.adventureDeck.riggedClearHand(game.players[1].getHand());
        game.adventureDeck.riggedClearHand(game.players[2].getHand());
        game.adventureDeck.riggedClearHand(game.players[3].getHand());

        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[0].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
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

        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new FoeCard(40)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("D", 5)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("S", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("H", 10)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("B", 15)));
        game.players[1].addCard(game.adventureDeck.riggedDraw(new WeaponCard("E", 30)));

        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(5)));
        game.players[2].addCard(game.adventureDeck.riggedDraw(new FoeCard(15)));
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
    }

    @And("{string} validate number cards {int}")
    public void validateNumberCards(String player, int numberCards) {
        int numberHand = stringToPlayerObject(player).getHand().size();

        assertEquals(stringToPlayerObject(player).getHand().size(), numberCards);
    }


    @And("{string} validate hand {string}")
    public void validateHand(String player, String inputtedHand) {
        StringBuilder compareString = new StringBuilder();
        List<Card> hand = stringToPlayerObject(player).getHand();
        compareString.append(hand);
        String compare = compareString.toString().replaceAll("\\W", "").replaceAll("]", "");

        inputtedHand = inputtedHand.toUpperCase().replaceAll("\\W", "");
        assertEquals(compare, inputtedHand);
    }

    @And("{string} validate shield {int}")
    public void validateShield(String player, int shields) {
        assertEquals(stringToPlayerObject(player).getShields(), shields);
    }

    @Then("{string} decline to sponsor quest")
//    @And("{string} decline to sponsor quest")
    public void askSponsorQuestNo(String player) {
        game.drawQCardInfoSetter(stringToPlayerObject(player), game.questCard);
        game.attemptSponsorship(this.game.questMakerPlayer, this.game.questCard, new Scanner("1\n"));
    }

    @Then("{string} accept to sponsor quest")
//    @And("{string} accept to sponsor quest")
    public void askSponsorQuestYes(String player) {
        game.drawQCardInfoSetter(stringToPlayerObject(player), game.questCard);
        game.attemptSponsorship(this.game.questMakerPlayer, this.game.questCard, new Scanner("0\n"));
    }

    public String createQuestInputFunction(Player player, String inputText) {
        player.sortHand();
        StringBuilder returnString = new StringBuilder();
        String[] text = inputText.split(",");
        List<Card> HardCopyHands = new ArrayList<>(player.getHand());
        for (String command : text) {
            int index = 0;
            for (Card card : HardCopyHands) {
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
    public void buildsQuest(String player, String inputText) {
        String createQuestInput = createQuestInputFunction(stringToPlayerObject(player), inputText);
        game.makeQuest(game.questMakerPlayer, game.questCard, new Scanner(createQuestInput));
//        game.makeQuest(game.questMakerPlayer, game.questCard,new Scanner("0\n6\nQuit\n1\n4\nQuit\n1\n2\n3\nQuit\n1\n2\nQuit\n"));
    }

    public Player stringToPlayerObject(String stringPlayer) {
        for (Player player : game.players) {
            if (player.toString().equalsIgnoreCase(stringPlayer)) {
                return player;
            }
        }
        return game.players[0];
    }

    @When("{string} draws a card with {string}")
    public void drawsQCard(String player, String data) {
        EventCard currentEvent = game.eventDeck.drawCard();
        Player objectPlayer = stringToPlayerObject(player);
        if (currentEvent instanceof QCard) {
            game.drawQCardInfoSetter(objectPlayer, (QCard) currentEvent);
        } else if (currentEvent instanceof ECard) {
            game.processECard((ECard) currentEvent, stringToPlayerObject(player), new Scanner(""));
        }

//        mainPackage.EventCard currentEvent = game.eventDeck.riggedDraw(new mainPackage.QCard(4));
//        game.processQCard((mainPackage.QCard) currentEvent, game.players[0], new Scanner("1\n\n0\n"));
    }

    @And("{string} accept quest discard {string}")
    public void acceptsQuestDrawsDiscards(String player, String info) {
        String scannerInput = createQuestInputFunction(stringToPlayerObject(player), info);
        String accepts = "0\n" + scannerInput;
        game.doingAStage(stringToPlayerObject(player), 0, new Scanner(accepts));
    }

    @And("{string} accept quest")
    public void acceptsQuestDrawsOnly(String player) {
        game.doingAStage(stringToPlayerObject(player), 0, new Scanner("0\n"));
        game.players[stringToIntPlayer(player)].addCard(game.adventureDeck.drawCard());
//        game.players[stringToIntPlayer(player)].sortHand();
        for (Player playerz : game.players) {
            playerz.sortHand();
        }

    }

    @And("{string} decline quest")
    public void declineQueststage(String player) {
        game.doingAStage(stringToPlayerObject(player), 0, new Scanner("1\n"));
    }

    public int stringToIntPlayer(String playerString) {
        for (Player player : game.players) {
            if (player.toString().equalsIgnoreCase(playerString)) {
                return player.getCharId() - 1;
            }
        }
        return 0;
    }

    @And("{string} builds attack {string}")
    public void attackMobs(String player, String info) {
        String scannerInput = createQuestInputFunction(stringToPlayerObject(player), info);
        game.players[stringToIntPlayer(player)].attack(new Scanner(scannerInput));
//            System.out.println("Attack");
    }

    @And("resolution stage {int}")
    public void resolution(int stage) {
        int stageValue = game.calculateStageValue(game.stageFull.get(stage - 1));
        game.resolutionFloor(stageValue);
    }

    @And("give shields stage {int}")
    public void giveShields(int hello) {
        game.earnShields(game.questCard);
    }

    @And("validate winners {string}")
    public void validateWinners(String stringOfPlayers) {
        String[] playerArray = stringOfPlayers.split(","); // Splitting the input string

        for (String playerName : playerArray) {
            boolean isWinner = false;

            for (Player player : game.winnerWinnerChickenDinner) {
                if (player.toString().equals(playerName.trim().toUpperCase())) {
                    System.out.println(player.toString() + " is a WINNER!");
                    isWinner = true;
                    break;
                }
            }

            if (!isWinner) {
                throw new AssertionError("mainPackage.Player " + playerName + " is not a winner.");
            }
        }
    }

    @And("{string} hand over 12 discard {string}")
    public void handOver12P(String player, String info) {
//        game.players[stringToIntPlayer(player)].sortHand();
        game.players[stringToIntPlayer(player)].reduceHand12(new Scanner(createQuestInputFunction(stringToPlayerObject(player), info)));
    }

    @And("{string} sponsor draws stage {int}")
    public void sponsorDraw(String player, int stage) {
        game.sponsorDraw(stringToPlayerObject(player), stage, new Scanner(""));
//        assertEquals(12, stringToPlayerObject(player).getHand().size());
    }

    @Given("game starts 2winner_game_2winner_quest")
    public void gameStart_2winner_game_2winner_quest() {
        game = new Game();
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

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new QCard(3));

        game.eventDeck.rigDeckTop(riggedCards);

        // Made it nice and simple :)
        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(5),
                new FoeCard(5),

                new FoeCard(10),
                new FoeCard(10),

                new WeaponCard("S", 10),
                new WeaponCard("S", 10),

                new WeaponCard("H", 10),
                new WeaponCard("H", 10),

                new FoeCard(10),
                new FoeCard(10),

                new WeaponCard("S", 10),
                new WeaponCard("S", 10),

                new WeaponCard("H", 10),
                new WeaponCard("H", 10)
        );
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
    }

    @Given("game starts 1winner_game_with_events")
    public void gameStart_1winner() {
        game = new Game();
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

        List<EventCard> riggedCards = Arrays.asList(
                new QCard(4),
                new ECard(ECard.EventType.PLAGUE),
                new ECard(ECard.EventType.PROSPERITY),
                new ECard(ECard.EventType.QUEENS_FAVOR),
                new QCard(3));

        game.eventDeck.rigDeckTop(riggedCards);

        // Made it nice and simple :)
        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(5),
                new FoeCard(5),


                new FoeCard(35),
                new WeaponCard("H", 10),
                new FoeCard(35),


                new FoeCard(20),
                new WeaponCard("H", 10),
                new FoeCard(20),

                new WeaponCard("S", 10),
                new WeaponCard("S", 10),
                new WeaponCard("S", 10),

                // Sponsor Draw 11 Cards
                new FoeCard(10),
                new FoeCard(10),
                new FoeCard(10),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(15),
                new FoeCard(25),
                new FoeCard(25),
                new FoeCard(25),
                // p1 should have 16 cards

                //prosperity
                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new WeaponCard("H", 10),
                new FoeCard(40),
                new FoeCard(40),
                new FoeCard(50),

                // Queens Favor
                new FoeCard(50),
                new FoeCard(70),

                //Battle 2 R 1
                new WeaponCard("S", 10),
                new WeaponCard("H", 10),
                new FoeCard(35),

                //Battle 2 R 2
                new WeaponCard("S", 10),
                new WeaponCard("B", 15),

                //Battle 2 R 3
                new FoeCard(35),
                new FoeCard(35)
        );
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);

    }

    @Given("game starts 0_winner_quest")
    public void gameStart_0winner() {
        game = new Game();
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

        List<EventCard> riggedCards = Arrays.asList(new QCard(2));
        game.eventDeck.rigDeckTop(riggedCards);

        // Made it nice and simple :)
        List<Card> riggedCardsAdventure = Arrays.asList(
                new FoeCard(5),
                new FoeCard(5),
                new FoeCard(5)
        );
        game.adventureDeck.rigDeckTop(riggedCardsAdventure);
    }
}
