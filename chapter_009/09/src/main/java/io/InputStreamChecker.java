package io;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamChecker {

    static boolean isEven(InputStream inputStream) {
        boolean result = false;
        try (Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num % 2 == 0) {
                    result = true;
                }
            }
        }
        return result;
    }
}
