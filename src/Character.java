package src;

// Each character should have a name + attributes + image

public abstract class Character {
    // Instance variables:
    private String name;
    private String image;


    // Constructor:
    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    // A method that returns value(s) of a specific attribute, with type safety (using generics)
    public abstract <T> T getAttribute(String category, Class<T> type);
}
