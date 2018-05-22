package ru.job4j.musicvenue.dao;

import org.apache.log4j.Logger;
import ru.job4j.musicvenue.models.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 19.05.18.
 */
public class SqlMusicTypeDao implements MusicTypeDao {

    private static final Logger LOGGER = Logger.getLogger("SqlMusicDao.class");

    SqlDaoFactory daoFactory = SqlDaoFactory.INSTANCE;

    @Override
    public void create(MusicType musicType) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "INSERT INTO music_type(name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, musicType.getName());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void update(int id, MusicType musicType) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "UPDATE music_type SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, musicType.getName());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "DELETE FROM music_type WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public MusicType findById(int id) {
        MusicType musicType = new MusicType();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT (id, name) FROM music_type WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                musicType.setId(resultSet.getInt("id"));
                musicType.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicType;
    }

    @Override
    public List<MusicType> findAll() {
        List<MusicType> allMusicTypes = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT (id, name) FROM music_type";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(resultSet.getInt("id"));
                musicType.setName(resultSet.getString("name"));
                allMusicTypes.add(musicType);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allMusicTypes;
    }
}
