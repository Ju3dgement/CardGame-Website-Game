import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class OneWinnerTest {
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
        Thread.sleep(1000);
        inputBox.sendKeys(input);
        Thread.sleep(1000);
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

        typeSubmit(inputBox, submitButton, "0"); // p1 accept sponsor Q4
        typeSubmit(inputBox, submitButton, "0"); // p1 F5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "1"); // p1 F10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "2"); // p1 F15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // p1 F20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept Q4
        typeSubmit(inputBox, submitButton, "0"); // p3 accept Q4
        typeSubmit(inputBox, submitButton, "0"); // p4 accept Q4
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p2 discard F5
        typeSubmit(inputBox, submitButton, "0"); //p3 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p3 discard F10
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p4 discard F20
        typeSubmit(inputBox, submitButton, "2"); // p2 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "2"); // p3 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // p4 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 accept stage 2
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 2
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 2


        typeSubmit(inputBox, submitButton, "5"); // p2 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "5"); // p3 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "6"); // p4 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 3
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 3
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 3
        typeSubmit(inputBox, submitButton, "7"); // p2 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "7"); // p3 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "8"); // p4 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 4
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 4
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 4
        typeSubmit(inputBox, submitButton, "9"); // p2 L20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "9"); // p3 L20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "10"); // p4 L20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "1"); // p1 discard F10
        typeSubmit(inputBox, submitButton, "1"); // p1 discard F10
        typeSubmit(inputBox, submitButton, ""); // RETURN/CONTINUE
        typeSubmit(inputBox, submitButton, ""); // RETURN/CONTINUE
        //Prosperity
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p2 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p3 discard F5
        typeSubmit(inputBox, submitButton, "0"); // p4 discard F20
        typeSubmit(inputBox, submitButton, ""); // RETURN/CONTINUE
        //Queen's favor
        typeSubmit(inputBox, submitButton, "3"); // p4 discard F25
        typeSubmit(inputBox, submitButton, "3"); // p4 discard F30
        typeSubmit(inputBox, submitButton, ""); // RETURN/CONTINUE
        // Q3
        typeSubmit(inputBox, submitButton, "0"); // p1 accept sponsor Q3
        typeSubmit(inputBox, submitButton, "0"); // p1 F15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p1 F15
        typeSubmit(inputBox, submitButton, "7"); // p1 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "3"); // p1 F20
        typeSubmit(inputBox, submitButton, "5"); // p1 D5
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept Q3
        typeSubmit(inputBox, submitButton, "0"); // p3 accept Q3
        typeSubmit(inputBox, submitButton, "0"); // p4 accept Q3
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // discard F5
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p3 discard F10
        typeSubmit(inputBox, submitButton, "0"); // p4 accept stage 1
        typeSubmit(inputBox, submitButton, "0"); // p4 discard F20
        typeSubmit(inputBox, submitButton, "8"); // p2 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "9"); // p3 B15
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "9"); // p4 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 2
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 2
        typeSubmit(inputBox, submitButton, "9"); // p2 B15
        typeSubmit(inputBox, submitButton, "7"); // p2 H10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "9"); // p3 B15
        typeSubmit(inputBox, submitButton, "5"); // p3 S10
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "0"); // p2 accept stage 3
        typeSubmit(inputBox, submitButton, "0"); // p3 accept stage 3
        typeSubmit(inputBox, submitButton, "4"); // p2 S10
        typeSubmit(inputBox, submitButton, "8"); // p2 L20
        typeSubmit(inputBox, submitButton, "Quit");
        typeSubmit(inputBox, submitButton, "10"); // p3 E30
        typeSubmit(inputBox, submitButton, "Quit");


        typeSubmit(inputBox, submitButton, "0"); // p1 discard F15
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F15
        typeSubmit(inputBox, submitButton, "0"); // p1 discard F15

        Thread.sleep(1000);
        assertTrue((p1hand.getText()).contains("F25 | F25 | F35 | D5 | D5 | S10 | S10 | S10 | S10 | H10 | H10 | H10 |"), "F25 | F25 | F35 | D5 | D5 | S10 | S10 | S10 | S10 | H10 | H10 | H10 |");
        assertTrue((p1hand.getText()).contains("Shields: 0"), "Shields: 0");
        assertEquals(p1hand.getText().chars().filter(ch -> ch == '|').count(), 12);

        Thread.sleep(1000);
        assertTrue((p2hand.getText()).contains("F15 | F25 | F30 | F40 | S10 | S10 | S10 | H10 | E30 |"), "F10 | F15 | F15 | F25 | F30 | F40 | F50 | L20 | L20 |");
        assertTrue((p2hand.getText()).contains("Shields: 5"), "Shields: 5");
        assertEquals(p2hand.getText().chars().filter(ch -> ch == '|').count(), 9);

        Thread.sleep(1000);
        assertTrue((p3hand.getText()).contains("F10 | F25 | F30 | F40 | F50 | S10 | S10 | H10 | H10 | L20 |"), "F10 | F25 | F30 | F40 | F50 | S10 | S10 | H10 | H10 | L20 |");
        assertTrue((p3hand.getText()).contains("Shields: 7"), "Shields: 7");
        assertEquals(p3hand.getText().chars().filter(ch -> ch == '|').count(), 10);

        Thread.sleep(1000);
        assertTrue((p4hand.getText()).contains("F25 | F25 | F30 | F50 | F70 | D5 | D5 | S10 | S10 | B15 | L20 |"), "F25 | F25 | F30 | F50 | F70 | D5 | D5 | S10 | S10 | B15 | L20 |");
        assertTrue((p4hand.getText()).contains("Shields: 4"), "Shields: 4");
        assertEquals(p4hand.getText().chars().filter(ch -> ch == '|').count(), 11);


    }
}