package ru.job4j.inheritance;

/**
 * Class Doctor. Характеристики врача.
 */
public class Doctor extends Profession {

    /**
     * Метод возвращает фразу содержащую ФИО врача и название болезни.
     * @param disease **заболевание**
     * @return **фраза содержащая ФИО врача и название болезни**
     */
    public String treat(Disease disease) {
        return String.format("Doctor $s treat $s", this.getFullName(), disease.getName());
    }
}
