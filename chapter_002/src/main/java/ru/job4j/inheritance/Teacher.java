package ru.job4j.inheritance;

import java.util.Date;

/**
 * Class Teacher. Хранит характеристики учителя.
 */
public class Teacher extends Profession {

    /**
     * Метод возвращает фразу содержащую ФИО учителя, название дисциплины и номер аудитории.
     * @param audienceNumber **номер аудитории**
     * @param discipline **название дисциплины**
     * @return **фраза содержащая ФИО учителя, название дисциплины и номер аудитории**
     */
    public String teach(int audienceNumber, Discipline discipline) {
        return (String.format("Teacher $s teach $ in audience №$s", this.getFullName(), discipline.getName(), audienceNumber));
    }

    /**
     * Метод возвращает фразу содержащую ФИО учителя, количество дней и дату начала отпуска.
     * @param numberOfDays **количество дней**
     * @param startDate **дата начала отпуска**
     * @return **фраза содержащая ФИО учителя, количество дней и дату начала отпуска.**
     */
    public String goOnVacation(int numberOfDays, Date startDate) {
        return (String.format("$ on vacation duration $s days since $s", this.getFullName(), numberOfDays, startDate.toString()));
    }
}
