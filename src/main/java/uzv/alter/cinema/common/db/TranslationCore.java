package uzv.alter.cinema.common.db;

import uzv.alter.cinema.common.translate.Localization;

import java.util.HashMap;
import java.util.Map;

public class TranslationCore {

    private static final Map<String, Localization> ID_TO_LOC = new HashMap<>();
    private static String CURRENT;



    public static void addLocalization(Localization newLoc) {
        ID_TO_LOC.put(newLoc.id(), newLoc);
    }

    public static void setCurrent(String newCurrent) {
        CURRENT = newCurrent;
    }

    public static String getCurrent() {
        return CURRENT;
    }

    public static String translate(String id, Object... args) {
        return String.format(ID_TO_LOC.get(CURRENT).idToLine().get(id), args);
    }


}
