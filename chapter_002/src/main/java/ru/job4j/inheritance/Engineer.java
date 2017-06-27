package ru.job4j.inheritance;

/**
 * Class Engineer. Характеристики инженера.
 */
public class Engineer extends Profession {

    /**
     * Метод возвращает фразу содержащую ФИО инженера и название оборудования.
     * @param equipment **ремонтируемое оборудование**
     * @return **фраза содержащая ФИО инженера и название оборудования**
     */
    public String repairEquipment(Equipment equipment) {
        return (String.format("Engineer $s repair $s", this.getFullName(), equipment.getName()));
    }
}
