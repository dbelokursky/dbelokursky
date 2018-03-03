package ru.job4j.bomberman;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmitry Belokursky
 * @since 03.03.18.
 */
public class Monster implements Callable<Monster> {

    private final int verticalPosition;

    private final int horizontalPosition;

    private final String name;

    private final Field field;

    public Monster(String name, int verticalPosition, int horizontalPosition, Field field) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.name = name;
        this.field = field;
    }

    public Monster(String name, Field field) {
        this.name = name;
        this.horizontalPosition = field.getField().length - 1;
        this.verticalPosition = field.getField().length - 1;
        this.field = field;
        field.getField()[field.getField().length - 1][field.getField().length - 1].lock();
    }

    @Override
    public Monster call() throws Exception {
        if (checkPosition(this.verticalPosition + 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition + 1][horizontalPosition].tryLock(5000, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                return new Monster(this.name, verticalPosition + 1, horizontalPosition, field);
            }
        }
        if (checkPosition(this.verticalPosition - 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition - 1][horizontalPosition].tryLock(5000, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + "M-vertical " + verticalPosition + "M-horizontal " + horizontalPosition);
                return new Monster(this.name, verticalPosition - 1, horizontalPosition, field);
            }
        }
        if (checkPosition(this.verticalPosition, this.horizontalPosition + 1)) {
            if (field.getField()[verticalPosition][horizontalPosition + 1].tryLock(5000, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + "M-vertical " + verticalPosition + "M-horizontal " + horizontalPosition);
                return new Monster(this.name, verticalPosition, horizontalPosition + 1, field);
            }
        }
        if (checkPosition(this.verticalPosition, this.horizontalPosition + 1)) {
            if (field.getField()[verticalPosition][horizontalPosition - 1].tryLock(5000, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + "M-vertical " + verticalPosition + "M-horizontal " + horizontalPosition);
                return new Monster(this.name, verticalPosition, horizontalPosition + 1, field);
            }
        }
        return null;
    }

    private boolean checkPosition(int verticalPosition, int horizontalPosition) {
        return horizontalPosition >= 0 && horizontalPosition < field.getField().length
                && verticalPosition >= 0 && verticalPosition < field.getField().length;
    }
}
