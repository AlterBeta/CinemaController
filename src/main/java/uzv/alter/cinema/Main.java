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
        ruLines.put("app.title", "Бронирование билетов в кино");
        ruLines.put("app.subtitle", "Выберите фильм, дату и желаемые места");
        ruLines.put("booking.your_choice", "Ваш выбор");
        ruLines.put("booking.total", "Итого:");
        ruLines.put("booking.button", "Забронировать");
        ruLines.put("booking.note", "* Нажмите на место для выбора");
        ruLines.put("booking.price_format", "%d ₸");
        ruLines.put("seat.row", "Ряд %d");
        ruLines.put("seat.number", "Место %d");
        ruLines.put("seat.tooltip.available", "%s (%d ₸)");
        ruLines.put("seat.tooltip.booked", "%s (Занято)");
        ruLines.put("booking.warning.no_seats_msg", "Пожалуйста, выберите хотя бы одно место.");
        ruLines.put("booking.warning.no_seats_title", "Выбор мест");
        ruLines.put("booking.success.msg", "Места успешно забронированы!");
        ruLines.put("booking.success.title", "Успех");

        ruLines.put("app.footer", "ALT University");
        ruLines.put("film.prefix", "Фильм: %s");
        ruLines.put("lang.label", "Язык:");
        ruLines.put("hall.screen", "Э К Р А Н");
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
        enLines.put("app.title", "Cinema Ticket Booking");
        enLines.put("app.subtitle", "Select a movie, date, and preferred seats");
        enLines.put("booking.your_choice", "Your Selection");
        enLines.put("booking.total", "Total:");
        enLines.put("booking.button", "Book Now");
        enLines.put("booking.note", "* Click a seat to select");
        enLines.put("booking.price_format", "%d ₸");
        enLines.put("seat.row", "Row %d");
        enLines.put("seat.number", "Seat %d");
        enLines.put("seat.tooltip.available", "%s (%d ₸)");
        enLines.put("seat.tooltip.booked", "%s (Occupied)");
        enLines.put("booking.warning.no_seats_msg", "Please select at least one seat.");
        enLines.put("booking.warning.no_seats_title", "Seat Selection");
        enLines.put("booking.success.msg", "Seats successfully booked!");
        enLines.put("booking.success.title", "Success");

        enLines.put("app.footer", "ALT University");
        enLines.put("film.prefix", "Movie: %s");
        enLines.put("lang.label", "Language:");
        enLines.put("hall.screen", "S C R E E N");
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
        kkLines.put("app.title", "Кино билеттерін брондау");
        kkLines.put("app.subtitle", "Фильмді, күнін және қалаған орындарды таңдаңыз");
        kkLines.put("booking.your_choice", "Сіздің таңдауыңыз");
        kkLines.put("booking.total", "Жиынтығы:");
        kkLines.put("booking.button", "Брондау");
        kkLines.put("booking.note", "* Таңдау үшін орынды басыңыз");
        kkLines.put("booking.price_format", "%d ₸");
        kkLines.put("seat.row", "%d қатар");
        kkLines.put("seat.number", "%d орын");
        kkLines.put("seat.tooltip.available", "%s (%d ₸)");
        kkLines.put("seat.tooltip.booked", "%s (Бос емес)");
        kkLines.put("booking.warning.no_seats_msg", "Өтініш, кем дегенде бір орын таңдаңыз.");
        kkLines.put("booking.warning.no_seats_title", "Орындарды таңдау");
        kkLines.put("booking.success.msg", "Орындар сәтті брондалды!");
        kkLines.put("booking.success.title", "Сәтті");

        kkLines.put("app.footer", "ALT University");
        kkLines.put("film.prefix", "Фильм: %s");
        kkLines.put("lang.label", "Тіл:");
        kkLines.put("hall.screen", "Э К Р А Н");
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