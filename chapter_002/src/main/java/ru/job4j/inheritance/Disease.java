package ru.job4j.inheritance;

/**
 * Class Disease. Характеристики болезни.
 */
public class Disease {

    /**
     * Поле хранит название болезни.
     */
    private String name;

    /**
     * Метод возвращает название болезни.
     * @return **название болезни**
     */
    public String getName() {
        return name;
    }

    /**
     * Метод устанавливает название болезни
     * @param name **название болезни**
     */
    public void setName(String name) {
        this.name = name;
    }
}
