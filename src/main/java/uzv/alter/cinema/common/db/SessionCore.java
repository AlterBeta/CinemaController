package uzv.alter.cinema.common.db;

import uzv.alter.cinema.common.film.Film;
import uzv.alter.cinema.common.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionCore {

    private static final Map<String, Session> FILM_TO_SESSION = new HashMap<>();




    public List<Session> getSessionsForFilm(Film film) {
        List<Session> toReturn = new ArrayList<>();

        for (Map.Entry<String, Session> entry : FILM_TO_SESSION.entrySet()) {

            if (entry.getKey().equals(film.id())) {
                toReturn.add(entry.getValue());
            }

        }

        return toReturn;
    }

}
