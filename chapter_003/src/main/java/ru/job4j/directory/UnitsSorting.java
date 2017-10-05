package ru.job4j.directory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Dmitry Belokursky
 * @since 04.10.17.
 */
public class UnitsSorting {

    /**
     * Добавляет пропущенные коды подразделений верхнего уровня.
     *
     * @param unitCodes массив строк с кодами подразделений
     * @return массив строк дополненый кодами подразделений верхнего уровня.
     */
    private Set<String> addMissingCodes(String[] unitCodes) {
        Set<String> processedUnitCodes = new HashSet<>();
        for (String unit : unitCodes) {
            String[] line = unit.split("\\\\");
            processedUnitCodes.add(line[0]);
            processedUnitCodes.add(unit);
        }
        return processedUnitCodes;
    }

    /**
     * Сортирует справочник подразделений в порядке возрастания.
     *
     * @param unitCodes массив строк с кодами подразделений.
     * @return отсортированный по возрастанию массив строк.
     */
    public String[] sortAscending(String[] unitCodes) {
        TreeSet<String> result = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        result.addAll(addMissingCodes(unitCodes));
        return result.toArray(new String[result.size()]);
    }

    /**
     * Сортирует справочник подразделений в порядке убывания.
     *
     * @param unitCodes массив строк с кодами подразделений.
     * @return отсортированный по убыванию массив строк.
     */
    public String[] sortDescending(String[] unitCodes) {
        TreeSet<String> result = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int subStrComp = -o1.substring(0, 2).compareTo(o2.substring(0, 2));
                int result = subStrComp;
                int lengthComp = o1.length() - o2.length();
                int strComp = -o1.compareTo(o2);
                if (subStrComp == 0) {
                    result = lengthComp;
                    if (lengthComp == 0) {
                        result = strComp;
                    }
                }
                return result;

            }
        });
        result.addAll(addMissingCodes(unitCodes));
        return result.toArray(new String[result.size()]);
    }
}
