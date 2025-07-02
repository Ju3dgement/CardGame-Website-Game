# Quest of the round tableop game: https://boardgamegeek.com/boardgame/360/quests-of-the-round-table 

Summary:
This project is a turn-based, quest-driven multiplayer game inspired by Arthurian legends, initially developed with a text-based interface using a Test-Driven Development (TDD) approach. The game logic is fully decoupled from the interface, allowing for seamless transition to a web-based version. It includes a variety of quest and event cards, with a focus on structured scenario-based testing using tools like Cucumber for behavior-driven development. The latest iteration transforms the game into a web interface where players can interact in separate browser windows on the same machine. Automated UI and regression testing are performed using Selenium to ensure game stability and functionality across interactions.

Card Game – Local Setup Guide (Windows Only)
Note: This setup currently works only on Windows. Compatibility with macOS or Linux is not guaranteed or tested.

Project Structure Navigation
Navigate to:

css
Copy
Edit
backend/src/main/java/[mainPackage]/Main.java
Choose a Scenario to Run:

Open Main.java.

You will see 4 scenarios and 1 random game.

Uncomment only one of them to run that specific scenario.

How to Run the Game
Make sure you've uncommented your desired scenario in Main.java.

Run Main.java to start the backend logic for the card game.

Selenium Testing
Navigate to:

swift
Copy
Edit
backend/src/test/selenium_test/java/selenium_test/
Run the .java file inside this directory to execute Selenium-based automated testing on your card game web interface.


- Java 17+ (or compatible JDK version)
- Selenium WebDriver
- Maven or Gradle for dependency management (if applicable)
- (Optional) ChromeDriver or any browser driver setup in your PATH





