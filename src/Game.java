package src;

// This class will hold all game logic.
// - Logic for setting up a new game
//      - initializing the full ArrayList of characters + setting secret person
// - Logic for filtering based on question asked
// - A counter or limit on how many question have been/can be asked
// - setting the secret person
// - Logic for checking if a guess is correct

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    // INSTANCE VARIABLES:
    public ArrayList<Character> charactersInPlay;
    private Character secretPerson;
    private int questionCount;
    private int questionLimit;

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
        return secretPerson.getName();
    }

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
                String secretAttribute = secretPerson.getAttribute(category, String.class);
                if (secretAttribute.equals(value)) {
                    filterCharacters(true, category, value);
                } else {
                        filterCharacters(false, category, value);
                }
            } else if (category.equals("accessories") || category.equals("other") || category.equals("pets")) {
                String[] secretAttribute = secretPerson.getAttribute(category, String[].class);
                if (Arrays.asList(secretAttribute).contains(value)) {
                    filterCharacters(true, category, value);
                } else {
                    filterCharacters(false, category, value);
                }
            } else {
                System.out.println("Category not valid");
            }

        } else {
            System.out.println("Invalid question format");
        }
    }

    public boolean checkGuess(Character guess) {
        if (guess.getName().equals(secretPerson.getName())) {
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


    // NOT DONE - TRY FOR SIMPLER ALTERNATIVES OR JUST WRITE OUT COMMENTS SO FOR UNDERSTANDING IT!
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

    private ArrayList<Character> initializeCharacterList() {
        // Create new ArrayList:
        ArrayList<Character> allCharacters = new ArrayList<>();

        // Add all characters (regular characters edition)
        allCharacters.add(new RegularCharacter("Jabala", "src/resources/images/jabala.png", "hidden", "hidden", new String[]{"glasses", "hats"}, new String[]{""}, new String[]{""}));
        allCharacters.add(new RegularCharacter("Jack", "src/resources/images/jack.png", "hidden", "blue", new String[]{"hats", "facial hair"}, new String[]{""}, new String[]{"parrot"}));
        allCharacters.add(new RegularCharacter("Jacques", "src/resources/images/jacques.png", "grey", "blue", new String[]{"hats", "facial hair"}, new String[]{"smoker"}, new String[]{""}));
        allCharacters.add(new RegularCharacter("Jai", "src/resources/images/jai.png", "black", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jake", "src/resources/images/jake.png", "yellow", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("James", "src/resources/images/james.png", "brown", "green", new String[]{"glasses", "facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jana", "src/resources/images/jana.png", "black", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jane", "src/resources/images/jane.png", "yellow", "hidden", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jaqueline", "src/resources/images/jaqueline.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jazebelle", "src/resources/images/jazebelle.png", "purple", "hidden", new String[]{"glasses"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jean", "src/resources/images/jean.png", "brown", "blue", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jeane", "src/resources/images/jeane.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jed", "src/resources/images/jed.png", "orange", "green", new String[]{"glasses", "hats", "facial-hair"}, new String[]{"smoker"}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jenni", "src/resources/images/jenni.png", "white", "hidden", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jeri", "src/resources/images/jeri.png", "orange", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jerry", "src/resources/images/jerry.png", "hidden", "blue", new String[]{"hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jess", "src/resources/images/jess.png", "black", "blue", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jocelyn", "src/resources/images/jocelyn.png", "black", "brown", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jon", "src/resources/images/jon.png", "brown", "green", new String[]{"glasses"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jordan", "src/resources/images/jordan.png", "yellow", "hidden", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Josephine", "src/resources/images/josephine.png", "grey", "brown", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Josh", "src/resources/images/josh.png", "yellow", "green", new String[]{}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Jude", "src/resources/images/jude.png", "black", "green", new String[]{"facial-hair"}, new String[]{}, new String[]{}));
        allCharacters.add(new RegularCharacter("Julie", "src/resources/images/julie.png", "black", "brown", new String[]{"glasses", "hats"}, new String[]{}, new String[]{}));

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

