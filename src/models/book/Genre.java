package models.book;

/**
 * Represents the genre of a book.
 * This enum provides a list of possible genres that a book can have. It also includes a method to get all genres as strings.
 * This can be useful when you want to display a list of genres to the user, for example, in a dropdown menu.
 *
 * @author John
 */
public enum Genre {
    FICTION, NON_FICTION, SCIENCE_FICTION, MYSTERY, THRILLER, ROMANCE, FANTASY,
    HORROR, BIOGRAPHY, HISTORY, CHILDREN, SCIENCE, POETRY, DICTIONARY, COMICS;

    /**
     * Returns an array of all genres as strings.
     * This method iterates over all the values of the Genre enum and converts them to strings.
     * The resulting array can be used to display a list of genres to the user.
     *
     * @return an array of strings representing all genres
     */
    public static String[] getGenresAsString() {
        String[] genres = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            genres[i] = values()[i].name();
        }
        return genres;
    }
}
