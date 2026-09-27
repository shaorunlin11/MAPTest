package org.apache.commons.cli;

import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HelpFormattergetOptionComparatorTest {
    @Test
    public void testGetOptionComparatorReturnsNonNullComparator() {
        HelpFormatter formatter = new HelpFormatter();
        Comparator<Option> comparator = formatter.getOptionComparator();
        assertNotNull("getOptionComparator should return a non-null Comparator", comparator);
    }

    @Test
    public void testGetOptionComparatorReturnsSameInstance() {
        HelpFormatter formatter = new HelpFormatter();
        Comparator<Option> comparator1 = formatter.getOptionComparator();
        Comparator<Option> comparator2 = formatter.getOptionComparator();
        assertEquals("getOptionComparator should return the same instance each time", comparator1, comparator2);
    }
}
