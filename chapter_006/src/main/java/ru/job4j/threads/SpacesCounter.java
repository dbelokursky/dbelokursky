package ru.job4j.threads;

import jdk.nashorn.api.scripting.URLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dmitry Belokursky
 * @since 20.01.18.
 */
public class SpacesCounter implements Runnable {

    private final String urlName = "http://www.gutenberg.org/files/56384/56384-0.txt";

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new URLReader(new URL(urlName)))) {
            String line;
            int spacesCounter = 0;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isWhitespace(line.charAt(i))) {
                        spacesCounter++;
                    }
                }
            }
            System.out.printf("File contains %d spaces %n", spacesCounter);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
