package ru.job4j.inheritance;

/**
 * Created by db on 26.06.17.
 */
public class Profession {

    private String fullName;

    private String specialization;

    private int experience;

    private int salary;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    public int getSalary() {
        return salary;
    }
}
