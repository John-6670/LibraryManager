package models.book;

public enum Genre {
    FICTION, NON_FICTION, SCIENCE_FICTION, MYSTERY, THRILLER, ROMANCE, FANTASY,
    HORROR, BIOGRAPHY, HISTORY, CHILDREN, SCIENCE, POETRY, DICTIONARY, COMICS;

    public static String[] getGenresAsString() {
        String[] genres = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            genres[i] = values()[i].name();
        }
        return genres;
    }

    public static Genre[] getGenres() {
        Genre[] genreList = new Genre[values().length];
        for (int i = 0; i < values().length; i++) {
            genreList[i] = values()[i];
        }
        return genreList;
    }
}
