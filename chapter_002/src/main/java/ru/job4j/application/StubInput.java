package ru.job4j.application;

import java.util.ArrayList;

/**
 * @author Dmitry Belokursky
 * @since 05.07.17.
 */
public class StubInput implements Input {

    private ArrayList<String> answers;

    private int position = 0;

    public StubInput(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public int ask(String question, ArrayList<Integer> range) {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public String ask(String question) {
        return answers.iterator().next();
    }
}
