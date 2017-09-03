package ru.job4j.application;

import java.util.ArrayList;

/**
 * @author Dmitry Belokursky
 * @since 11.07.17.
 */
public class ValidateInput extends ConsoleInput {
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data again.");
            }
        } while (invalid);
        return value;
    }
}
