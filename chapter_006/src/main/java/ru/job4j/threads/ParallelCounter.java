package ru.job4j.threads;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dmitry Belokursky
 * @since 18.01.18.
 */
public class ParallelCounter {

    public void count() {
        try {
            InputStream inUrl = new URL("http://www.gutenberg.org/files/56384/56384-0.txt").openStream();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
