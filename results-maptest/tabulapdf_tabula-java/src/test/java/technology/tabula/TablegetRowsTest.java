package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import technology.tabula.extractors.ExtractionAlgorithm;
import technology.tabula.CellPosition;
import technology.tabula.RectangularTextContainer;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
public class TablegetRowsTest {
    private Table table;
    private List<List<RectangularTextContainer>> mockRows;

    @Before
    public void setUp() throws Exception {
        // Create a mock Table instance using reflection to bypass constructor restrictions
        Class<?> tableClass = Class.forName("technology.tabula.Table");
        Constructor<?> constructor = tableClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        table = (Table) constructor.newInstance("testMethod");

        // Initialize mock rows
        mockRows = new ArrayList<>();
        List<RectangularTextContainer> row1 = new ArrayList<>();
        row1.add(new RectangularTextContainer(0.0f, 0.0f, 0.0f, 0.0f));
        mockRows.add(row1);

        // Use reflection to set the memoizedRows field
        Field memoizedRowsField = tableClass.getDeclaredField("memoizedRows");
        memoizedRowsField.setAccessible(true);
        memoizedRowsField.set(table, mockRows);
    }

    @Test
    public void testGetRowsReturnsMemoizedValue() {
        List<List<RectangularTextContainer>> result = table.getRows();
        Assert.assertEquals(mockRows, result);
    }
}
