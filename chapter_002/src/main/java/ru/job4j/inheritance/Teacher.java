package ru.job4j.inheritance;

import java.util.Date;

/**
 * Created by db on 26.06.17.
 */
public class Teacher extends Profession {

    public String teach(int audienceNumber, Discipline discipline) {
        return (String.format("Teacher $s teach $ in audience №$s", this.getFullName(), discipline.getName(), audienceNumber));
    }

    public String goOnVacation(int numberOfDays, Date startDate) {
        return (String.format("$ on vacation duration $s days since $s", this.getFullName(), numberOfDays, startDate));
    }
}
