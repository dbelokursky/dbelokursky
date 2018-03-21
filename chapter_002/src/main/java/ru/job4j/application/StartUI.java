package ru.job4j.application;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init();
    }

    public void init() {
        initDatabase();
        MenuTracker menu = new MenuTracker(this.input);
        menu.fillActions();
        do {
            menu.show();
            menu.select(this.input.ask("Select: ", menu.getMenuRange()));
        } while (!"y".equals(this.input.ask("Exit? y ")));
    }

    private void initDatabase() {
        PrintStream stdOut = System.out;
        try (PrintStream ps = new PrintStream(new File("./chapter_002/src/main/java/ru/job4j/application/init.sql.log"))) {
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pathToSqlFile = "./chapter_002/src/main/java/ru/job4j/application/init.sql";
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("./chapter_002/src/main/java/ru/job4j/application/db.properties")) {
            properties.load(inputStream);
            Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader(pathToSqlFile));
            scriptRunner.runScript(reader);
            System.setOut(stdOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
