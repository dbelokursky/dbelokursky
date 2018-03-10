package ru.job4j.application;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class StartUI {

    private Input input;

    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

    public void init() {
        initDatabase();
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(this.input.ask("Select: ", menu.getMenuRange()));
        } while (!"y".equals(this.input.ask("Exit? y ")));
    }

    private void initDatabase() {
        PrintStream stdOut = System.out;
        try {
            System.setOut(new PrintStream(new File("./chapter_002/src/main/java/ru/job4j/application/init.sql.log")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pathToSqlFile = "./chapter_002/src/main/java/ru/job4j/application/init.sql";
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./chapter_002/src/main/java/ru/job4j/application/db.properties");
            properties.load(inputStream);
            Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
            Statement statement = null;
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader(pathToSqlFile));
            scriptRunner.runScript(reader);
            System.setOut(stdOut);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
