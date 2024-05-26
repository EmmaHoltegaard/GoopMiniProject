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

    // Consider: Make other constructors, where less info is necessary?
}
