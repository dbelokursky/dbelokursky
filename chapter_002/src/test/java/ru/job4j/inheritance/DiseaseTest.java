package ru.job4j.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class DiseaseTest для тестирования класса Disease.
 */
public class DiseaseTest {

    /**
     * Тестирование геттеров/сеттеров.
     */
    @Test
    public void setNameGetNameTest() {
        Disease disease = new Disease();
        disease.setName("Корь");
        String result = disease.getName();
        String expected = "Корь";
        assertThat(result, is(expected));
    }
}