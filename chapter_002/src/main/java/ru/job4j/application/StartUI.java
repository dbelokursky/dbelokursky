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
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

    public void init() {
        showMenu();
        selectMenu(input);
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

    public String selectMenu(Input input) {
        boolean exitProgram = false;
        String menuItem = "";

        while (!exitProgram) {
            menuItem = input.ask("Select: ");

            switch (menuItem) {
                case "0"://Добавление заявки.
                    addItem(input);
                    break;
                case "1"://Вывод списка заявок.
                    showItemsList();
                    break;
                case "2"://Редактирование заявки.
                    editItem(input);
                    break;
                case "3"://Удаление заявки.
                    deleteItem(input);
                    break;
                case "4"://Поиск по заявки ID.
                    findItemById(input);
                    break;
                case "5"://Поиск заявок по названию.
                    findItemByName(input);
                    break;
                case "6"://Завершение программы.
                    exitProgram = exit();
            }
        }
        return menuItem;
    }

    private boolean exit() {
        boolean exitProgram;
        System.out.println("Выход из программы.");
        exitProgram = true;
        return exitProgram;
    }

    private void findItemByName(Input input) {
        String name;
        System.out.println("Поиск заявок по названию.");
        name = input.ask("Введите название заявки: ");
        Item[] foundItems = null;
        foundItems = tracker.findByName(name);
        if (foundItems.length >= 1) {
            for (Item i : foundItems) {
                System.out.println(i.toString());
                ;
            }
        } else {
            System.out.println("Заявки не найдены.");
        }
    }

    private void findItemById(Input input) {
        String id;
        Item item;
        System.out.println("Поиск заявки по ID.");
        id = input.ask("Введите ID заявки: ");
        item = tracker.findById(id);
        if (item != null) {
            System.out.println(item.toString());
        } else {
            itemNotFound();
        }
    }

    private void deleteItem(Input input) {
        String id;
        Item item;
        System.out.println("Удаление заявки");
        id = input.ask("Введите ID заявки: ");
        item = tracker.findById(id);
        if (item != null) {
            tracker.delete(item);
            System.out.println("Заявка удалена.");
        } else {
            itemNotFound();
        }
    }

    private void editItem(Input input) {
        String name;
        String description;
        System.out.println("Редактирование заявки.");
        String id = input.ask("Введите ID заявки: ");
        name = input.ask("Введите новое имя заявки :");
        description = input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, description);
        item.setId(id);
        tracker.update(item);
        System.out.println("Заявка отредактированна.");
    }

    private void showItemsList() {
        if (tracker.findAll().length <= 0) {
            System.out.println("Заявки отсутствуют.");
        } else {
            showAllItems(tracker);
        }
    }

    private void addItem(Input input) {
        String name = input.ask("Введите имя заявки: ");
        String description = input.ask("Введите описание заявки: ");
        tracker.add(new Item(name, description));
        System.out.println("Заявка добавлена.");
    }

    private void itemNotFound() {
        System.out.println("Заявка не найдена.");
    }

    private void showAllItems(Tracker tracker) {
        System.out.println("Список заявок: ");
        int itemNum = 1;
        for (Item i : tracker.findAll()) {
            StringBuilder sb = new StringBuilder();
            sb.append("№").append(itemNum++).append(" ").append(i.toString());
            System.out.println(sb.toString());
        }
    }
}
