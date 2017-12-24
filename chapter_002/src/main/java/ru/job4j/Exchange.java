package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Exchange {

    private List<Integer> waysOfExchange;

    private StringBuilder textRepresentation;

    public Exchange() {
        this.waysOfExchange = new ArrayList<>();
        this.textRepresentation = new StringBuilder();
    }

    public String getExchangeWays(int denomination, int[] coins) {
        calculateExchangeWays(denomination, coins, 0);
        return textRepresentation.toString();
    }

    private void calculateExchangeWays(int denomination, int[] coins, int position) {
        if (denomination == 0) {
            for (int r : this.waysOfExchange) {
                this.textRepresentation.append(r).append(" ");
            }
            this.textRepresentation.append(System.getProperty("line.separator"));
        }
        for (int i = position; i < coins.length; i++) {
            if (denomination >= coins[i]) {
                this.waysOfExchange.add(coins[i]);
                calculateExchangeWays(denomination - coins[i], coins, i);
                this.waysOfExchange.remove(this.waysOfExchange.size() - 1);
            }
        }
    }
}