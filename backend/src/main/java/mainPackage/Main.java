package mainPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("https://localhost:8080");
        OutputController.redirectSystemOutput();
        SpringApplication.run(Main.class, args);
        OutputController.createGame();

        // COMMENT OUT THE ONE YOU WANT ONLY 1 AT A TIME
        OutputController.playGame(); // RandomGame
//        OutputController.rigA1(); // A1_scenario
//        OutputController.rig2Winner(); // 2winner_game_2winner_quest
//        OutputController.rig1Winner(); // 1winner_game_with_events
//        OutputController.rig0Winner(); // 0_winner_quest




        // ===== This is just for debugging nothing to see here ====
//        Game game = new Game();
//        game.rigA1();
//        game.rigA2();
//        game.rigOneWinner();
//        game.rigZeroWinner();
//        game.playGame(false);
    }
}
