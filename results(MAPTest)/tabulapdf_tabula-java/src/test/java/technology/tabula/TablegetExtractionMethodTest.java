package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
public class TablegetExtractionMethodTest {
    @Test
    public void testGetExtractionMethod() throws Exception {
        // Arrange
        String expectedExtractionMethod = "TEST_METHOD";
        Class<?> tableClass = Class.forName("technology.tabula.Table");
        Constructor<?> constructor = tableClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object tableInstance = constructor.newInstance(expectedExtractionMethod);

        // Act
        Method method = tableClass.getMethod("getExtractionMethod");
        Object result = method.invoke(tableInstance);

        // Assert
        assertEquals(expectedExtractionMethod, result);
    }
}
