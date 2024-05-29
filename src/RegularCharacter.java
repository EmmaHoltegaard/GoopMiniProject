package src;

public class RegularCharacter extends Character {

    // Instance variables:
    private String hair;
    private String eyes;
    private String[] accessories;
    private String[] other;
    private String[] pets;

    // Constructor:
    public RegularCharacter(String name, String image, String hair, String eyes, String[] accessories, String[] other, String[] pets) {
        super(name, image); // calls constructor of Character superclass
        this.hair = hair;
        this.eyes = eyes;
        this.accessories = accessories;
        this.other = other;
        this.pets = pets;
    }

    // A method that returns value(s) of a specific attribute, with type safety (using generics)
    // This method throws a checked (and specific) exception, which MUST be handled by any calls to this method (w. try/catch block)
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

