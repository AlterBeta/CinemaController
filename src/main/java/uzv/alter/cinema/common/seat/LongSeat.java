package uzv.alter.cinema.common.seat;

import uzv.alter.cinema.common.translate.Component;

public class LongSeat implements ISeatable {

    @Override
    public float getHeight() {
        return 1.6f;
    }

    @Override
    public float getWidth() {
        return 1.0f;
    }

    @Override
    public float getPriceModifier() {
        return 1.15f;
    }

    @Override
    public Component getName() {
        return Component.of("seat.long");
    }
}
