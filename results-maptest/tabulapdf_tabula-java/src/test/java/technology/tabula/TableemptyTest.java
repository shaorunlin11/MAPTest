package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;


public class TableemptyTest {
    @Test
    public void testEmptyMethodReturnsNonNullTable() {
        Table table = Table.empty();
        assertNotNull("empty() should return a non-null Table instance", table);
    }

    @Test
    public void testEmptyMethodUsesPrivateConstructorWithEmptyString() throws Exception {
        Table table = Table.empty();

        // Use reflection to access the private constructor
        Constructor<Table> constructor = Table.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        // Create an instance using the private constructor with an empty string
        Table expected = constructor.newInstance("");

        // Compare the actual instance with the expected one
        assertEquals("empty() should create a Table instance using the private constructor with an empty string", expected, table);
    }
}
