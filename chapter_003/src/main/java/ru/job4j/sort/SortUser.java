package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */
public class SortUser {

    public TreeSet<User> sort(List<User> list) {
        TreeSet<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return -(o1.getName().length() - o2.getName().length());
            }
        });
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int nameComparator = o1.getName().compareTo(o2.getName());
                int ageComparator = o1.getAge() - o2.getAge();
                return nameComparator == 0 ? ageComparator : nameComparator;
            }
        });
        return list;
    }
}
