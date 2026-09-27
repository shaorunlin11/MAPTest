package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;


public class HelpFormattersetOptionComparatorTest {
    private HelpFormatter helpFormatter;
    private Comparator<Option> mockComparator;

    @Before
    public void setUp() {
        helpFormatter = new HelpFormatter();
        mockComparator = new Comparator<Option>() {
            @Override
            public int compare(Option o1, Option o2) {
                return 0;
            }
        };
    }

    @After
    public void tearDown() {
        helpFormatter = null;
        mockComparator = null;
    }

    @Test
    public void testSetOptionComparator() throws Exception {
        // Act
        helpFormatter.setOptionComparator(mockComparator);

        // Assert
        Field optionComparatorField = HelpFormatter.class.getDeclaredField("optionComparator");
        optionComparatorField.setAccessible(true);
        Comparator<Option> result = (Comparator<Option>) optionComparatorField.get(helpFormatter);
        Assert.assertEquals(mockComparator, result);
    }
}
