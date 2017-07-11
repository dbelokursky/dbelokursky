package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class StartUI {

    private Input input;

    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(this.input.ask("Select: ", menu.getMenuRange()));
        } while (!"y".equals(this.input.ask("Exit? y ")));
    }
}
