package uzv.alter.cinema;


import uzv.alter.cinema.client.CinemaFrame;
import uzv.alter.cinema.common.db.TranslationCore;
import uzv.alter.cinema.common.translate.Localization;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        TranslationCore.addLocalization(createRuLoc());
        TranslationCore.addLocalization(createEnLoc());
        TranslationCore.addLocalization(createKkLoc());
        TranslationCore.setCurrent("ru");
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new CinemaFrame().setVisible(true));

    }

    private static Localization createRuLoc() {

        Map<String, String> ruLines = new HashMap<>();
        ruLines.put("app.title", "Кинотеатр");
        ruLines.put("app.subtitle", "Выберите свободные места на сеанс");
        ruLines.put("app.footer", "ALT University");
        ruLines.put("film.prefix", "Фильм: %s");
        ruLines.put("lang.label", "Язык:");
        ruLines.put("hall.screen", "Э К Р А Н");
        ruLines.put("booking.your_choice", "Ваш выбор");
        ruLines.put("booking.total", "Итого");
        ruLines.put("booking.button", "Бронь");
        ruLines.put("booking.note", "");
        ruLines.put("seat.row", "Ряд %d");
        ruLines.put("seat.number", "место %d");
        ruLines.put("date.format", "dd.MM.yyyy");
        ruLines.put("date.time", "HH:mm");
        ruLines.put("session.date", "%s");
        ruLines.put("session.time", "%s");

        ruLines.put("star_wars", "Звёздные войны");
        ruLines.put("odyssey", "Одиссея");
        ruLines.put("spider_man", "Человек-паук");

        ruLines.put("seat.basic", "Базовое место");
        ruLines.put("seat.long", "Комфорт место");

        ruLines.put("lang.ru", "Русский");
        ruLines.put("lang.en", "Английский");
        ruLines.put("lang.kk", "Казахский");

        ruLines.put("card.film", "ФИЛЬМ");
        ruLines.put("card.datetime", "ДАТА И ВРЕМЯ");
        ruLines.put("card.lang", "ЯЗЫК");

        return new Localization("ru", ruLines);
    }


    private static Localization createEnLoc() {

        Map<String, String> enLines = new HashMap<>();
        enLines.put("app.title", "Cinema");
        enLines.put("app.subtitle", "Select available seats for the session");
        enLines.put("app.footer", "ALT University");
        enLines.put("film.prefix", "Movie: %s");
        enLines.put("lang.label", "Language:");
        enLines.put("hall.screen", "S C R E E N");
        enLines.put("booking.your_choice", "Your selection");
        enLines.put("booking.total", "Total");
        enLines.put("booking.button", "Booking");
        enLines.put("booking.note", "");
        enLines.put("seat.row", "Row %d");
        enLines.put("seat.number", "seat %d");
        enLines.put("date.format", "dd.MM.yyyy");
        enLines.put("date.time", "HH:mm");
        enLines.put("session.date", "%s");
        enLines.put("session.time", "%s");

        enLines.put("star_wars", "Star Wars");
        enLines.put("odyssey", "The Odyssey");
        enLines.put("spider_man", "Spider-Man");

        enLines.put("seat.basic", "Standard Seat");
        enLines.put("seat.long", "Comfort Seat");

        enLines.put("lang.ru", "Russian");
        enLines.put("lang.en", "English");
        enLines.put("lang.kk", "Kazakh");

        enLines.put("card.film", "MOVIE");
        enLines.put("card.datetime", "DATE & TIME");
        enLines.put("card.lang", "LANGUAGE");

        return new Localization("en", enLines);
    }



    private static Localization createKkLoc() {

        Map<String, String> kkLines = new HashMap<>();
        kkLines.put("app.title", "Кинотеатр");
        kkLines.put("app.subtitle", "Сеансқа бос орындарды таңдаңыз");
        kkLines.put("app.footer", "ALT University");
        kkLines.put("film.prefix", "Фильм: %s");
        kkLines.put("lang.label", "Тіл:");
        kkLines.put("hall.screen", "Э К Р А Н");
        kkLines.put("booking.your_choice", "Сіздің таңдауыңыз");
        kkLines.put("booking.total", "Жиынтығы");
        kkLines.put("booking.button", "Броньдау");
        kkLines.put("booking.note", "");
        kkLines.put("seat.row", "%d қатар");
        kkLines.put("seat.number", "%d орын");
        kkLines.put("date.format", "dd.MM.yyyy");
        kkLines.put("date.time", "HH:mm");
        kkLines.put("session.date", "%s");
        kkLines.put("session.time", "%s");

        kkLines.put("star_wars", "Жұлдызды соғыстар");
        kkLines.put("odyssey", "Одиссея");
        kkLines.put("spider_man", "Өрмекші-адам");

        kkLines.put("seat.basic", "Стандартты орын");
        kkLines.put("seat.long", "Жайлы орын");

        kkLines.put("lang.ru", "Орыс тілі");
        kkLines.put("lang.en", "Ағылшын тілі");
        kkLines.put("lang.kk", "Қазақ тілі");

        kkLines.put("card.film", "ФИЛЬМ");
        kkLines.put("card.datetime", "КҮНІ ЖӘНЕ УАҚЫТЫ");
        kkLines.put("card.lang", "ТІЛ");


        return new Localization("kk", kkLines);
    }


}