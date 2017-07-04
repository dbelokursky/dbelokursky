package ru.job4j.application;

import java.util.Scanner;

/**
 * @author Dmitry Belokursky
 * @since 03.07.17.
 */
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public int menuSelect() {
        boolean exitProgram = false;
        int menuItem = 0;
        Tracker tracker = new Tracker();

        while (!exitProgram) {
            System.out.println("Select: ");
            menuItem = scanner.nextInt();

            switch (menuItem) {
                case 0://Добавление заявки.
                    System.out.println("Введите имя заявки: ");
                    scanner = new Scanner(System.in);
                    String name = scanner.nextLine();
                    System.out.println("Введите описание заявки: ");
                    String description = scanner.nextLine();
                    Item item = new Item(name, description);
                    tracker.add(item);
                    System.out.println("Заявка добавлена.");
                    break;
                case 1://Вывод списка заявок.
                    if (tracker.findAll().length <= 0) {
                        System.out.println("Заявки отсутствуют.");
                    } else {
                        System.out.println("Список заявок: ");
                        int itemNum = 1;
                        for (Item i : tracker.findAll()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("№").append(itemNum++).append(" ").append(i.toString());
                            System.out.println(sb.toString());
                        }
                    }
                    break;
                case 2://Редактирование заявки.
                    System.out.println("Редактирование заявки.");
                    System.out.println("Введите ID заявки: ");
                    scanner = new Scanner(System.in);
                    String id = scanner.nextLine();
                    System.out.println("Введите новое имя заявки.");
                    name = scanner.nextLine();
                    System.out.println("Введите новое описание заявки: ");
                    description = scanner.nextLine();
                    item = new Item(name, description);
                    item.setId(id);
                    tracker.update(item);
                    System.out.println("Заявка отредактированна.");
                    break;
                case 3://Удаление заявки.
                    System.out.println("Удаление заявки");
                    System.out.println("Введите ID заявки: ");
                    scanner = new Scanner(System.in);
                    id = scanner.nextLine();
                    item = tracker.findById(id);
                    if (item != null) {
                        tracker.delete(item);
                        System.out.println("Заявка удалена.");
                    } else {
                        System.out.println("Заявка не найдена.");
                    }
                    break;
                case 4://Поиск по заявки ID.
                    System.out.println("Поиск заявки по ID.");
                    System.out.println("Введите ID заявки: ");
                    scanner = new Scanner(System.in);
                    id = scanner.nextLine();
                    item = tracker.findById(id);
                    if (item != null) {
                        System.out.println(item.toString());
                    } else {
                        System.out.println("Заявка не найдена.");
                    }
                    break;
                case 5://Поиск заявок по названию.
                    System.out.println("Поиск заявок по названию.");
                    System.out.println("Введите название заявки: ");
                    scanner = new Scanner(System.in);
                    name = scanner.nextLine();
                    Item[] foundItems = null;
                    foundItems = tracker.findByName(name);
                    if (foundItems.length >= 1) {
                        for (Item i : foundItems) {
                            System.out.println(i.toString());;
                        }
                    } else {
                        System.out.println("Заявки не найдены.");
                    }
                    break;
                case 6://Завершение программы.
                    System.out.println("Выход из программы.");
                    exitProgram = true;
            }
        }
        return menuItem ;
    }
}
