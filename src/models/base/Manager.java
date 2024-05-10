package models.base;

/**
 * This interface provides the basic functionalities that a manager class should implement.
 *
 * @author John
 */
public interface Manager {
    /**
     * Adds an item to the list.
     */
    void add();

    /**
     * Updates an item in the list.
     *
     * @param id the ID of the item to update
     */
    void update(int id);

    /**
     * Deletes an item from the list.
     *
     * @param id the ID of the item to delete
     */
    void delete(int id);

    /**
     * Displays all items in the list.
     */
    void list();

    /**
     * Searches for an item by name.
     *
     * @param name the name of the item to search
     */
    void search(String name);

    /**
     * Starts the manager.
     */
    void start();
}
