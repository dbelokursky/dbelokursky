package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 13.10.17.
 */
public class PrimeIterator implements Iterator<Integer> {

    private final int[] values;

    private int index = 0;

    private int nextPrime;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (isPrime(values[i])) {
                nextPrime = values[i];
                index = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            index++;
            return nextPrime;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean isPrime(int number) {
        boolean result = true;

        if (number == 1) {
            result = false;
        }

        if (number > 1) {
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
