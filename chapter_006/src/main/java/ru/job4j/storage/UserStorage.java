package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dmitry Belokursky
 * @since 26.01.18.
 */

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> users;

    public UserStorage() {
        this.users = new ConcurrentHashMap<>();
    }

    public synchronized boolean add(final User user) {
        users.put(user.getId(), user);
        return true;
    }

    public boolean update(final User user) {
        return (users.put(user.getId(), user) != null);
    }

    public boolean delete(final User user) {
        return (users.remove(user.getId()) != null);
    }

    public boolean transfer(final int fromId, final int toId, final int amount) {
        boolean result = false;
        User from = users.get(fromId);
        User to = users.get(toId);
        if (from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            users.replace(fromId, from);
            to.setAmount(to.getAmount() + amount);
            users.replace(toId, to);
            result = true;
        }
        return result;
    }

    public int size() {
        return users.size();
    }

    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public String toString() {
        return "UserStorage{ users=" + users + '}';
    }
}
