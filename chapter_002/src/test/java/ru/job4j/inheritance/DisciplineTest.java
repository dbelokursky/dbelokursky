package ru.job4j.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class DisciplineTest для тестирования класса Discipline.
 * Created by db on 27.06.17.
 */
public class DisciplineTest {

    /**
     * Тестирование геттеров/сеттеров.
     */
    @Test
    public void setNameGetNameTest() {
        Discipline discipline = new Discipline();
        discipline.setName("Литература");
        String result = discipline.getName();
        String expected = "Литература";
        assertThat(result, is(expected));
    }
}
