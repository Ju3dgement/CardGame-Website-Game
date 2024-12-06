package mainPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        OutputController.redirectSystemOutput();
        SpringApplication.run(Main.class, args);
        OutputController.createGame();

        // COMMENT OUT THE ONE YOU WANT
//        OutputController.playGame();
//        OutputController.rigA1();
        OutputController.rig2Winner();
//        OutputController.rig1Winner();
//        OutputController.rig0Winner();


//        Game game = new Game();
//        game.rigA1();
//          game.rigA2();
//        game.playGame(false);
    }
}
