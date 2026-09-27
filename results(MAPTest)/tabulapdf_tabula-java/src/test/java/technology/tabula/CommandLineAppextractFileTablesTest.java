package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.io.File;
import java.io.StringWriter;
import java.io.IOException;
import org.apache.commons.cli.CommandLineParser;
public class CommandLineAppextractFileTablesTest {
    private CommandLineApp commandLineApp;
    private StringWriter defaultOutput;
    private File pdfFile;

    @Before
    public void setUp() throws Exception {
        defaultOutput = new StringWriter();
        Options options = new Options();
        options.addOption("o", "output", true, "Output file");
        options.addOption("s", "password", true, "Password for PDF");
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, new String[]{});
        commandLineApp = new CommandLineApp(defaultOutput, line);
        pdfFile = new File("test.pdf");
    }

    @After
    public void tearDown() throws IOException {
        defaultOutput.close();
    }

}
