package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */
public class UserTest {

    @Test
    public void createTheSameUsers() {
        Calendar birthday = Calendar.getInstance();
        User fUser = new User("First", 1, birthday);
        User sUser = new User("First", 1, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(fUser, fUser);
        map.put(sUser, sUser);
        System.out.println(map);
        System.out.println("fUser" + " hashcode " + fUser.hashCode());
        System.out.println("sUser" + " hashcode " + sUser.hashCode());
        System.out.println("fUser.equals(sUser) " + fUser.equals(sUser));
    }

}