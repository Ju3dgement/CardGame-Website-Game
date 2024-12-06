package selenium_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

public class ZeroWinnerTest {
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
    void Zero_Winner_Quest() throws InterruptedException {
        driver.get("http://localhost:8080/");
        Thread.sleep(5000);

        WebElement p1hand = driver.findElement(By.id("output-console-1"));
        WebElement p2hand = driver.findElement(By.id("output-console-2"));
        WebElement p3hand = driver.findElement(By.id("output-console-3"));
        WebElement p4hand = driver.findElement(By.id("output-console-4"));
        WebElement inputBox = driver.findElement(By.id("player-input")); // input box
        WebElement submitButton = driver.findElement(By.id("submit-player-input")); // submit button

        typeSubmit(inputBox, submitButton, "0"); // p1 accept Q2
        typeSubmit(inputBox, submitButton, "0"); // p1 F50
        typeSubmit(inputBox, submitButton, "1"); // p1 D5
        typeSubmit(inputBox, submitButton, "2"); // p1 S10
        typeSubmit(inputBox, submitButton, "3"); // p1 H10
        typeSubmit(inputBox, submitButton, "4"); // p1 B15
        typeSubmit(inputBox, submitButton, "5"); // p1 L20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 F70
        typeSubmit(inputBox, submitButton, "0"); // p1 D5
        typeSubmit(inputBox, submitButton, "0"); // p1 S10
        typeSubmit(inputBox, submitButton, "0"); // p1 H10
        typeSubmit(inputBox, submitButton, "0"); // p1 B15
        typeSubmit(inputBox, submitButton, "0"); // p1 L20
//        typeSubmit(inputBox, submitButton, "Quit"); // NO need to quit since out of cards will auto do it


        typeSubmit(inputBox, submitButton, "0"); // p2 accept Q2
        typeSubmit(inputBox, submitButton, "0"); // p2 accept Q2
        typeSubmit(inputBox, submitButton, "0"); // p3 accept Q2
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p2 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 1
        typeSubmit(inputBox, submitButton, "3"); // p3 discard F15
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 1
        typeSubmit(inputBox, submitButton, "2"); // p4 discard F10

        typeSubmit(inputBox, submitButton, "11"); // p2 E30
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "Quit"); // p3 plays nothing
        typeSubmit(inputBox, submitButton, "Quit"); // p4 plays nothing

        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F10

        Thread.sleep(3000);
        assertTrue((p1hand.getText()).contains("F15 | D5 | D5 | D5 | D5 | S10 | S10 | S10 | H10 | H10 | H10 | H10 |"), "F15 | D5 | D5 | D5 | D5 | S10 | S10 | S10 | H10 | H10 | H10 | H10 |");
        assertTrue((p1hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p1hand.getText().chars().filter(ch -> ch == '|').count(), 12);
        assertFalse(p1hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p2hand.getText()).contains("F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F30 | F30 | F40 |"), "F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F30 | F30 | F40 |");
        assertTrue((p2hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p2hand.getText().chars().filter(ch -> ch == '|').count(), 11);
        assertFalse(p2hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p3hand.getText()).contains("F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F25 | F30 | F40 | L20 |"), "F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F25 | F30 | F40 | L20 |");
        assertTrue((p3hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p3hand.getText().chars().filter(ch -> ch == '|').count(), 12);
        assertFalse(p3hand.getText().contains("WINNER"), "WINNER");

        assertTrue((p4hand.getText()).contains("F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F25 | F30 | F50 | E30 |"), "F5 | F5 | F10 | F15 | F15 | F20 | F20 | F25 | F25 | F30 | F50 | E30 |");
        assertTrue((p4hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p4hand.getText().chars().filter(ch -> ch == '|').count(), 12);
        assertFalse(p4hand.getText().contains("WINNER"), "WINNER");


    }
}