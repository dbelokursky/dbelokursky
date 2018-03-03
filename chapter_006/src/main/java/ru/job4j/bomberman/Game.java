package ru.job4j.bomberman;

/**
 * @author Dmitry Belokursky
 * @since 22.02.18.
 */
public class Game {

    private static Player player;

    private static Monster monster;

    private final Field field;

    private static boolean gameOver = false;

    public Game(Field field, Player player, Monster monster) {
        this.field = field;
        this.player = player;
        this.monster = monster;
    }

    public Game() {
        this.field = new Field();
        this.player = new Player("Player#1", field);
        this.monster = new Monster("Monster#1", field);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        while (!gameOver) {
            monster = monster.call();
        }
    }
}
