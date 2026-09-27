package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Stack;

public class QuickSortsort_49a91948Test {
    private List<Integer> randomAccessList;
    private List<Integer> nonRandomAccessList;

    @Before
    public void setUp() {
        randomAccessList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            randomAccessList.add(i);
        }
        nonRandomAccessList = new ArrayList<Integer>(randomAccessList);
    }

    @After
    public void tearDown() {
        randomAccessList = null;
        nonRandomAccessList = null;
    }

    @Test
    public void testSortWithRandomAccessList() {
        QuickSort.sort(randomAccessList, Comparator.naturalOrder());
        Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4), randomAccessList);
    }

    @Test
    public void testSortWithNonRandomAccessList() {
        QuickSort.sort(nonRandomAccessList, Comparator.naturalOrder());
        Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4), nonRandomAccessList);
    }

    @Test
    public void testSortWithCustomComparator() {
        List<String> stringList = new ArrayList<>();
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("cherry");

        QuickSort.sort(stringList, Comparator.comparing(String::length));
        Assert.assertEquals(Arrays.asList("apple", "banana", "cherry"), stringList);
    }

    @Test
    public void testSortEmptyList() {
        List<Integer> emptyList = new ArrayList<>();
        QuickSort.sort(emptyList, Comparator.naturalOrder());
        Assert.assertTrue(emptyList.isEmpty());
    }

    @Test
    public void testSortWithNullElements() {
        List<String> listWithNulls = new ArrayList<>();
        listWithNulls.add("b");
        listWithNulls.add(null);
        listWithNulls.add("a");

        QuickSort.sort(listWithNulls, Comparator.nullsFirst(Comparator.naturalOrder()));
        Assert.assertEquals(Arrays.asList(null, "a", "b"), listWithNulls);
    }
}
