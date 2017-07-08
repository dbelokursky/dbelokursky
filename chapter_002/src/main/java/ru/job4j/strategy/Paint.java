package ru.job4j.strategy;

/**
 * @author Dmitry Belokursky
 * @since 08.07.17.
 */
public class Paint {

    private Shape shape;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        System.out.print(shape.pic());
    }
}
