package ru.job4j.bomberman;

/**
 * @author Dmitry Belokursky
 * @since 22.02.18.
 */
public class Game {

    private static Player player;

    private final Field field;

    public Game(Field field, Player player) {
        this.field = field;
        this.player = player;
    }

    public Game() {
        this.field = new Field();
        this.player = new Player("Player#1", field);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        while (true) {
            player = player.call();
        }
    }
}
