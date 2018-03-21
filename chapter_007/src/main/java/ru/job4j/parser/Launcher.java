package ru.job4j.parser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Dmitry Belokursky
 * @since 18.03.18.
 */
public class Launcher {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask parserTask = new Parser();
        timer.scheduleAtFixedRate(parserTask, 0, 24 * 60 * 60 * 1000);
    }
}
