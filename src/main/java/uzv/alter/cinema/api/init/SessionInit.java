package uzv.alter.cinema.api.init;

import uzv.alter.cinema.common.session.Session;

import java.time.LocalDateTime;
import java.util.List;

public class SessionInit {

    public static final Session SESSION_A = new Session(
            FilmInit.ODYSSEY,
            List.of(
                    LocalDateTime.of(2026, 9, 30, 15, 30),
                    LocalDateTime.of(2026, 9, 30, 17, 30),
                    LocalDateTime.of(2026, 9, 30, 19, 30)
            ),
            List.of(
                    HallInit.HALL_A,
                    HallInit.HALL_A,
                    HallInit.HALL_B
            )
    );


    public static final Session SESSION_B = new Session(
            FilmInit.SPIDER_MAN,
            List.of(
                    LocalDateTime.of(2026, 9, 30, 12, 30),
                    LocalDateTime.of(2026, 9, 30, 14, 30),
                    LocalDateTime.of(2026, 9, 30, 19, 30)
            ),
            List.of(
                    HallInit.HALL_B,
                    HallInit.HALL_B,
                    HallInit.HALL_A
            )
    );



    public static final Session SESSION_C = new Session(
            FilmInit.STAR_WARS,
            List.of(
                    LocalDateTime.of(2026, 10, 1, 12, 30),
                    LocalDateTime.of(2026, 10, 1, 14, 30),
                    LocalDateTime.of(2026, 10, 1, 19, 30)
            ),
            List.of(
                    HallInit.HALL_A,
                    HallInit.HALL_B,
                    HallInit.HALL_A
            )
    );

}
