package org.apache.commons.csv;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.END_OF_STREAM;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Constants.UNDEFINED;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
public class ExtendedBufferedReaderreadLineTest {
    private ExtendedBufferedReader reader;
    private Reader mockReader;

    @Before
    public void setUp() throws Exception {
        mockReader = new StringReader("test\nline");
        reader = new ExtendedBufferedReader(mockReader);
    }

    @After
    public void tearDown() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

    @Test
    public void test() throws IOException {
        Assert.assertEquals("test", reader.readLine());
        Assert.assertEquals("line", reader.readLine());
        Assert.assertNull(reader.readLine());
    }
}
