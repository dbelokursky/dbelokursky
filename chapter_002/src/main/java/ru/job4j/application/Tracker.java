package ru.job4j.application;

/**
 * Класс Tracker. Обертка для работы с массивом заявок.
 *
 * @author Dmitry Belokursky
 * @since 0.1 02.07.2017
 */
public class Tracker {

    private Item[] items = new Item[100];

    private int cursor = 0;

    /**
     * Добавляет заявку в массив заявок items.
     *
     * @param item заявка
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        this.items[cursor++] = item;
        return item;
    }

    /**
     * Возвращает копию массива items без null элементов.
     *
     * @return копия массива без null элементов.
     */
    public Item[] findAll() {
        Item[] tmp = new Item[cursor];
        int tInd = 0;
        for (Item item : items) {
            if (item != null) {
                tmp[tInd++] = item;
            }
        }
        Item[] result = new Item[tInd];
        System.arraycopy(tmp, 0, result, 0, tInd);
        return result;
    }

    /**
     * Проверяет все элементы массива items, сравнивая name с аргументом String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив.
     *
     * @param key название заявки.
     * @return результирующий массив.
     */
    public Item[] findByName(String key) {
        Item[] tmp = new Item[cursor];
        int tInd = 0;
        for (int i = 0; i < cursor; i++) {
            if (key.equals(items[i].getName())) {
                tmp[tInd++] = items[i];
            }
        }
        Item[] result = new Item[tInd];
        System.arraycopy(tmp, 0, result, 0, tInd);
        return result;
    }
}
