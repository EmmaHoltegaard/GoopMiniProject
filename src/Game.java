package src;

// This class will hold all game logic.
// - Logic for setting up a new game
//      - initializing the full ArrayList of characters + setting secret person
// - Logic for filtering based on question asked
// - A counter or limit on how many question have been/can be asked
// - setting the secret person
// - Logic for checking if a guess is correct

import java.util.ArrayList;
import java.util.Random;

public class Game {
    // INSTANCE VARIABLES:
    public ArrayList<Character> charactersInPlay;
    private String secretPerson;
    private int questionCount;
    private int questionLimit;
    // public String[] allQuestions;  - list of all possible questions?

    // CONSTRUCTOR:

    public Game() {
        charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        setSecretPerson(charactersInPlay); // Choose secretPerson from the initial full list of characters
        questionCount = 0; // reset guessCount
        questionLimit = 3; // decide question limit
    }

    // METHODS:
    public int getQuestionCount() {
        return questionCount;
    }

    public int getQuestionLimit() {
        return questionLimit;
    }

    public String getSecretPerson() {
        return secretPerson;
    }

    public void checkQuestion(/* selected question / category + value?*/) {
        // NOT DONE
        // Checks for match between chosen question + secretPerson
        // Invokes filterCharacters() w. either true or false
    }

    public boolean isGuessCorrect(String guess) {
        // NOT DONE
        if (guess.equals(secretPerson)) {
            return true;
        } else {
            return false;
        }
    }

    private void updateQuestionCount() {
        questionCount++;
    }

    private void setSecretPerson(ArrayList<Character> allCharacters) {
        Random random = new Random();
        int randomIndex = random.nextInt(allCharacters.size()); // .nextInt() to generate random int within 0-23 (24 characters)
        secretPerson = allCharacters.get(randomIndex).name; // Use random int to select from index in charactersInPlay
    }

    private void filterCharacters(boolean keep) {
        // NOT DONE
        // filters charactersInPlay, based on true/false input.
    }

    private ArrayList<Character> initializeCharacterList() {
        // Create new ArrayList:
        ArrayList<Character> allCharacters = new ArrayList<>();

        // Add all characters
        allCharacters.add(new Character("Jabala", "/resources/images/jabala.svg", "hidden", "hidden", new String[]{"glasses", "hats"}, new String[]{""}, new String[]{""}));
        allCharacters.add(new Character("Jack", "", "hidden", "blue", new String[]{"hats", "facial hair"}, new String[]{""}, new String[]{"parrot"}));
        allCharacters.add(new Character("Jacques", "", "grey", "blue", new String[]{"hats", "facial hair"}, new String[]{"smoker"}, new String[]{""}));
        allCharacters.add(new Character("Jai", "", "black", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jake", "", "yellow", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("James", "", "brown", "green", new String[]{"glasses", "facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jana", "", "black", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jane", "", "yellow", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jaqueline", "", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jazebelle", "", "purple", "hidden", new String[]{"glasses"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jean", "", "brown", "blue", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jeane", "", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jed", "", "orange", "green", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jenni", "", "white", "hidden", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jeri", "", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jerry", "", "hidden", "blue", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jess", "", "black", "blue", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jocelyn", "", "black", "brown", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jon", "", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jordan", "", "yellow", "hidden", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Josephine", "", "grey", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Josh", "", "yellow", "green", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jude", "", "black", "green", new String[]{"facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Julie", "", "black", "brown", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));

        // Return the full list w. ALL characters
        return allCharacters;
    }


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
 */
