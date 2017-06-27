package ru.job4j.inheritance;

/**
 * Class Discipline. Характеристики научной дисциплины.
 * @author Dmitry Belokursy
 * @since 27.06.2017
 */
public class Discipline {

    /**
     * Поле хранит название дисциплины.
     */
    private String name;

    /**
     * Метод устанавливает название дисциплины.
     * @param name **название дисциплины**
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает название дисциплины.
     * @return **название дисциплины**
     */
    public String getName() {
        return name;
    }
}
