package ru.job4j;

/**
 * @author Dmitry Belokursky
 * @since 13.08.17.
 */
public class Exchange {

    /**
     * Предлагает варианты размена купюры монетами номиналом 1, 2, 5, 10 рублей.
     *
     * @param den номинал купюры(10, 50, 100, 500, 1000, 5000).
     * @return строку с вариантами обмена.
     */
    public String change(int den) throws IllegalArgumentException {
        String result = "";
        if (den == 10 || den == 50 || den == 100 || den == 500 || den == 1000 || den == 5000) {
            int numberOfOneRubleCoins = den / 1;
            int numberOfTwoRubleCoins = den / 2;
            int numberOfFiveRubleCoins = den / 5;
            int numberOfTenRubleCoins = den / 10;
            StringBuilder sb = new StringBuilder();
            String lineSeparator = System.getProperty("line.separator");
            sb.append(numberOfOneRubleCoins).append(" монет номиналом 1 рубль").append(lineSeparator)
                    .append(numberOfTwoRubleCoins).append(" монет номиналом 2 рубля").append(lineSeparator)
                    .append(numberOfFiveRubleCoins).append(" монет номиналом 5 рублей").append(lineSeparator)
                    .append(numberOfTenRubleCoins).append(" монет номиналом 10 рублей");
            result = sb.toString();
        } else {
            throw new IllegalArgumentException("Номинал купюры может быть: 10, 50, 100, 500, 1000, 5000.");
        }
        return result;
    }
}
