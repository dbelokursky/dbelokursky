package ru.job4j.musicvenue;

import ru.job4j.musicvenue.models.Address;

import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public interface AddressDao {

    void add(Address address);

    void update(int id, Address address);

    void delete(int id);

    Address findById(int id);

    List<Address> findAll();
}
