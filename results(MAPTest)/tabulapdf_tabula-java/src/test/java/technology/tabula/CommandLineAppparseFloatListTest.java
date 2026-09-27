package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.cli.ParseException;

public class CommandLineAppparseFloatListTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testParseFloatList_ValidInput_ReturnsListOfFloats() throws Exception {
        String option = "1.5,2.3,3.0";
        List<Float> result = CommandLineApp.parseFloatList(option);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1.5f, result.get(0), 0.0001);
        Assert.assertEquals(2.3f, result.get(1), 0.0001);
        Assert.assertEquals(3.0f, result.get(2), 0.0001);
    }

    @Test
    public void testParseFloatList_InvalidInput_ThrowsParseException() throws Exception {
        String option = "1.5,abc,3.0";
        exception.expect(ParseException.class);
        exception.expectMessage("Wrong number syntax");
        CommandLineApp.parseFloatList(option);
    }

    @Test
    public void testParseFloatList_NullInput_ThrowsNullPointerException() throws Exception {
        String option = null;
        exception.expect(NullPointerException.class);
        CommandLineApp.parseFloatList(option);
    }

    @Test
    public void testParseFloatList_EmptyString_ThrowsParseException() throws Exception {
        String option = "";
        exception.expect(ParseException.class);
        exception.expectMessage("Wrong number syntax");
        CommandLineApp.parseFloatList(option);
    }

    @Test
    public void testParseFloatList_SingleElement_ReturnsSingleFloat() throws Exception {
        String option = "42.0";
        List<Float> result = CommandLineApp.parseFloatList(option);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(42.0f, result.get(0), 0.0001);
    }
}
