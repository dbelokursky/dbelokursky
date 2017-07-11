package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 09.07.17.
 */

class EditItem implements UserAction {

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item.");
    }

    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID заявки: ");
        String name = input.ask("Введите новое имя заявки :");
        String description = input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, description);
        item.setId(id);
        tracker.update(item);
    }
}

public class MenuTracker {

    private Input input;

    private Tracker tracker;

    private UserAction[] actions = new UserAction[6];

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public UserAction[] getActions() {
        return actions;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindItemById();
        this.actions[5] = this.new FindItemByName();
    }

    public int[] getMenuRange() {
        int[] range = new int[this.getActions().length];
        for (int i = 0; i < this.getActions().length; i++) {
            range[i] = i;
        }
        return range;
    }

    public void show() {
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    private void itemNotFound() {
        System.out.println("Заявка не найдена.");
    }

    private class AddItem implements UserAction {

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }

        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            tracker.add(new Item(name, desc));
        }
    }

    private static class ShowItems implements UserAction {

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show items.");
        }

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Список заявок: ");
            int itemNum = 1;
            for (Item i : tracker.findAll()) {
                StringBuilder sb = new StringBuilder();
                sb.append("№").append(itemNum++).append(" ").append(i.toString());
                System.out.println(sb.toString());
            }
        }
    }

    private class DeleteItem implements UserAction {

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item.");
        }

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки: ");
            Item item = tracker.findById(id);
            if (item != null) {
                tracker.delete(item);
                System.out.println("Заявка удалена.");
            } else {
                itemNotFound();
            }
        }
    }

    private class FindItemById implements UserAction {

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id.");
        }

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item.toString());
            } else {
                itemNotFound();
            }
        }
    }

    private class FindItemByName implements UserAction {

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by name.");
        }

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите название заявки: ");
            Item[] foundItems = null;
            foundItems = tracker.findByName(name);
            if (foundItems.length >= 1) {
                for (Item i : foundItems) {
                    System.out.println(i.toString());
                }
            } else {
                itemNotFound();
            }
        }
    }
}
