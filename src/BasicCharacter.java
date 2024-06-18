// NB: getAttribute() was written with help from ChatGPT

package src;

/**
 * Represents a basic character.
 * Basic characters inherit attributes and behavior from the Character superclass.
 * They have additional attributes such as hair color, eye color, accessories, pets, etc.
 */
public class BasicCharacter extends Character {

    // Instance variables:
    private String hair;
    private String eyes;
    private String[] accessories;
    private String[] other;
    private String[] pets;

    /**
     * Constructs a BasicCharacter object with the specified attributes.
     * @param name The name of the character.
     * @param image The image file path of the character.
     * @param hair The hair color of the character.
     * @param eyes The eye color of the character.
     * @param accessories An array of accessories worn by the character.
     * @param other An array of other attributes or features of the character.
     * @param pets An array of pets owned by the character.
     */
    public BasicCharacter(String name, String image, String hair, String eyes, String[] accessories, String[] other, String[] pets) {
        super(name, image); // calls constructor of Character superclass
        this.hair = hair;
        this.eyes = eyes;
        this.accessories = accessories;
        this.other = other;
        this.pets = pets;
    }

    /**
     * Returns the value(s) of a specific attribute category, with type safety
     * @param category The category of the attribute to retrieve
     * @param type The class type of the attribute to retrieve (either String og String[])
     * @return The value(s) of the given attribute as any specified data type.
     * @param <T> The type for the value of the given attribute
     */
    @Override
    public <T> T getAttribute(String category, Class<T> type) {
        switch (category) {
            case "hair":
                return type.cast(hair);
            case "eyes":
                return type.cast(eyes);
            case "accessories":
                return type.cast(accessories);
            case "other":
                return type.cast(other);
            case "pets":
                return type.cast(pets);
        }
        return null;
    }

}

