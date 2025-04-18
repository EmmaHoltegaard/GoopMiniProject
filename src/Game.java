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
    private ArrayList<Character> charactersInPlay;
    /**
     * Holds the name of the secret character
     */
    private Character secretCharacter;
    /**
     * Keeps track of number of questions asked
     */
    private int questionCount;
    /**
     * The limit of many questions can be asked during a single game
     */
    private int questionLimit;
    /**
     * Holds the text for the message displayed to the player
     */
    private String message;

   //CONSTRUCTOR
    /**
     * Construct a Game object.
     * Calling this constructor starts a new game by initialising a character-list, setting a secret character and setting the question count to 0.
     */
    public Game() {
        this.charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        this.secretCharacter = setSecretCharacter(charactersInPlay); // Choose secretPerson from the initial full list of characters
        this.questionCount = 0; // guessCount start value
        this.questionLimit = 5; // question limit
        this.message = "Go ahead an ask a question from the menu!";
    }

    // METHODS:
    /**
     * Gets the list of characters currently in play
     * @return an ArrayList Characters objects
     */
    public ArrayList<Character> getCharactersInPlay() {
        return charactersInPlay;
    }

    /**
     * Gets the number of questions asked during the game
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


    /**
     * Returns the secret character
     * @return secret character of the type Character
     */
    public Character getSecretCharacter() {
        return secretCharacter;
    }

    /**
     * Returns a game message, which guides the player or gives feedback on their actions.
     * @return The game message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Changes the player message
     * @param playerMessage text for the player message
     */
    public void setMessage(String playerMessage) {
        message = playerMessage;
    }

    /**
     * Checks for a match between selected question and the secret character.
     * Splits selected question into category and value, and checks if these match with any of the secret character's attributes.
     * Then calls the filterCharacter() method to filter the list of characters in play and sets a new message accordingly.
     * NB: This method is not dynamic in this iteration and is only set up to deal with BasicCharacter objects
     * @param question The question selected by the user, formatted as "category: value".
     *                 Valid categories are "hair", "eyes", "accessories", "other", and "pets".
     *                 Represents the specific attribute to be checked against the secret character.
     */
    public void checkQuestion(String question) {
        String category;
        String value;

        // Split question into category and value
        String[] parts = question.split(": "); // array of parts
        if (parts.length == 2) {
            category = parts[0].toLowerCase();
            value = parts[1].toLowerCase();
            //System.out.println("Category:" + category + ", Value: " + value);

            // Check for match between selected question & secretPerson's attributed - and invoke filterCharacters() + sets new message text
                // filterCharacters(true, category, value) = keep characters with selected attribute
                // filterCharacters(false, category, value) = remove characters with selected attribute
            if (category.equals("hair") || category.equals("eyes")) {
                String secretAttribute = secretCharacter.getAttribute(category, String.class);
                if (secretAttribute.equals(value)) {
                    setMessage("Yes, the secret character has " + value + " " + category + ".");
                    filterCharacters(true, category, value);
                } else {
                    setMessage("No, the secret character does not have " + value + " " + category + ".");
                    filterCharacters(false, category, value);
                }
            } else if (category.equals("accessories") || category.equals("other") || category.equals("pets")) {
                String[] secretAttribute = secretCharacter.getAttribute(category, String[].class);
                if (Arrays.asList(secretAttribute).contains(value)) {
                    filterCharacters(true, category, value);
                    if (category.equals("accessories") && value.equals("glasses")) {
                        setMessage("Yes, the character wears " + value + ".");
                    }
                    if (category.equals("accessories") && value.equals("hats")) {
                        setMessage("Yes, the character wears a hat.");
                    }
                    if (category.equals("accessories") && value.equals("facial hair")) {
                        setMessage("Yes, the character has facial hair.");
                    }
                    if (category.equals("other") && value.equals("smoker")) {
                        setMessage("Yes, the character is a smoker.");
                    }
                    if (category.equals("pets")) {
                        setMessage("Yes, the character has a pet.");
                    }
                } else {
                    if (category.equals("accessories") && value.equals("glasses")) {
                        setMessage("No, the character does not wear " + value + ".");
                    }
                    if (category.equals("accessories") && value.equals("hats")) {
                        setMessage("No, the character does not wear a hat.");
                    }
                    if (category.equals("accessories") && value.equals("facial hair")) {
                        setMessage("No, the character does not have facial hair.");
                    }
                    if (category.equals("other") && value.equals("smoker")) {
                        setMessage("No, the character isn't a smoker.");
                    }
                    if (category.equals("pets")) {
                        setMessage("No, the character doesn't have a pet.");
                    }
                    filterCharacters(false, category, value);
                }
            } else {
                System.out.println("Category not valid");
                setMessage("Category is not valid.");
            }

        } else {
            System.out.println("Invalid question format");
            setMessage("Invalid question format.");
        }
    }

    /**
     * Checks if the selected character is the secret character.
     * @param guess The final guess made by the player
     * @return {@code true} if the guessed character's name matches the secret character's name,
     *         {@code false} if not.
     */
    public boolean checkGuess(Character guess) {
        setMessage("You've made your guess. Press restart to play again.");
        if (guess.getName().equals(secretCharacter.getName())) {
            return true;
        } else {
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
        int randomIndex = random.nextInt(allCharacters.size()); // .nextInt() generates random int within 0-23 (24 characters)
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

        // removeIf() iterates over characters in charactersInPlay, and removes based on boolean condition.
        // True = remove, false = don't remove.
        charactersInPlay.removeIf(person -> {
            // Stores the attribute value for the character, for the specified category.
            Object attribute = person.getAttribute(category, Object.class);

            // Check if the attribute is a String (hair or eyes)
            if (attribute instanceof String) {
                if (keep && attribute.equals(value)) { // if we want to keep AND attribute matches
                    return false; // don't remove the character
                } else if (keep && !attribute.equals(value)) { // if we want to keep AND attribute  doesn't match
                    return true; // remove the character
                }
                else if (!keep && attribute.equals(value)) { // if we don't want to keep + attribute matches
                    return true; // remove character
                } else if (!keep && !attribute.equals(value)) { // If we don't want to keep + attribute doesn't match
                    return false; // don't remove
                }
            }
            // Check if the attribute is an array of values (String[]) (other, pets, accessories)
            else if (attribute instanceof String[]) {
                // Convert the array to a list to run contains() method
                if (keep && Arrays.asList((String[]) attribute).contains(value)) { // If we want to keep + list of values contains the specified attribute value
                    return false; // Don't remove
                } else if (keep && !Arrays.asList((String[]) attribute).contains(value)) { // If we want to keep + list doesn't contain specified value
                    return true; // Remove
                }
                else if (!keep && Arrays.asList((String[]) attribute).contains(value)) { // If we don't want to keep + list of values contains the specified attribute value
                    return true; // Remove
                } else if (!keep && !Arrays.asList((String[]) attribute).contains(value)) { // If we don't want to keep + list doesn't contain specified value
                    return false; // don't remove
                }
            }
            return false; // removeIf() default to false
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
        allCharacters.add(new BasicCharacter("James", "src/resources/images/james.png", "brown", "green", new String[]{"glasses", "facial hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jana", "src/resources/images/jana.png", "black", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jane", "src/resources/images/jane.png", "yellow", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jaqueline", "src/resources/images/jaqueline.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jazebelle", "src/resources/images/jazebelle.png", "purple", "hidden", new String[]{"glasses"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jean", "src/resources/images/jean.png", "brown", "blue", new String[]{"glasses", "hats", "facial hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jeane", "src/resources/images/jeane.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jed", "src/resources/images/jed.png", "orange", "green", new String[]{"glasses", "hats", "facial hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jenni", "src/resources/images/jenni.png", "white", "hidden", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jeri", "src/resources/images/jeri.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jerry", "src/resources/images/jerry.png", "hidden", "blue", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jess", "src/resources/images/jess.png", "black", "blue", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jocelyn", "src/resources/images/jocelyn.png", "black", "brown", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jon", "src/resources/images/jon.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jordan", "src/resources/images/jordan.png", "yellow", "hidden", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Josephine", "src/resources/images/josephine.png", "grey", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Josh", "src/resources/images/josh.png", "yellow", "green", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Jude", "src/resources/images/jude.png", "black", "green", new String[]{"facial hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new BasicCharacter("Julie", "src/resources/images/julie.png", "black", "brown", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));

        // Return the full list w. ALL characters
        return allCharacters;
    }
}

