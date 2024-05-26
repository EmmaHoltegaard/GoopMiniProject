package src;

// This class will hold all game logic.
// - Logic for setting up a new game
//      - initializing the full ArrayList of characters + setting secret person
// - Logic for filtering based on question asked
// - A counter or limit on how many question have been/can be asked
// - setting the secret person
// - Logic for checking if a guess is correct

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    // INSTANCE VARIABLES:
    public ArrayList<Character> charactersInPlay;
    private Character secretPerson;
    private int questionCount;
    private int questionLimit;
    // public String[] allQuestions;  - list of all possible questions?

    // CONSTRUCTOR:
    public Game() {
        charactersInPlay = initializeCharacterList(); // Assign new (full) list to charactersInPlay
        secretPerson = setSecretPerson(charactersInPlay); // Choose secretPerson from the initial full list of characters
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

    // returns name of secret person
    public String getSecretPerson() {
        return secretPerson.name;
    }

    public void checkQuestion(String question) {
        String category;
        String value;

        // Split question into category and value
        String[] parts = question.split(": "); // array of parts
        if (parts.length == 2) {
            category = parts[0].toLowerCase();
            value = parts[1].toLowerCase();
            System.out.println("checkQuestion will filter based on " + category + " and " + value);

            // Check for match between selected question & secretPerson's attributed - and invoke filterCharacters()
            // filterCharacters(true) = keep characters with selected attribute
            // filterCharacters(false) = remove characters with selected attribute
            if (category.equals("hair") || category.equals("eyes")) {
                String secretAttribute = secretPerson.getAttribute(category, String.class);
                if (secretAttribute.equals(value)) {
                    //filterCharacters(true);
                    updateQuestionCount();
                    System.out.println(questionCount);
                } else {
                    //filterCharacters(false);
                    updateQuestionCount();
                    System.out.println(questionCount);
                }
            } else if (category.equals("accessories") || category.equals("other") || category.equals("pets")) {
                String[] secretAttribute = secretPerson.getAttribute(category, String[].class);
                if (Arrays.asList(secretAttribute).contains(value)) {
                    //filterCharacters(true);
                    updateQuestionCount();
                    System.out.println(questionCount);
                } else {
                    //filterCharacters(false);
                    updateQuestionCount();
                    System.out.println(questionCount);
                }
            }
        } else {
            System.out.println("Question check failed");
        }
    }

    public boolean checkGuess(Character guess) {
        if (guess.name.equals(secretPerson.name)) {
            return true;
        } else {
            return false;
        }
    }

    private Character setSecretPerson(ArrayList<Character> allCharacters) {
        Random random = new Random();
        int randomIndex = random.nextInt(allCharacters.size()); // .nextInt() to generate random int within 0-23 (24 characters)
        secretPerson = allCharacters.get(randomIndex); // Use random int to select from index in charactersInPlay
        return secretPerson;
    }

    private void updateQuestionCount() {
        questionCount++;
    }

    private void filterCharacters(boolean keep) {
        // NOT DONE
        // filters charactersInPlay, based on true/false input.
    }

    private ArrayList<Character> initializeCharacterList() {
        // Create new ArrayList:
        ArrayList<Character> allCharacters = new ArrayList<>();

        // Add all characters
        allCharacters.add(new Character("Jabala", "src/resources/images/jabala.png", "hidden", "hidden", new String[]{"glasses", "hats"}, new String[]{""}, new String[]{""}));
        allCharacters.add(new Character("Jack", "src/resources/images/jack.png", "hidden", "blue", new String[]{"hats", "facial hair"}, new String[]{""}, new String[]{"parrot"}));
        allCharacters.add(new Character("Jacques", "src/resources/images/jacques.png", "grey", "blue", new String[]{"hats", "facial hair"}, new String[]{"smoker"}, new String[]{""}));
        allCharacters.add(new Character("Jai", "src/resources/images/jai.png", "black", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jake", "src/resources/images/jake.png", "yellow", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("James", "src/resources/images/james.png", "brown", "green", new String[]{"glasses", "facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jana", "src/resources/images/jana.png", "black", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jane", "src/resources/images/jane.png", "yellow", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jaqueline", "src/resources/images/jaqueline.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jazebelle", "src/resources/images/jazebelle.png", "purple", "hidden", new String[]{"glasses"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jean", "src/resources/images/jean.png", "brown", "blue", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jeane", "src/resources/images/jeane.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jed", "src/resources/images/jed.png", "orange", "green", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new Character("Jenni", "src/resources/images/jenni.png", "white", "hidden", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jeri", "src/resources/images/jeri.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jerry", "src/resources/images/jerry.png", "hidden", "blue", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jess", "src/resources/images/jess.png", "black", "blue", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jocelyn", "src/resources/images/jocelyn.png", "black", "brown", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jon", "src/resources/images/jon.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jordan", "src/resources/images/jordan.png", "yellow", "hidden", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Josephine", "src/resources/images/josephine.png", "grey", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Josh", "src/resources/images/josh.png", "yellow", "green", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Jude", "src/resources/images/jude.png", "black", "green", new String[]{"facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new Character("Julie", "src/resources/images/julie.png", "black", "brown", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));

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

