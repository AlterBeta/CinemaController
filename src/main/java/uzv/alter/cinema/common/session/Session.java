package uzv.alter.cinema.common.session;

import uzv.alter.cinema.common.film.Film;
import uzv.alter.cinema.common.hall.Hall;
import uzv.alter.cinema.common.translate.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Session {

    private final Film film;

    private List<LocalDateTime> dateList;
    private List<Hall> halls;

    public Session(Film film, List<LocalDateTime> dateList, List<Hall> halls) {
        this.film = film;
        this.dateList = dateList;
        this.halls = halls;
    }

    public Film getFilm() {
        return this.film;
    }

    public Component getFilmName() {
        return this.film.getName();
    }

    public Component getDate(int id) {
        return Component.of("session.date", dateList.get(id).format(
                DateTimeFormatter.ofPattern(
                        Component.of("date.format").getString()
                )
        ));
    }

    public Component getTime(int id) {
        return Component.of("session.time", dateList.get(id).format(
                DateTimeFormatter.ofPattern(
                        Component.of("date.time").getString()
                )
        ));
    }

    public List<LocalDateTime> getTimes() {
        return this.dateList;
    }

    public List<Hall> getHalls() {
        return this.halls;
    }

    public Hall getHall(int id) {
        return this.halls.get(id);
    }


}
