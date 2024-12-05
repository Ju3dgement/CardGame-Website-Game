import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SeleniumTest {
    WebDriver driver;
    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

//    @AfterEach
//    void destroyDriver() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
    public void typeSubmit(WebElement inputBox, WebElement submitButton, String input) throws InterruptedException {
//        Thread.sleep(1000);
        inputBox.sendKeys(input);
//        Thread.sleep(1000);
        submitButton.click();
    }
    @Test
    void A1_Scenario() throws InterruptedException {
        driver.get("http://localhost:8080/");
        Thread.sleep(5000);

        WebElement p1hand = driver.findElement(By.id("output-console-1"));
        WebElement p2hand = driver.findElement(By.id("output-console-2"));
        WebElement p3hand = driver.findElement(By.id("output-console-3"));
        WebElement p4hand = driver.findElement(By.id("output-console-4"));
        WebElement inputBox = driver.findElement(By.id("player-input")); // input box
        WebElement submitButton = driver.findElement(By.id("submit-player-input")); // submit button

        typeSubmit(inputBox, submitButton, "1"); // No Sponsor
        typeSubmit(inputBox, submitButton, "0"); // P2 Sponsor
        typeSubmit(inputBox, submitButton, "0"); // F5
        typeSubmit(inputBox, submitButton, "6"); // H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "1"); // F15
        typeSubmit(inputBox, submitButton, "4"); // S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "1"); // F15
        typeSubmit(inputBox, submitButton, "2"); // D5
        typeSubmit(inputBox, submitButton, "3"); // B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "1"); // F40
        typeSubmit(inputBox, submitButton, "2"); // B15
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0"); // P1 do quest accept
        typeSubmit(inputBox, submitButton, "0"); // p3 do quest accept
        typeSubmit(inputBox, submitButton, "0"); // p4 Do quest accept
        typeSubmit(inputBox, submitButton, "0"); // p1 accept stage
        typeSubmit(inputBox, submitButton, "0"); // P1 discard F5

        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage
        typeSubmit(inputBox, submitButton, "0"); // discard F5

        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage
        typeSubmit(inputBox, submitButton, "0"); // discard F5

        typeSubmit(inputBox, submitButton, "4"); // p1 Dagger
        typeSubmit(inputBox, submitButton, "4"); // p1 Sword
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "4"); // Sword
        typeSubmit(inputBox, submitButton, "3"); // Dagger
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // Dagger
        typeSubmit(inputBox, submitButton, "5"); // Horse
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0"); // p1 next stage accept
        typeSubmit(inputBox, submitButton, "0"); // p2 next stage accept
        typeSubmit(inputBox, submitButton, "0"); // p3 next stage accept

        typeSubmit(inputBox, submitButton, "6"); // p1 Horse
        typeSubmit(inputBox, submitButton, "5"); // p1 Sword
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "8"); // p3 axe
        typeSubmit(inputBox, submitButton, "3"); // p3 sword
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "5"); // p4 horse
        typeSubmit(inputBox, submitButton, "5"); // p4 axe
        typeSubmit(inputBox, submitButton, "Quit");

        Thread.sleep(1000);
        assertTrue((p1hand.getText()).contains("F5 | F10 | F15 | F15 | F30 | H10 | B15 | B15 | L20 |"), "F5 | F10 | F15 | F15 | F30 | H10 | B15 | B15 | L20 |");
        assertTrue((p1hand.getText()).contains("Shields: 0"), "Shields: 0");

        typeSubmit(inputBox, submitButton, "0"); // p3 ask accept stage
        typeSubmit(inputBox, submitButton, "0"); // p4 ask accept stage
        typeSubmit(inputBox, submitButton, "8"); // p3 lance
        typeSubmit(inputBox, submitButton, "5"); // p3 horse
        typeSubmit(inputBox, submitButton, "3"); // p3 sword
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "6"); // p4 axe
        typeSubmit(inputBox, submitButton, "4"); // p4 sword
        typeSubmit(inputBox, submitButton, "6"); // p4 lance
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0"); // p3 ask accept stage
        typeSubmit(inputBox, submitButton, "0"); // p4 ask accept stage
        typeSubmit(inputBox, submitButton, "6"); // p3 axe
        typeSubmit(inputBox, submitButton, "5"); // p3 sword
        typeSubmit(inputBox, submitButton, "5"); // p3 lance
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // p4 dagger
        typeSubmit(inputBox, submitButton, "3"); // p4 sword
        typeSubmit(inputBox, submitButton, "4"); // p4 lance
        typeSubmit(inputBox, submitButton, "4"); // p4 excalibur
        typeSubmit(inputBox, submitButton, "Quit");

        Thread.sleep(1000);
        assertTrue((p3hand.getText()).contains("F5 | F5 | F15 | F30 | S10 |"), "F5 | F5 | F15 | F30 | S10 |");
        assertTrue((p3hand.getText()).contains("Shields: 0"), "Shields: 0");

        assertTrue((p4hand.getText()).contains("F15 | F15 | F40 | L20"), "F15 | F15 | F40 | L20");
        assertTrue((p4hand.getText()).contains("Shields: 4"), "Shields: 4");

        typeSubmit(inputBox, submitButton, "0"); // p2 discard
        typeSubmit(inputBox, submitButton, "0"); // p2 discard
        typeSubmit(inputBox, submitButton, "0"); // p2 discard
        typeSubmit(inputBox, submitButton, "0"); // p2 discard
        Thread.sleep(1000);
        assertEquals(p2hand.getText().chars().filter(ch -> ch == '|').count(), 12);

    }
}