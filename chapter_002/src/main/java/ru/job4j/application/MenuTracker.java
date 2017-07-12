package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 09.07.17.
 */

class EditItem extends BaseAction {

    @Override
    public String info() {
        return super.info(this.key(), "Edit item.");
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

    private int position = 0;

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public UserAction[] getActions() {
        return actions;
    }

    public void fillActions() {
        this.actions[position++] = this.new AddItem();
        this.actions[position++] = new MenuTracker.ShowItems();
        this.actions[position++] = new EditItem();
        this.actions[position++] = this.new DeleteItem();
        this.actions[position++] = this.new FindItemById();
        this.actions[position++] = this.new FindItemByName();
    }

    public void addAction(UserAction action) {
        this.actions[position++] = action;
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

    private static class ShowItems extends BaseAction {

        @Override
        public String info() {
            return super.info(this.key(), "Show items.");
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

    private class AddItem extends BaseAction {

        @Override
        public String info() {
            return super.info(this.key(), "Add the new item.");
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

    private class DeleteItem extends BaseAction {

        @Override
        public String info() {
            return super.info(this.key(), "Delete item.");
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

    private class FindItemById extends BaseAction {

        @Override
        public String info() {
            return super.info(this.key(), "Find item by id.");
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

    private class FindItemByName extends BaseAction {

        @Override
        public String info() {
            return super.info(this.key(), "Find item by name.");
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
