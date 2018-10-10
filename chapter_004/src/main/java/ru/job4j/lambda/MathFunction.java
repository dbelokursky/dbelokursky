package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MathFunction {

    public List<Double> calcFunctionOnRange(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

    /**
     * y = 0.5 * x − 2
     *
     * @param x
     * @return y
     */
    public Double calcLinearFunc(Double x) {
        return 0.5 * x - 2;
    }

    /**
     * y = x * x
     *
     * @param x
     * @return y
     */
    public Double calcQuadraticFunc(Double x) {
        return x * x;
    }

    /**
     * y = log(x)
     *
     * @param x
     * @return
     */
    public Double calcLogarithmicFunc(Double x) {
        return Math.log(x);
    }

}
