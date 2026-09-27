package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class Utilssort_49a91948Test {
    private boolean originalUseQuickSort;

    @Before
    public void setUp() throws Exception {
        originalUseQuickSort = Utils.useQuickSort;
    }

    @After
    public void tearDown() throws Exception {
        Utils.useQuickSort = originalUseQuickSort;
    }

    @Test
    public void testSortWithUseQuickSortTrue() throws Exception {
        // Arrange
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
        Comparator<Integer> comparator = Integer::compareTo;

        // Set useQuickSort to true
        Utils.useQuickSort = true;

        // Act
        Utils.sort(list, comparator);

        // Assert
        Assert.assertEquals(Arrays.asList(1, 2, 3), list);
    }

    @Test
    public void testSortWithUseQuickSortFalse() throws Exception {
        // Arrange
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
        Comparator<Integer> comparator = Integer::compareTo;

        // Set useQuickSort to false
        Utils.useQuickSort = false;

        // Act
        Utils.sort(list, comparator);

        // Assert
        Assert.assertEquals(Arrays.asList(1, 2, 3), list);
    }
}
