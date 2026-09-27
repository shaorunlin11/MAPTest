package humanevaltest.original.task74;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
public class SolutiontotalMatchTest {
    @Test
    public void testTotalMatchEmptyLists() {
        Solution solution = new Solution();
        List<String> lst1 = new ArrayList<>();
        List<String> lst2 = new ArrayList<>();
        List<String> result = solution.totalMatch(lst1, lst2);
        assertSame(lst1, result);
    }

    @Test
    public void testTotalMatchLst1Shorter() {
        Solution solution = new Solution();
        List<String> lst1 = new ArrayList<>();
        lst1.add("a");
        lst1.add("bc");
        List<String> lst2 = new ArrayList<>();
        lst2.add("def");
        lst2.add("gh");
        List<String> result = solution.totalMatch(lst1, lst2);
        assertSame(lst1, result);
    }

    @Test
    public void testTotalMatchLst2Shorter() {
        Solution solution = new Solution();
        List<String> lst1 = new ArrayList<>();
        lst1.add("abc");
        lst1.add("de");
        List<String> lst2 = new ArrayList<>();
        lst2.add("f");
        List<String> result = solution.totalMatch(lst1, lst2);
        assertSame(lst2, result);
    }


    @Test
    public void testTotalMatchLst1Empty() {
        Solution solution = new Solution();
        List<String> lst1 = new ArrayList<>();
        List<String> lst2 = new ArrayList<>();
        lst2.add("hello");
        List<String> result = solution.totalMatch(lst1, lst2);
        assertSame(lst1, result);
    }

    @Test
    public void testTotalMatchLst2Empty() {
        Solution solution = new Solution();
        List<String> lst1 = new ArrayList<>();
        lst1.add("world");
        List<String> lst2 = new ArrayList<>();
        List<String> result = solution.totalMatch(lst1, lst2);
        assertSame(lst2, result);
    }
}
