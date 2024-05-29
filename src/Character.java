package src;

// Each character should have a name + attributes + image

/**
 * This class represents a character in the game.
 * This is an abstract class, with various subclasses of characters
 */
public abstract class Character {
    // Instance variables:
    private String name;
    private String image;


    // Constructor:

    /**
     * Constructs a BasicCharacter object with the specified attributes.
     * @param name The name of the character.
     * @param image The image file path of the character.
     */
    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Gets name of the character
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the image file path of the character
     * @return image
     */
    public String getImage() {
        return image;
    }

    // A method that returns value(s) of a specific attribute, with type safety (using generics)
    public abstract <T> T getAttribute(String category, Class<T> type);
}
