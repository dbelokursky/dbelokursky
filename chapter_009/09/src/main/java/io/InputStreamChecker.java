package io;

import lombok.extern.log4j.Log4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Log4j
public class InputStreamChecker {

    boolean isEven(InputStream inputStream) {
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

    void dropAbusive(InputStream is, OutputStream os, String[] abusiveWords) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String word : abusiveWords) {
                    line = line.replaceAll(word, "");
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
