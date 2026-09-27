package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TypeHandleropenFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testOpenFileWithExistingFile() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        FileInputStream result = TypeHandler.openFile(tempFile.getAbsolutePath());
        assert result != null;
        result.close();
    }

    @Test
    public void testOpenFileWithNonExistingFile() throws Exception {
        String nonExistingFilePath = "non_existing_file.txt";
        thrown.expect(ParseException.class);
        thrown.expectMessage("Unable to find file: " + nonExistingFilePath);

        TypeHandler.openFile(nonExistingFilePath);
    }
}
