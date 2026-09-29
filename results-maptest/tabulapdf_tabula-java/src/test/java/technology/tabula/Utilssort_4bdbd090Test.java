package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

import java.lang.reflect.Field;


public class Utilssort_4bdbd090Test {
    @Test
    public void testSortUsesQuickSortWhenUseQuickSortIsTrue() throws Exception {
        // Arrange
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
        boolean originalUseQuickSort = Utils.useQuickSort;

        // Temporarily set useQuickSort to true
        Field useQuickSortField = Utils.class.getDeclaredField("useQuickSort");
        useQuickSortField.setAccessible(true);
        useQuickSortField.set(null, true);

        // Act
        Utils.sort(list);

        // Assert
        Assert.assertEquals(Arrays.asList(1, 2, 3), list);

        // Restore original value
        useQuickSortField.set(null, originalUseQuickSort);
    }

    @Test
    public void testSortUsesCollectionsSortWhenUseQuickSortIsFalse() throws Exception {
        // Arrange
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
        boolean originalUseQuickSort = Utils.useQuickSort;

        // Temporarily set useQuickSort to false
        Field useQuickSortField = Utils.class.getDeclaredField("useQuickSort");
        useQuickSortField.setAccessible(true);
        useQuickSortField.set(null, false);

        // Act
        Utils.sort(list);

        // Assert
        Assert.assertEquals(Arrays.asList(1, 2, 3), list);

        // Restore original value
        useQuickSortField.set(null, originalUseQuickSort);
    }
}
