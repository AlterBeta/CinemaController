package uzv.alter.cinema.common.translate;

import uzv.alter.cinema.common.db.TranslationCore;

public class Component {

    private Object[] args;
    private final String id;

    private Component(String id, Object... args) {
        this.id = id;
        this.args = args;
    }

    public static Component of(String id, Object... args) {
        return new Component(id, args);
    }

    public String getId() {
        return this.id;
    }

    public Object[] getFormatting() {
        return this.args;
    }

    public String getString() {
        return TranslationCore.translate(id, args);
    }


}
