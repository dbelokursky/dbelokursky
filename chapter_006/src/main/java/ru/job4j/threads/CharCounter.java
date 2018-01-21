package ru.job4j.threads;

import jdk.nashorn.api.scripting.URLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dmitry Belokursky
 * @since 21.01.18.
 */
public class CharCounter implements Runnable {

    private final String urlName = "http://lib.ru/EKZUPERY/mprinc.txt";

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new URLReader(new URL(urlName)))) {
            int counter = 0;

            while (br.read() != -1) {
                if (Thread.interrupted()) {
                    return;
                }
                counter++;
            }

            System.out.printf("%d symbols in file.", counter);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
