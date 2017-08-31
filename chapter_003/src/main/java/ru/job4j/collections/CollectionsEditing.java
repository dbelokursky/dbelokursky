package ru.job4j.collections;

import java.util.Collection;

/**
 * @author Dmitry Belokursky
 * @since 27.08.17.
 */
public class CollectionsEditing {

//    public static void main(String[] args) {
////        CollectionsEditing collectionsEditing = new CollectionsEditing();
////        LinkedList<String> ll = new LinkedList<>();
////        System.out.println(collectionsEditing.add(ll, 1_000_000));
////        System.out.println(collectionsEditing.delete(ll, 100_000));
////        ArrayList<String> al = new ArrayList<>();
////        System.out.println(collectionsEditing.add(al, 1_000_000));
////        System.out.println(collectionsEditing.delete(al, 100_000));
//        TreeSet<String> ts = new TreeSet<>();
////        System.out.println(collectionsEditing.add(ts, 1_000_000));
//        System.out.println(collectionsEditing.delete(ts, 100_000));
//    }

    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        fillCollection(collection, amount);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public long delete(Collection<String> collection, int amount) {
        fillCollection(collection, amount);
        long startTime = System.currentTimeMillis();
        String[] tmpCollection = new String[collection.size()];
        tmpCollection = collection.toArray(tmpCollection);
        for (String word : tmpCollection) {
            collection.remove(word);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private void fillCollection(Collection<String> collection, int amount) {
        for (int i = 0; i < amount; i++) {
            double randomNum = Math.random() * 1_000_000;
            collection.add(Double.toString(randomNum));
        }
    }
}
