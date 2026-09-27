package humanevaltest.original.task162;
import org.junit.Test;
import org.junit.Assert;
import java.security.*;
import java.util.*;
public class SolutionstringToMd5Test {
    @Test
    public void testStringToMd5_emptyInput_returnsEmptyOptional() throws NoSuchAlgorithmException {
        Solution solution = new Solution();
        Optional<String> result = solution.stringToMd5("");
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testStringToMd5_nonEmptyInput_returnsPaddedMd5Hash() throws NoSuchAlgorithmException {
        Solution solution = new Solution();
        Optional<String> result = solution.stringToMd5("hello");
        String md5 = result.get();
        Assert.assertEquals(32, md5.length());
        Assert.assertTrue(md5.matches("[0-9a-f]+"));
    }

@Test
    public void testStringToMd5_nonEmptyInput_executesTargetLines() throws NoSuchAlgorithmException {
        Solution solution = new Solution();
        Optional<String> result = solution.stringToMd5("test");
        String md5 = result.get();
        Assert.assertEquals(32, md5.length());
        Assert.assertTrue(md5.matches("[0-9a-f]+"));
    }
}
