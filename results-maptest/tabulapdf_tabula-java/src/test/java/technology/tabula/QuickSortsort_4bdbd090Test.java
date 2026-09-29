package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RunWith(JUnit4.class)
public class QuickSortsort_4bdbd090Test {
    private List<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);
    }

    @After
    public void tearDown() {
        list = null;
    }

    @Test
    public void testSortWithNaturalOrder() {
        QuickSort.sort(list);
        Assert.assertEquals("List should be sorted in natural order", Integer.valueOf(1), list.get(0));
        Assert.assertEquals("List should be sorted in natural order", Integer.valueOf(3), list.get(1));
        Assert.assertEquals("List should be sorted in natural order", Integer.valueOf(5), list.get(2));
        Assert.assertEquals("List should be sorted in natural order", Integer.valueOf(8), list.get(3));
    }
}
