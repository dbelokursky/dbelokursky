package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class StartUI {
    public static void main(String[] args) {
        StartUI startUI = new StartUI();
        ConsoleInput consoleInput = new ConsoleInput();
        startUI.showMenu();
        consoleInput.menuSelect();
    }

    public void showMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("0. Add new Item\n")
          .append("1. Show all items\n")
          .append("2. Edit item\n")
          .append("3. Delete item\n")
          .append("4. Find item by Id\n")
          .append("5. Find items by name\n")
          .append("6. Exit Program\n");
        System.out.println(sb.toString());
    }
}
