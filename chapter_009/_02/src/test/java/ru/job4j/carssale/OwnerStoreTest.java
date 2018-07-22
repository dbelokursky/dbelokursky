package ru.job4j.carssale;

import org.junit.Test;
import ru.job4j.carssale.models.Owner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OwnerStoreTest {

    @Test
    public void isExist() {
        OwnerStore ownerStore = new OwnerStore();
        Owner expected = null;
        Owner result = ownerStore.isExist("test", "test");
        assertThat(result, is(expected));
    }
}