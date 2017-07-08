package ru.job4j.strategy;

/**
 * @author Dmitry Belokursky
 * @since 08.07.17.
 */
public class Square implements Shape {

    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        return sb.append("******\n")
                 .append("******\n")
                 .append("******\n")
                 .append("******\n")
                 .toString();
    }
}
