package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import technology.tabula.extractors.ExtractionAlgorithm;
import technology.tabula.Table;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class TablegetColCountTest {
    private Table table;

    @Before
    public void setUp() throws Exception {
        ExtractionAlgorithm extractionAlgorithm = new ExtractionAlgorithm() {
            @Override
            public String toString() {
                return "test";
            }

            @Override
            public List<Table> extract(Page page) {
                return new ArrayList<Table>();
            }
        };
        table = new Table(extractionAlgorithm);
    }

    @Test
    public void testGetColCount() {
        // Verify the method returns the expected value
        Assert.assertEquals(0, table.getColCount());
    }
}
