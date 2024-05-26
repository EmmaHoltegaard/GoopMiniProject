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
    private int guessCount;
    private int questionLimit;
    // public String[] allQuestions;  - list of all possible questions?

    // CONSTRUCTOR:
    public Game() {
        charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        setSecretPerson(charactersInPlay); // Choose secretPerson from the initial full list of characters
        guessCount = 0; // reset guessCount
        questionLimit = 3; // decide question limit
    }

    // METHODS:
    public void updateGuessCount() {
        guessCount++;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public int getQuestionLimit() {
        return questionLimit;
    }

    public void checkQuestion(/* selected question / category + value?*/) {
        // NOT DONE
        // Checks for match between chosen question + secretPerson
        // filterCharacters()
    }

    public boolean isGuessCorrect(String guess) {
        // NOT DONE
        if (guess.equals(secretPerson)) {
            return true;
        } else {
            return false;
        }
    }

    public String getSecretPerson() {
        return secretPerson;
    }

    private void setSecretPerson(ArrayList<Character> allCharacters) {
        Random random = new Random();
        int randomIndex = random.nextInt(charactersInPlay.size());
        secretPerson = charactersInPlay.get(randomIndex).name;
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

    /*
    public void newGame() {
        charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        // setSecretPerson(charactersInPlay); // choose secretPerson from the initial full list of characters
        guessCount = 0;
        guessLimit = 3;
    } MOVED THIS TO THE CONSTRUCTOR. INITIALISE NEW GAME OBJECT TO START A NEW GAME.
    */

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
