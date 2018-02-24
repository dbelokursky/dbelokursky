package ru.job4j.bomberman;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmitry Belokursky
 * @since 22.02.18.
 */
public class Player implements Callable<Player> {

    private final int initialPosition = 0;

    private final int verticalPosition;

    private final int horizontalPosition;

    private final String name;

    private final Field field;

    public Player(String name, int verticalPosition, int horizontalPosition, Field field) {
        this.name = name;
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        this.field = field;
    }

    public Player(String name, Field field) {
        this.name = name;
        this.horizontalPosition = initialPosition;
        this.verticalPosition = initialPosition;
        this.field = field;
        field.getField()[initialPosition][initialPosition].lock();
    }

    @Override
    public Player call() throws Exception {
        if (checkPosition(this.verticalPosition + 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition + 1][horizontalPosition].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                return new Player(this.name, verticalPosition + 1, horizontalPosition, field);
            }
        }
        if (checkPosition(this.verticalPosition - 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition - 1][horizontalPosition].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                return new Player(this.name, verticalPosition - 1, horizontalPosition, field);
            }
        }
        if (checkPosition(this.verticalPosition, this.horizontalPosition + 1)) {
            if (field.getField()[verticalPosition][horizontalPosition + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                return new Player(this.name, verticalPosition, horizontalPosition + 1, field);
            }
        }
        if (checkPosition(this.verticalPosition, this.horizontalPosition + 1)) {
            if (field.getField()[verticalPosition][horizontalPosition - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                return new Player(this.name, verticalPosition, horizontalPosition + 1, field);
            }
        }
        return null;
    }

    private boolean checkPosition(int horizontalPosition, int verticalPosition) {
        return horizontalPosition >= 0 && horizontalPosition < field.getField().length
                && verticalPosition >= 0 && verticalPosition < field.getField().length;
    }
}
