package ru.job4j.application;

import java.util.Scanner;

/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
