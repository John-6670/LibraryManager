package models.base;


public abstract class LibraryItem {
    protected static int nextID = 2024_05_10; // Static variable to hold the next item ID
    protected final int ID;
    protected String name;

    /**
     * Constructs a new LibraryItem with the given name
     *
     * @param name the name of the item
     */
    public LibraryItem(String name) {
        this.name = name;
        this.ID = nextID++;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return a string representation of the item
     */
    public abstract String toString();

    /**
     * Returns a formatted string representation of the item for table display.
     *
     * @return a formatted string representation of the item for table display
     */
    public abstract String toStringTable();
}
