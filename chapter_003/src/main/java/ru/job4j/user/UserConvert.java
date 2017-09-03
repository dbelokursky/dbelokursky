package ru.job4j.user;

import java.util.HashMap;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */
public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
