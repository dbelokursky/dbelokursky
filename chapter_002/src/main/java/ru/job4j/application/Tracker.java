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
     * Обновляет заявку в массиве items.
     *
     * @param item новая заявка.
     */
    public void update(Item item) {
        for (int i = 0; i < cursor; i++) {
            if (item != null && item.getId().equals(items[i].getId())) {
                items[i].setName(item.getName());
                items[i].setDescription(item.getDescription());
                break;
            }
        }
    }

    /**
     * Удаляет ячейку в массиве items. Все значения справа от удаляемого элемента - на одну ячейку влево.
     *
     * @param item заявка.
     */
    public void delete(Item item) {
        for (int i = 0; i < cursor; i++) {
            if (item != null && item.getId().equals(items[i].getId())) {
                System.arraycopy(items, i + 1, items, i, cursor);
                cursor--;
                break;
            }
        }
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
        Item result[] = new Item[tInd];
        System.arraycopy(tmp, 0, result, 0, tInd);
        return result;
    }

    /**
     * Проверяет элементы массива items, сравнивая id с аргументом String id
     *
     * @param id id заявки
     * @return возвращает найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < cursor; i++) {
            if (id.equals(items[i].getId())) {
                result = items[i];
                break;
            }
        }
        return result;
    }
}
