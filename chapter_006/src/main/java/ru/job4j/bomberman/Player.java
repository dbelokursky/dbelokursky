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

    private volatile String direction;

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

    public Player turnRight() throws InterruptedException {
        if (checkPosition(this.verticalPosition, this.horizontalPosition + 1)) {
            if (field.getField()[verticalPosition][horizontalPosition + 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                direction = "right";
                return call();
            }
        }
        return new Player(name, verticalPosition, horizontalPosition, field);
    }

    public Player turnLeft() throws InterruptedException {
        if (checkPosition(this.verticalPosition, this.horizontalPosition - 1)) {
            if (field.getField()[verticalPosition][horizontalPosition - 1].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                direction = "left";
                return call();
            }
        }
        return new Player(name, verticalPosition, horizontalPosition, field);
    }

    public Player turnUp() throws InterruptedException {
        if (checkPosition(this.verticalPosition + 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition + 1][horizontalPosition].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                direction = "up";
                return call();
            }
        }
        return new Player(name, verticalPosition, horizontalPosition, field);
    }

    public Player turnDown() throws InterruptedException {
        if (checkPosition(this.verticalPosition - 1, this.horizontalPosition)) {
            if (field.getField()[verticalPosition - 1][horizontalPosition].tryLock(500, TimeUnit.MILLISECONDS)) {
                field.getField()[verticalPosition][horizontalPosition].unlock();
                System.out.println(name + " vertical " + verticalPosition + " horizontal " + horizontalPosition);
                direction = "down";
                return call();
            }
        }
        return new Player(name, verticalPosition, horizontalPosition, field);
    }

    @Override
    public Player call() {
        if (direction.equals("right")) {
            return new Player(this.name, verticalPosition, horizontalPosition + 1, field);
        }
        if (direction.equals("left")) {
            return new Player(this.name, verticalPosition, horizontalPosition - 1, field);
        }
        if (direction.equals("up")) {
            return new Player(this.name, verticalPosition + 1, horizontalPosition, field);
        }
        if (direction.equals("down")) {
            return new Player(this.name, verticalPosition - 1, horizontalPosition, field);
        }
        return null;
    }

    private boolean checkPosition(int verticalPosition, int horizontalPosition) {
        return horizontalPosition >= 0 && horizontalPosition < field.getField().length
                && verticalPosition >= 0 && verticalPosition < field.getField().length;
    }
}
