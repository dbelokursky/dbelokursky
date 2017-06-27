package ru.job4j.inheritance;

/**
 * Class Engineer. Характеристики оборудования.
 */
public class Equipment {

    /**
     * Поле хранит название оборудования.
     */
    private String name;

    /**
     * Метод устанавливает название оборудования.
     * @param name **название оборудования**
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает название оборудования.
     * @return **название оборудования**
     */
    public String getName() {
        return name;
    }
}
