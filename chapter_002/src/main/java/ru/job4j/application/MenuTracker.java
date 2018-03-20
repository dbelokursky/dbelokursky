package ru.job4j.application;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 09.07.17.
 */
public class MenuTracker {

    private Input input;

    private UserAction[] actions = new UserAction[7];

    private int position = 1;

    MenuTracker(Input input) {
        this.input = input;
    }

    public UserAction[] getActions() {
        return actions;
    }

    public void fillActions() {
        this.actions[position++] = this.new AddItem("Add the new item.", 1);
        this.actions[position++] = new MenuTracker.ShowItems("Show items.", 2);
        this.actions[position++] = this.new EditItem("Edit item.", 3);
        this.actions[position++] = this.new DeleteItem("Delete item.", 4);
        this.actions[position++] = this.new FindItemById("Find item by id.", 5);
        this.actions[position++] = this.new FindItemByName("Find item by name.", 6);
    }

    private Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./chapter_002/src/main/java/ru/job4j/application/db.properties");
            properties.load(inputStream);
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<Integer> getMenuRange() {
        ArrayList<Integer> range = new ArrayList<>(getActions().length);
        for (int i = 0; i < this.getActions().length; i++) {
            range.add(i);
        }
        return range;
    }

    public void show() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM actions;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void select(int key) {
        this.actions[key].execute(this.input);
    }

    private void itemNotFound() {
        System.out.println("Item not found.");
    }

    private class EditItem extends BaseAction {

        EditItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            String id = input.ask("Enter the ID of the item: ");
            String name = input.ask("Enter a new name for the item:");
            String description = input.ask("Enter a new description for the item: ");
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items SET name = ?, description = ? WHERE id = ?");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, Integer.parseInt(id));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private class ShowItems extends BaseAction {

        ShowItems(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            System.out.println("List of items: ");
            try (Connection connection = getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM items;");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " "
                            + resultSet.getString("name") + " "
                            + resultSet.getString("description"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private class AddItem extends BaseAction {

        AddItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            String name = input.ask("Enter the name of the item: ");
            String desc = input.ask("Enter the description of the item: ");
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items (name, description) VALUES (?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, desc);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private class DeleteItem extends BaseAction {

        DeleteItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            String id = input.ask("Enter the ID of the item: ");
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?;");
                preparedStatement.setInt(1, Integer.parseInt(id));
                if (preparedStatement.executeUpdate() == 0) {
                    itemNotFound();
                } else {
                    System.out.println("The item is removed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private class FindItemById extends BaseAction {

        FindItemById(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            String id = input.ask("Enter the ID of the item: ");
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE id = ?;");
                preparedStatement.setInt(1, Integer.parseInt(id));
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    do {
                        System.out.println(resultSet.getInt("id") + " "
                                + resultSet.getString("name") + " "
                                + resultSet.getString("description"));
                    } while (resultSet.next());
                } else {
                    itemNotFound();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private class FindItemByName extends BaseAction {

        FindItemByName(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input) {
            String name = input.ask("Enter the name of the item: ");
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE name LIKE ?;");
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    do {
                        System.out.println(resultSet.getInt("id") + " "
                                + resultSet.getString("name") + " "
                                + resultSet.getString("description"));

                    } while (resultSet.next());
                } else if (!resultSet.next()) {
                    itemNotFound();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
