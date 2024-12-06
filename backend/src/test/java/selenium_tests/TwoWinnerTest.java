package selenium_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

public class TwoWinnerTest {
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
        Thread.sleep(750);
        inputBox.sendKeys(input);
        Thread.sleep(750);
        submitButton.click();
    }
    @Test
    void A1_Scenario_Test() throws InterruptedException {
        driver.get("http://localhost:8080/");
        Thread.sleep(5000);

        WebElement p1hand = driver.findElement(By.id("output-console-1"));
        WebElement p2hand = driver.findElement(By.id("output-console-2"));
        WebElement p3hand = driver.findElement(By.id("output-console-3"));
        WebElement p4hand = driver.findElement(By.id("output-console-4"));
        WebElement inputBox = driver.findElement(By.id("player-input")); // input box
        WebElement submitButton = driver.findElement(By.id("submit-player-input")); // submit button

        typeSubmit(inputBox, submitButton, "0"); // p1 Yes Sponsor
        typeSubmit(inputBox, submitButton, "0"); // p1 F5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 F5
        typeSubmit(inputBox, submitButton, "4"); // p1 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 F10
        typeSubmit(inputBox, submitButton, "3"); // p1 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 F10
        typeSubmit(inputBox, submitButton, "3"); // p1 B15
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0");
        typeSubmit(inputBox, submitButton, "0");
        typeSubmit(inputBox, submitButton, "0");
        typeSubmit(inputBox, submitButton, "0");
        typeSubmit(inputBox, submitButton, "0"); // p2 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage
        typeSubmit(inputBox, submitButton, "0"); // p3 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage
        typeSubmit(inputBox, submitButton, "0"); // p4 discard F10

        typeSubmit(inputBox, submitButton, "5"); // p2 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "Quit"); // P3 picks notihng
        typeSubmit(inputBox, submitButton, "5"); // p4 horse
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 2
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 2

        typeSubmit(inputBox, submitButton, "3"); // p2 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // p4 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 3
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 3
        typeSubmit(inputBox, submitButton, "6"); // p2 H10
        typeSubmit(inputBox, submitButton, "4"); // p2 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "6"); // p4 H10
        typeSubmit(inputBox, submitButton, "4"); // p4 S10
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 4
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 4

        typeSubmit(inputBox, submitButton, "5"); // p2 S10
        typeSubmit(inputBox, submitButton, "5"); // p2 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "5"); // p4 S10
        typeSubmit(inputBox, submitButton, "5"); // p4 B15
        typeSubmit(inputBox, submitButton, "Quit");

        // assert p1 has 16 cards
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F10
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F15
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F15

        typeSubmit(inputBox, submitButton, "1"); // p2 declines to sponsor Q3 quest
        typeSubmit(inputBox, submitButton, "1"); // p1 declines to sponsor Q3 quest ******

        typeSubmit(inputBox, submitButton, "0"); // p3 Decides to sponsor quest
        typeSubmit(inputBox, submitButton, "0"); // p3 F5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p3 F5
        typeSubmit(inputBox, submitButton, "2"); // p3 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p3 F5
        typeSubmit(inputBox, submitButton, "3"); // p3 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "1"); // P1 declines to do quest
        typeSubmit(inputBox, submitButton, "0"); // p2 accept Q3 quest
        typeSubmit(inputBox, submitButton, "0"); // p4 accept Q3 quest
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 1
        typeSubmit(inputBox, submitButton, "5"); // p2 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "5"); // p4 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 2
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 2
        typeSubmit(inputBox, submitButton, "6"); // p2 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "6"); // p4 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 3
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 3
        typeSubmit(inputBox, submitButton, "9"); // p2 E30
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "9"); // p4 E30
        typeSubmit(inputBox, submitButton, "Quit");

        typeSubmit(inputBox, submitButton, "1"); // p1 discard F20
        typeSubmit(inputBox, submitButton, "1"); // p1 discard F25
        typeSubmit(inputBox, submitButton, "1"); // p1 discard F30

        Thread.sleep(3000);
        assertTrue((p1hand.getText()).contains("F15 | F15 | F20 | F20 | F20 | F20 | F25 | F25 | F30 | H10 | B15 | L20 |"), "F15 | F15 | F20 | F20 | F20 | F20 | F25 | F25 | F30 | H10 | B15 | L20 |");
        assertTrue((p1hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p1hand.getText().chars().filter(ch -> ch == '|').count(), 12);
        assertFalse(p1hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p2hand.getText()).contains("F10 | F15 | F15 | F25 | F30 | F40 | F50 | L20 | L20 |"), "F10 | F15 | F15 | F25 | F30 | F40 | F50 | L20 | L20 |");
        assertTrue((p2hand.getText()).contains("Shields: 7"), "Shields: 7");
        assertEquals(p2hand.getText().chars().filter(ch -> ch == '|').count(), 9);
        assertTrue(p2hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p3hand.getText()).contains("F20 | F40 | D5 | D5 | S10 | H10 | H10 | H10 | H10 | B15 | B15 | L20 |"), "F20 | F40 | D5 | D5 | S10 | H10 | H10 | H10 | H10 | B15 | B15 | L20 |");
        assertTrue((p3hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p3hand.getText().chars().filter(ch -> ch == '|').count(), 12);
        assertFalse(p3hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p4hand.getText()).contains("F15 | F15 | F20 | F25 | F30 | F50 | F70 | L20 | L20 |"), "F15 | F15 | F20 | F25 | F30 | F50 | F70 | L20 | L20 |");
        assertTrue((p4hand.getText()).contains("Shields: 7"), "Shields: 7");
        assertEquals(p4hand.getText().chars().filter(ch -> ch == '|').count(), 9);
        assertTrue(p4hand.getText().contains("WINNER"), "WINNER");

    }
}