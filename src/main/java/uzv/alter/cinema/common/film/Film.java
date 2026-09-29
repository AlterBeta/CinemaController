package uzv.alter.cinema.common.film;

import uzv.alter.cinema.common.translate.Component;

public record Film(String id, float costOfTicket) {

    public Component getName() {
        return Component.of(id);
    }

}
