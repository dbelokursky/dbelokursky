package ru.job4j.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 29.08.17.
 */
public class ConvertList {

    public List<Integer> toList(int[][] array) {
        List<Integer> result = new LinkedList<>();
        for (int[] arr : array) {
            for (int num : arr) {
                result.add(num);
            }
        }
        return result;
    }

    public int[][] toArray(List<Integer> list, int rows) throws ArithmeticException {
        int listSize = list.size();
        try {
            int rowLength = listSize / rows;
            if (listSize % rows != 0) {
                rowLength++;
            }
            Iterator<Integer> iter = list.iterator();
            int result[][] = new int[rows][rowLength];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rowLength; j++) {
                    if (iter.hasNext()) {
                        result[i][j] = iter.next();
                    } else {
                        result[i][j] = 0;
                    }
                }
            }
            return result;
        } catch (ArithmeticException e) {
            throw new ArithmeticException();
        }
    }
}
