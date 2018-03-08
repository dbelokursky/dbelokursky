package ru.job4j.optimization;

import java.io.Serializable;

/**
 * @author Dmitry Belokursky
 * @since 06.03.18.
 */

public class Entry implements Serializable {

    private int field;

    public Entry() {
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
