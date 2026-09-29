package uzv.alter.cinema.api.init;

import uzv.alter.cinema.common.hall.Hall;
import uzv.alter.cinema.common.seat.BaseSeat;
import uzv.alter.cinema.common.seat.ISeatable;
import uzv.alter.cinema.common.seat.LongSeat;

import java.util.List;

public class HallInit {

    public static final List<ISeatable> BASIC_SEAT_TYPE = List.of(new BaseSeat(), new LongSeat());

    public static final Hall HALL_A = new Hall(
            new int[][] {
                    new int[] {1, 1, 1, 1,   1, 1, 1, 1},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0}
            },
            BASIC_SEAT_TYPE
    );


    public static final Hall HALL_B = new Hall(
            new int[][] {
                    new int[] {1, 1, 1, 1,   1, 1, 1, 1,   1, 1, 1, 1},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0},
                    new int[] {0, 0, 0, 0,   0, 0, 0, 0,   0, 0, 0, 0}
            },
            BASIC_SEAT_TYPE
    );




}
