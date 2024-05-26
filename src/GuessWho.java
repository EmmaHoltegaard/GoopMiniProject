package src;


import javafx.application.Application;
import javafx.scene.control.Button;
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
        Game game = new Game();

        // Build all constant UI elements:
            // Menu of questions
            // Ask-button
            // Container for the board

            // restart button:
        Button restartGame = new Button("Restart");
        restartGame.setOnAction(event -> {
            game.newGame();
            generateBoard();
        });


        // New game on start():
        game.newGame();
        generateBoard();


        // Testing:
        System.out.println("Secret person is:" + game.getSecretPerson());
        for (Character character : game.charactersInPlay) {
            System.out.println(character.name);
        }
    }

    public void generateBoard() {
        // create UI for board specifically
        // Image + button for each.
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
