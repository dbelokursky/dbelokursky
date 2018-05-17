package ru.job4j.musicvenue;

import ru.job4j.musicvenue.models.MusicType;

import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public interface MusicTypeDao {

    void add(MusicType musicType);

    void update(int id, MusicType musicType);

    void delete(int id);

    MusicType findById(int id);

    List<MusicType> findAll();
}
