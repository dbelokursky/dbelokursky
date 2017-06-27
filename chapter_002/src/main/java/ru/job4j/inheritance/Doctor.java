package ru.job4j.inheritance;

/**
 * Created by db on 26.06.17.
 */
public class Doctor extends Profession {

    public String treat(Disease desiase) {
        return String.format("Doctor $s treat $s", this.getFullName(), desiase.getName());
    }

}
