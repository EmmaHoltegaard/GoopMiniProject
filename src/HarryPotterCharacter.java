package src;

/**
 * Represents a Harry Potter character.
 * Harry Potters characters inherit attributes and behavior from the Character superclass.
 * They have additional attributes such as hair color, blood status, Hogwarts house, patronus, pets, etc.
 * This is just an example of what another subclass of Character might look like.
 */
public class HarryPotterCharacter extends Character {
    private String hair;
    private String[] status;
    private String hogwartsHouse;
    private String patronus;
    private String[] pets;
    // other attributes...

    // Constructor:
    public HarryPotterCharacter(String name, String image, String hair, String[] status, String hogwartsHouse, String patronus, String[] pets) {
        super(name, image);
        this.hair = hair;
        this.status = status;
        this.hogwartsHouse = hogwartsHouse;
        this.patronus = patronus;
        this.pets = pets;
    }

    @Override
    public <T> T getAttribute(String category, Class<T> type) {
        switch (category) {
            case "hair":
                return type.cast(hair);
            case "eyes":
                return type.cast(status);
            case "accessories":
                return type.cast(hogwartsHouse);
            case "other":
                return type.cast(patronus);
            case "pets":
                return type.cast(pets);
        }
        return null;
    }
}
