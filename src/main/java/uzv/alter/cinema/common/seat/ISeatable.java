package uzv.alter.cinema.common.seat;

import uzv.alter.cinema.common.translate.Component;

public interface ISeatable {

    float getHeight();
    float getWidth();

    float getPriceModifier();

    Component getName();

}
