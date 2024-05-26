package src;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* This class will be the main class. It will be responsible for connecting UI to Game functionality
+ creating all constant elements in GUI (everything except characters)
+ initialise/call things from other classes.
+ generateBoard()
    Creates UI elements based on the current CharactersInPlat (either init or filtered version)
+ Connect buttons and stuff to things from Game
    - Showing all characters
    - Show all questions in a dropdown
    - Buttons (restart, guess, select question...)
 */

// Build all constant UI elements:
// Menu of questions
// Ask-button
// Container for the board

public class GuessWho extends Application {

    //Instance variables:
    Game currentGame;

    // start()

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create root layout:
        BorderPane root = new BorderPane();

        // Create Menu container (left) + add to root
        VBox menuContainer = createMenuContainer(); // Vertical box
        root.setLeft(menuContainer);

        // Create Board container (right) + add to root
        GridPane boardContainer = createBoardContainer();
        root.setCenter(boardContainer);

        // New game on start():
        currentGame = new Game();
        generateBoard();

        // Testing:
        System.out.println("Secret person is:" + currentGame.getSecretPerson());
        for (Character character : currentGame.charactersInPlay) {
            System.out.println(character.name);
        }

        // Set the scene and show the stage
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("style.css"); // Applies CSS file
        primaryStage.setScene(scene);
        primaryStage.setTitle("Guess Who");
        primaryStage.show();
        System.out.println("Scene set");
    }

    private VBox createMenuContainer() {
        // Create the menu container
        VBox menuContainer = new VBox(10);

        // Buttons etc.
        Button askQuestionButton = new Button("Ask Question");
        Button restartButton = new Button("Restart");

        // Add CSS style class
        menuContainer.getStyleClass().add("menu-container");
        restartButton.getStyleClass().add("restart-button");
        askQuestionButton.getStyleClass().add("ask-button");

        // Event handlers:
        restartButton.setOnAction(event -> {
            currentGame = new Game();
            System.out.println("Secret person is:" + currentGame.getSecretPerson());
            generateBoard(); // needs to be defined
        });

        // Styling:
        // menuContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        // Add UI elements to container:
        menuContainer.getChildren().addAll(askQuestionButton, restartButton);

        return menuContainer;
    }

    private GridPane createBoardContainer() {
        GridPane boardContainer = new GridPane(); // flexible layout

        // Set CSS style class
        boardContainer.getStyleClass().add("board-container");

        return boardContainer;
    }

    private void generateBoard() {
        // Reset // clear
        // create UI elements + add to boardContainer
            // Image + button for each character, arranged in columns/rows

        // Event handlers:
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
