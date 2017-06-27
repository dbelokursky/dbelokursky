package ru.job4j.inheritance;

/**
 * Created by db on 26.06.17.
 */
public class Engineer extends Profession {

    public String repairEquipment(Equipment equipment) {
        return (String.format("Engineer $s repair $s", this.getFullName(), equipment.getName()));
    }
}
