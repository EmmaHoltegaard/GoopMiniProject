package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class represents game logic and actions.
 * It includes methods to setup a new game, check questions, filter the list of characters and decide of a guess if correct or not.
 */
public class Game {
    // INSTANCE VARIABLES:
    /**
     * A list of the characters currently active on the board
     */
    public ArrayList<Character> charactersInPlay;
    /**
     * Holds the name of the secret character
     */
    private Character secretCharacter;
    /**
     * Keeps track of number of questions asked
     */
    private int questionCount;
    /**
     * Sets a limit of many questions can be asked during a single game
     */
    private int questionLimit;

    private String message;

   //CONSTRUCTOR
    /**
     * Construct a Game object.
     * Calling this constructor starts a new game by initialising a character-list, setting a secret character and setting the question count to 0.
     */
    public Game() {
        this.charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        this.secretCharacter = setSecretCharacter(charactersInPlay); // Choose secretPerson from the initial full list of characters
        this.questionCount = 0; // reset guessCount
        this.questionLimit = 5; // decide question limit
        this.message = "Go ahead an ask a question from the menu!";
    }

    // METHODS:
    /**
     * Returns number of questions asked during the game
     * @return number of questions asked
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * Returns number of questions that the player can ask during a single game
     * @return question limit for the game
     */
    public int getQuestionLimit() {
        return questionLimit;
    }

    // returns name of secret person

    /**
     * Returns the name of the secret character
     * @return name of secret character
     */
    public String getSecretCharacter() {
        return secretCharacter.getName();
    }

    public String getMessage() {
        return message;
    }

    /**
     * Checks the selected question against the secret character, by splitting it into category and value,
     * and then checking if the secret character's attributes match the selected values.
     * Then calls the filterCharacter() method to filter the list of characters accordingly.
     * This method is not dynamic in this iteration and is only set up to deal with BasicCharacters
     * @param question The question selected by the user, formatted as "category: value".
     *                 Valid categories are "hair", "eyes", "accessories", "other", and "pets".
     *                 Represents the specific attribute to be checked against the secret character.
     */
    // OBS: This method is currently not dynamic, and therefore not setup to deal with different types of characters.
    public void checkQuestion(String question) {
        String category;
        String value;

        // Split question into category and value
        String[] parts = question.split(": "); // array of parts
        if (parts.length == 2) {
            category = parts[0].toLowerCase();
            value = parts[1].toLowerCase();
            System.out.println("Category:" + category + ", Value: " + value);

            // Check for match between selected question & secretPerson's attributed - and invoke filterCharacters()
            // filterCharacters(true) = keep characters with selected attribute
            // filterCharacters(false) = remove characters with selected attribute
            if (category.equals("hair") || category.equals("eyes")) {
                String secretAttribute = secretCharacter.getAttribute(category, String.class);
                if (secretAttribute.equals(value)) {
                    message = "Yes, the secret character has " + value + " " + category;
                    filterCharacters(true, category, value);
                } else {
                    message = "No, the secret character does not have " + value + " " + category;
                    filterCharacters(false, category, value);
                }
            } else if (category.equals("accessories") || category.equals("other") || category.equals("pets")) {
                String[] secretAttribute = secretCharacter.getAttribute(category, String[].class);
                if (Arrays.asList(secretAttribute).contains(value)) {
                    filterCharacters(true, category, value);
                } else {
                    filterCharacters(false, category, value);
                }
            } else {
                System.out.println("Category not valid");
                message = "Category is not valid";
            }

        } else {
            System.out.println("Invalid question format");
            message = "Invalid question format";
        }
    }

    /**
     * Checks if the selected character is the secret character.
     * @param guess The final guess made by the player
     * @return {@code true} if the guessed character's name matches the secret character's name,
     *         {@code false} otherwise.
     */
    public boolean checkGuess(Character guess) {
        if (guess.getName().equals(secretCharacter.getName())) {
            message = "Correct!";
            return true;
        } else {
            message = "Wrong!";
            return false;
        }
    }

    /**
     * Selects a secret character at random, chosen from the list of characters at the start of the game.
     * @param allCharacters A List of all characters in play at the start of the game.
     * @return The name of the secret character
     */
    private Character setSecretCharacter(ArrayList<Character> allCharacters) {
        Random random = new Random();
        int randomIndex = random.nextInt(allCharacters.size()); // .nextInt() to generate random int within 0-23 (24 characters)
        secretCharacter = allCharacters.get(randomIndex); // Use random int to select from index in charactersInPlay
        return secretCharacter;
    }

    /**
     * Increments the question count by 1
     */
    private void updateQuestionCount() {
        questionCount++;
    }


    /**
     * Filters the list of characters in play based on the specified category and value.
     * Characters in the list will either be kept or removed
     * @param keep If {@code true}, keeps characters with the specified attribute; if {@code false}, removes them.
     * @param category The category of the attribute to filter by.
     * @param value The specific value of the attribute to filter by.
     */
    private void filterCharacters(boolean keep, String category, String value) {
        System.out.println("checkQuestion will filter based on " + category + " and " + value);
        updateQuestionCount();
        System.out.println(getQuestionCount());

        charactersInPlay.removeIf(person -> {
                Object attribute = person.getAttribute(category, Object.class); // getAttribute requires try-catch because of eexceptop

                if (attribute instanceof String) {
                    return attribute.equals(value) != keep;
                } else if (attribute instanceof String[]) {
                    return Arrays.asList((String[]) attribute).contains(value) != keep;
                }
            return false; // Default to false if an exception occurs
        });

    }

    /**
     * Instantiates 24 BasicCharacter objects and adds them to an ArrayList.
     * The ArrayList represents the full list of game characters,
     * and each BasicCharacter object has its own specific attributes.
     * This method is not dynamic in this iteration.
     * @return An ArrayList that holds 24 BasicCharacter objects
     */
    private ArrayList<Character> initializeCharacterList() {
        // Create new ArrayList:
        ArrayList<Character> allCharacters = new ArrayList<>();

        // Add all characters (regular characters edition)
        allCharacters.add(new BasicCharacter("Jabala", "src/resources/images/jabala.png", "hidden", "hidden", new String[]{"glasses", "hats"}, new String[]{""}, new String[]{""}));
        allCharacters.add(new BasicCharacter("Jack", "src/resources/images/jack.png", "hidden", "blue", new String[]{"hats", "facial hair"}, new String[]{""}, new String[]{"parrot"}));
        allCharacters.add(new BasicCharacter("Jacques", "src/resources/images/jacques.png", "grey", "blue", new String[]{"hats", "facial hair"}, new String[]{"smoker"}, new String[]{""}));
        allCharacters.add(new BasicCharacter("Jai", "src/resources/images/jai.png", "black", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jake", "src/resources/images/jake.png", "yellow", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("James", "src/resources/images/james.png", "brown", "green", new String[]{"glasses", "facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jana", "src/resources/images/jana.png", "black", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jane", "src/resources/images/jane.png", "yellow", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jaqueline", "src/resources/images/jaqueline.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jazebelle", "src/resources/images/jazebelle.png", "purple", "hidden", new String[]{"glasses"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jean", "src/resources/images/jean.png", "brown", "blue", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jeane", "src/resources/images/jeane.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jed", "src/resources/images/jed.png", "orange", "green", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jenni", "src/resources/images/jenni.png", "white", "hidden", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jeri", "src/resources/images/jeri.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jerry", "src/resources/images/jerry.png", "hidden", "blue", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jess", "src/resources/images/jess.png", "black", "blue", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jocelyn", "src/resources/images/jocelyn.png", "black", "brown", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jon", "src/resources/images/jon.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jordan", "src/resources/images/jordan.png", "yellow", "hidden", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Josephine", "src/resources/images/josephine.png", "grey", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Josh", "src/resources/images/josh.png", "yellow", "green", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jude", "src/resources/images/jude.png", "black", "green", new String[]{"facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Julie", "src/resources/images/julie.png", "black", "brown", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));

        // Return the full list w. ALL characters
        return allCharacters;
    }


    // This class will hold all game logic.
// - Logic for setting up a new game
//      - initializing the full ArrayList of characters + setting secret person
// - Logic for filtering based on question asked
// - A counter or limit on how many question have been/can be asked
// - setting the secret person
// - Logic for checking if a guess is correct

    // Maybe I'll need a helper method for selecting and a character in GUI and making sure it's the guess the player wants to make...


    // METHODS
    // initializeCharacterList()
    // setSecretPerson()
    // start() (starts a new game)
    //      - int guessCounter = 0;
    //      - initializeCharacterList()
    //          Assigns CharactersInPlay with a list of all characters
    //      - setSecretPerson()
    // updateGuessCounter()
    // checkQuestion() - checks for a match between attribute and secretCharacter. Then call filterCharacters()
    // filterCharacters() - again
    // isGuessCorrect() - Checks if guess is correct

}

/* Notes on constructor vs. newGame():
    // Having a game initialised through the constructor created problems in Main, specifically for the restartGame button.
    // The restart button wouldn't allow a new Game object to be assigned to the existing currentGame variable declared outside the event handler/lambda, requiring verbose workarounds involving an array of games.
    // Starting a new game with the newGame() method (instead of by instantiating with a new Game object) is easier for this specific reason.
    // UPDATE: NOPE. I had just declared currentGame as a local variable in start(), not as an instance variable. Now it works.

        /*public void newGame() {
        charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        setSecretPerson(charactersInPlay); // Choose secretPerson from the initial full list of characters
        questionCount = 0;  // reset guessCount
        questionLimit = 3;  // decide question limit
    }
*/

