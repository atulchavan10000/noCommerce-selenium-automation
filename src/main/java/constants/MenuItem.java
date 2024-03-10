package constants;

public enum MenuItem {
    HOME("Home"),
    DESKTOPS("Desktops"),
    NOTEBOOKS("Notebooks");

    private final String itemName;

    MenuItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    // Optionally, you can add methods or additional properties as needed
}