package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MathFunctionTest {

    @Test
    public void calcLinearFunc() {
        MathFunction mathFunction = new MathFunction();
        List<Double> result = mathFunction.calcFunctionOnRange(1, 1, mathFunction::calcLinearFunc);
        List<Double> expected = Arrays.asList(-1.5);
        assertThat(result, is(expected));
    }

    @Test
    public void calcQuadraticFunc() {
        MathFunction mathFunction = new MathFunction();
        List<Double> result = mathFunction.calcFunctionOnRange(1, 2, mathFunction::calcQuadraticFunc);
        List<Double> expected = Arrays.asList(1.0, 4.0);
        assertThat(result, is(expected));
    }

    @Test
    public void calcLogarithmicFunc() {
        MathFunction mathFunction = new MathFunction();
        List<Double> result = mathFunction.calcFunctionOnRange(1, 2, mathFunction::calcLogarithmicFunc);
        List<Double> expected = Arrays.asList(0.0, 0.6931471805599453);
        assertThat(result, is(expected));
    }
}