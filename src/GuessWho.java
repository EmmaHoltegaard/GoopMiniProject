package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class represents the game board.
 * The class is responsible for creating all UI elements and connecting them to game logic/actions from the Game class.
 */
public class GuessWho extends Application {

    /**
     * Hold the game object representing the current game
     */
    Game currentGame;

    // UI elements that will need to be accessed by multiple methods:
    /**
     * The container for the board, which shows the characters currently in play.
     */
    StackPane boardContainer; // must be inst. var. to be accessible in generateBoard as well as start()

    /**
     * A message to player based on their latest action
     */
    Text playerMessage;

    Text limitWarning;

    Button askQuestionButton;


    /**
     * Starts the application by initializing a new game and setting up the primary stage.
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception if an error occurs during application initialization.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            currentGame = new Game();

            // Exception-test: accessing an array index that doesn't exist
            //int[] array = new int[3];
            //System.out.println(array[4]); // This will throw an Exception

            // Create root layout:
            BorderPane root = new BorderPane();

            // Create Menu container (left) + add to root
            VBox menuContainer = createMenuContainer(); // Vertical box
            root.setLeft(menuContainer);

            // Create Board container (right) + add to root
            boardContainer = createBoardContainer();
            root.setCenter(boardContainer);

            // New game on start():
            generateBoard();

            // Testing:
            System.out.println("Secret person is:" + currentGame.getSecretCharacter().getName());
            for (Character character : currentGame.getCharactersInPlay()) {
                System.out.println(character.getName());
            }

            // Set the scene and show the stage
            Scene scene = new Scene(root, 970, 625);
            scene.getStylesheets().add("style.css"); // Applies CSS file
            primaryStage.setScene(scene);
            primaryStage.setTitle("Guess Who");
            primaryStage.show();
            System.out.println("Scene set");

        } catch (Exception e) {
            // Handle any exceptions that occur during application initialization
            System.out.println("An error occurred during startup: " + e.getMessage());
        }
    }

    /**
     * Creates all UI elements for the game menu.
     * This menu includes a restart button, a dropdown of possible questions, an ask button
     * and a counter showing how many questions have been asked by the player.
     * NB: The question-dropdown is not dynamic in this iteration, but hard coded with questions for BasicCharater objects.
     * @return a VBox containing the menu UI elements.
     */
    private VBox createMenuContainer() {
        // Create the menu container
        VBox menuContainer = new VBox(10);

        // Create UI elements:
        askQuestionButton = new Button("Ask");
        Button restartButton = new Button("Restart");
        Label questionCount = new Label("Questions asked: " + currentGame.getQuestionCount() + " / " + currentGame.getQuestionLimit());
        Label prompt = new Label("Does the secret person have...");
        VBox playerMessageContainer = new VBox();
        playerMessage = new Text(currentGame.getMessage() + "\n");
        limitWarning = new Text();
        ImageView logo = new ImageView(new Image("src/resources/images/logo.png"));



        // OBS: This combobox is not yet dynamic, but hard-coded to only handle the BasicCharacter subclass.
        ComboBox<String> questionComboBox = new ComboBox<>();
        // Add the "Choose one..." item
        questionComboBox.getItems().add("Choose one...");
        questionComboBox.setValue("Choose one...");
        // Add different options to comboBox:
        questionComboBox.getItems().addAll(
                "Hair: brown", "Hair: yellow", "Hair: hidden", "Hair: black", "Hair: grey",
                "Hair: orange", "Hair: purple", "Hair: white");
        questionComboBox.getItems().addAll(
                "Eyes: hidden", "Eyes: brown", "Eyes: blue", "Eyes: green");
        questionComboBox.getItems().addAll(
                "Accessories: glasses", "Accessories: hats", "Accessories: facial hair");
        questionComboBox.getItems().addAll("Other: smoker");
        questionComboBox.getItems().addAll("Pets: parrot");
        //questionComboBox.getItems().addAll("Test: Test");
        //questionComboBox.getItems().addAll("This is a test");

        // Layout:
        prompt.setWrapText(true);
        prompt.setMaxWidth(160); // width on message
        playerMessage.setWrappingWidth(160);
        limitWarning.setWrappingWidth(160);
        playerMessageContainer.setMinHeight(170);
        playerMessageContainer.setPadding(new Insets(8));
        menuContainer.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(logo, new Insets(0,0,0,0));
        VBox.setMargin(prompt, new Insets(0,0,5,0));
        VBox.setMargin(questionComboBox, new Insets(0,0,0,0));
        VBox.setMargin(askQuestionButton, new Insets(0,0,10,0));
        VBox.setMargin(questionCount, new Insets(0,0,10,0));
        VBox.setMargin(playerMessageContainer, new Insets(5,0,0,0));
        VBox.setMargin(playerMessage, new Insets(0,0,15,0));
        VBox.setMargin(restartButton, new Insets(10,0,0,0));

        // Add CSS style classes
        menuContainer.getStyleClass().add("menu-container");
        restartButton.getStyleClass().add("restart-button");
        askQuestionButton.getStyleClass().add("ask-button");
        questionComboBox.getStyleClass().add("question-combo-box");
        playerMessageContainer.getStyleClass().add("player-message-container");
        playerMessage.getStyleClass().add("player-message");
        limitWarning.getStyleClass().add("limit-warning");
        logo.getStyleClass().add("logo");
        prompt.getStyleClass().add("prompt");

        // Event handlers:
        restartButton.setOnAction( event -> {
            currentGame = new Game();
            System.out.println("Secret person is:" + currentGame.getSecretCharacter().getName());
            generateBoard();
            questionCount.setText("Questions asked: " + currentGame.getQuestionCount() + " / " + currentGame.getQuestionLimit()); // update question count in UI
            askQuestionButton.setDisable(false); // reactivate ask-button
            limitWarning.setText(""); // remove question limit warning
            updatePlayerMessage();
        });

        askQuestionButton.setOnAction(event -> {
            String selectedOption = questionComboBox.getValue();
            System.out.println("Selected question is " + selectedOption);

            if (!selectedOption.equals("Choose one...")) {
                // Checks that question limit has not yet been reached:
                if(currentGame.getQuestionCount() < currentGame.getQuestionLimit()) {
                    // Run question through checkQuestion method:
                    currentGame.checkQuestion(selectedOption);
                    // Update UI:
                    questionCount.setText("Questions asked: " + currentGame.getQuestionCount() + " / " + currentGame.getQuestionLimit()); // update questionCount
                    updatePlayerMessage();
                    generateBoard();

                    // Once question limit is reached, disable ask button
                    if (currentGame.getQuestionCount() >= currentGame.getQuestionLimit()) {
                        askQuestionButton.setDisable(true);
                        limitWarning.setText("You cannot ask anymore questions. It's time to make a guess!");
                    }
                }
            } else {
                // When question limit is reached, disable button:
                askQuestionButton.setDisable(true);
                playerMessage.setText(currentGame.getMessage());
            }
        });

        // add text elements to playerMessageContainer
        playerMessageContainer.getChildren().addAll(playerMessage, limitWarning);
        // Add UI elements to container:
        menuContainer.getChildren().addAll(logo, prompt, questionComboBox, askQuestionButton, questionCount, playerMessageContainer, restartButton);

        return menuContainer;
    } // end of createMenuContainer

    /**
     * Creates the container for the game board
     * @return A GridPane that will any board made with the generateBoard() method.
     */
    private StackPane createBoardContainer() {
        StackPane boardContainer = new StackPane(); // flexible layout

        // Set CSS style class
        boardContainer.getStyleClass().add("board-container");

        return boardContainer;
    }

    /**
     * Displays the content of player message to the most recent game message
     */
    private void updatePlayerMessage(){
        playerMessage.setText(currentGame.getMessage());
    }

    /**
     * Clears the board
     */
    private void clearBoard(){
        // Clear & reset board of child elements:
        boardContainer.getChildren().clear();
    }

    /**
     * Displays a win or lose message, depending on the outcome of the guess
     * @param isCorrect {@code true} if the guess passed is correct, {@code false} if not.
     * @param character the character that was selected as the guess
     */
    private void showResult(Boolean isCorrect, Character character) {
        clearBoard();

        // Generate UI elements
        VBox resultContainer = new VBox();
        Text resultMessage = new Text();
        ImageView secretCharacterImage = new ImageView(new Image(currentGame.getSecretCharacter().getImage()));

        // Layout:
        resultContainer.setAlignment(Pos.CENTER);
        resultContainer.setSpacing(20);
        resultContainer.setPadding(new Insets(20)); // Padding around the container

        // add CSS classes:
        resultContainer.getStyleClass().add("result-container");
        resultMessage.getStyleClass().add("result-message");

        // Disable askQuestionButton:
        askQuestionButton.setDisable(true);

        // Reset playerMessage
        playerMessage.setText(currentGame.getMessage());
        limitWarning.setText("");

        // Display correct message:
        if (isCorrect) {
            resultMessage.setText("Correct! The secret character is " + character.getName() + ":");
        } else {
            resultMessage.setText("Wrong! " + character.getName() + " is NOT the secret character - but " + currentGame.getSecretCharacter().getName() + " is:");
        }

        // Add to UI
        resultContainer.getChildren().addAll(resultMessage, secretCharacterImage);
        boardContainer.getChildren().add(resultContainer);
    }


    /**
     * Generates the UI elements for the game board,
     * including name, image and guess-button for each character currently in play.
     */
    private void generateBoard() {
        clearBoard();

        GridPane innerBoardContainer = new GridPane();
        boardContainer.getChildren().add(innerBoardContainer);

        // create UI element for each character + add to boardContainer
        int numColumns = 6; // Pre-defined the number of columns
        int rowIndex = 0;
        int columnIndex = 0;

        // Generate character-card for each character still in play:
        for (Character character : currentGame.getCharactersInPlay()) {
            // Layout: characterPaneContainer and characterPane that stacks character image + button
            VBox characterPaneContainer = new VBox();
            StackPane characterPane = new StackPane();

            // UI elements: image + button for each character
            ImageView characterImage = new ImageView(new Image(character.getImage()));
            Button characterButton = new Button("Guess");
            Label characterName = new Label(character.getName());

            // Layout within characterPaneContainer and characterPane:
            StackPane.setAlignment(characterButton, Pos.BOTTOM_CENTER);
            StackPane.setMargin(characterButton, new Insets(0, 0, 5, 0)); // bottom margin
            characterPaneContainer.setAlignment(Pos.TOP_CENTER); // Align the container at the top center
            VBox.setMargin(characterName, new Insets(5, 0, 5, 0)); // Add spacing between elements
            characterName.setAlignment(Pos.TOP_CENTER); // center pos for name
            GridPane.setMargin(characterPaneContainer, new Insets(5, 5, 5, 5)); // Margin around characterPanes

            // CSS style classes
            characterButton.getStyleClass().add("character-button");
            characterImage.getStyleClass().add("character-image");
            characterPane.getStyleClass().add("character-pane");
            characterPaneContainer.getStyleClass().add("character-pane-container");
            characterName.getStyleClass().add("character-name");

            // Event handlers:
            characterButton.setOnAction(event -> {
                // Handle button click
                System.out.println("Clicked character: " + character.getName());

                boolean isGuessCorrect = currentGame.checkGuess(character);
                showResult(isGuessCorrect, character);
            });

            // Add characterName + characterPane to characterPaneContainer
            characterPaneContainer.getChildren().addAll(characterName, characterPane);

            // Add the UI elements to the characterPane
            characterPane.getChildren().addAll(characterImage, characterButton);

            // Add the characterPaneContainer to the board container w. specified position
            innerBoardContainer.add(characterPaneContainer, columnIndex, rowIndex);


            // Update column and row indices - moves to the next row, once a row is full.
            // This is done every iteration to figure out placement of each individual characterPane
            columnIndex++; // increment current columnIndex
            if (columnIndex >= numColumns) {
                columnIndex = 0;
                rowIndex++; // increment current rowIndex
            }
        }
    } // end of generateBoard()

} // end of Main

