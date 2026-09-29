package technology.tabula;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.CommandLineParser;
import java.io.StringWriter;
public class CommandLineAppextractTablesTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testExtractTablesWithBatchOptionAndFilename() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Filename specified with batch\nTry --help for help");
        CommandLineApp app = new CommandLineApp(new StringWriter(), createCommandLine("-b", "/path/to/dir", "file.pdf"));
        app.extractTables(createCommandLine("-b", "/path/to/dir", "file.pdf"));
    }

    @Test
    public void testExtractTablesWithBatchOptionAndInvalidDirectory() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Filename specified with batch\nTry --help for help");
        CommandLineApp app = new CommandLineApp(new StringWriter(), createCommandLine("-b", "/invalid/path", "file.pdf"));
        app.extractTables(createCommandLine("-b", "/invalid/path", "file.pdf"));
    }

    @Test
    public void testExtractTablesWithoutBatchOptionAndNoFilename() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Need exactly one filename\nTry --help for help");
        CommandLineApp app = new CommandLineApp(new StringWriter(), createCommandLine());
        app.extractTables(createCommandLine());
    }

    @Test
    public void testExtractTablesWithoutBatchOptionAndMultipleFilenames() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Need exactly one filename\nTry --help for help");
        CommandLineApp app = new CommandLineApp(new StringWriter(), createCommandLine("file1.pdf", "file2.pdf"));
        app.extractTables(createCommandLine("file1.pdf", "file2.pdf"));
    }

    @Test
    public void testExtractTablesWithoutBatchOptionAndNonExistentFile() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage("File does not exist");
        CommandLineApp app = new CommandLineApp(new StringWriter(), createCommandLine("nonexistent.pdf"));
        app.extractTables(createCommandLine("nonexistent.pdf"));
    }

    private CommandLine createCommandLine(String... args) throws Exception {
        Options options = new Options();
        options.addOption("b", "batch", false, "Process all PDFs in a directory");
        options.addOption("s", "password", true, "Password for encrypted PDFs");
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }
}
