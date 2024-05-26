package src;


import javafx.application.Application;
import javafx.stage.Stage;

/* This class will be the main class. It will be responsible for the flow of the game
+ creating all constant elements in GUI (everything except characters)
+ initialise/call things from other classes.
+ generateBoard()
    Creates UI elements based on the current CharactersInPlat (either init or filtered version)
+ Connect buttons and stuff to things from Game
    - Showing all characters
    - Show all questions in a dropdown
    - Buttons (restart, guess, select question...)
 */

public class GuessWho extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game currentGame;

        // Build constant UI elements:

        // New game on start():
        currentGame = new Game();
        // + generateBoard()

        // Restart button (should assign new Game to currentGame + generateBoard())

        // Testing:
        System.out.println("Secret person is:" + currentGame.getSecretPerson());
        for (Character character : currentGame.charactersInPlay) {
            System.out.println(character.name);
        }
    }

    public void generateBoard() {
        // create UI for board
    }


} // end of Main

// generateBoard()
//      Create GUI elements for all characters currently in CharactersInPlay
// Call methods from Game:
// Connected to buttons:
//      - updateGuessCounter (also in GUI)
//      - checkQuestion() + generateBoard (new)
//      - isGuessCorrect()/Checks if guess is correct
// A win/lose display of some kind, depending on what happens in isGuessCorrect()
