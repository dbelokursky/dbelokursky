package ru.job4j.parser;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Dmitry Belokursky
 * @since 18.03.18.
 */
public class Launcher {

    private static final Logger LOGGER = Logger.getLogger("Launcher.class");

    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            TimerTask parserTask = new Parser();
            timer.scheduleAtFixedRate(parserTask, 0, 24 * 60 * 60 * 1000);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
