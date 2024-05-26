package src;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

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
    GridPane boardContainer; // must be inst. var. to be accessible in generateBoard as well as start()

    // start()

    @Override
    public void start(Stage primaryStage) throws Exception {

        currentGame = new Game();

        // Create root layout:
        BorderPane root = new BorderPane();

        // Create Menu container (left) + add to root
        VBox menuContainer = createMenuContainer(); // Vertical box
        root.setLeft(menuContainer);

        // Create Board container (right) + add to root
        boardContainer = createBoardContainer();
        root.setCenter(boardContainer);

        // New game on start():
        //currentGame = new Game();
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

        // Create UI elements:
        Button askQuestionButton = new Button("Ask Question");
        Button restartButton = new Button("Restart");
        ComboBox<String> questionComboBox = new ComboBox<>();
        questionComboBox.setItems(FXCollections.observableArrayList(
                "Hair Color", "Eye Color", "Accessories")); // question's categories

        // Set CSS style class
        menuContainer.getStyleClass().add("menu-container");
        restartButton.getStyleClass().add("restart-button");
        askQuestionButton.getStyleClass().add("ask-button");

        // Event handlers:
        restartButton.setOnAction(event -> {
            currentGame = new Game();
            System.out.println("Secret person is:" + currentGame.getSecretPerson());
            generateBoard();
        });

        // Add UI elements to container:
        menuContainer.getChildren().addAll(restartButton, questionComboBox, askQuestionButton);

        return menuContainer;
    }

    private GridPane createBoardContainer() {
        GridPane boardContainer = new GridPane(); // flexible layout

        // Set CSS style class
        boardContainer.getStyleClass().add("board-container");

        return boardContainer;
    }

    private void generateBoard() {
        // Clear & reset board of children elements:
        boardContainer.getChildren().clear();

        // create UI element for each character + add to boardContainer
        int numColumns = 6; // Pre-defined the number of columns
        int rowIndex = 0;
        int columnIndex = 0;

        for (Character character : currentGame.charactersInPlay) {
            // Layout: CharacterPane that stacks character image + button
            StackPane characterPane = new StackPane();

            // UI elements: image + button for each character
            ImageView characterImage = new ImageView(new Image(character.image));
            Button characterButton = new Button(character.name);

            // characterButton placement in the StackPane container:
            StackPane.setAlignment(characterButton, Pos.BOTTOM_CENTER);
            StackPane.setMargin(characterButton, new Insets(0, 5, 5, 0)); // bottom margin

            // CSS style classes
            characterButton.getStyleClass().add("character-button");
            characterImage.getStyleClass().add("character-image");
            characterPane.getStyleClass().add("character-pane");

            // Event handlers:
            characterButton.setOnAction(event -> {
                // Handle button click
                System.out.println("Clicked character: " + character.name);

                boolean isGuessCorrect = currentGame.checkGuess(character.name);

                if (isGuessCorrect) {
                    System.out.println("Correct!"); // change to an allert? Followed by restart?
                } else {
                    System.out.println("Wrong!");
                }
            });

            // Add the UI elements to the characterPane
            characterPane.getChildren().addAll(characterImage, characterButton);

            // Add the characterPaneContainer to the board container at the specified position
            boardContainer.add(characterPane, columnIndex, rowIndex);

            GridPane.setMargin(characterPane, new Insets(5, 5, 5, 5));

            // Update column and row indices - moves to the next row, once a row is full.
            // This is done every iteration to figure out placement of each individual characterPane
            columnIndex++; // increment current columnIndex
            if (columnIndex >= numColumns) {
                columnIndex = 0;
                rowIndex++; // increment current rowIndex
            }
        }

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
