package ru.job4j.inheritance;

/**
 * Class Profession. Хранит характеристики профессии.
 */
public class Profession {

    /**
     * Поле хранит ФИО
     */
    private String fullName;

    /**
     * Поле хранит название специализации.
     */
    private String specialization;

    /**
     * Поле хранит стаж(количество лет).
     */
    private int experience;

    /**
     * Поле хранит значение оклада.
     */
    private int salary;

    /**
     * Метод устанавливает ФИО.
     * @param fullName **ФИО**
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Метод устанавливает название специализации.
     * @param specialization **название специализации**
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Метод устанавливает значение стажа.
     * @param experience **стаж**
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Метод устанавливает значение оклада.
     * @param salary **оклад**
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Метод возвращает значение ФИО.
     * @return **ФИО**
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Метод возвращает значение специализации.
     * @return **специализация**
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Метод возвращает значение стажа.
     * @return **стаж**
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Метод возвращает значение оклада.
     * @return **оклад**
     */
    public int getSalary() {
        return salary;
    }
}
