package src;

// Each character should have a name + attributes + image

public class Character {
    // Instance variables:
    public String name;
    public String image;
    public String hair;
    public String eyes;
    public String[] accessories;
    public String[] other;
    public String[] pets;

    // Constructor:
    public Character(String name, String image, String hair, String eyes, String[] accessories, String[] other, String[] pets) {
        this.name = name;
        this.image = image;
        this.hair = hair;
        this.eyes = eyes;
        this.accessories = accessories;
        this.other = other;
        this.pets = pets;
    }

    // A method that returns value(s) of a specific attribute, with type safety (using generics)
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
