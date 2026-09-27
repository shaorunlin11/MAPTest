package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandLineaddArgTest {
    private CommandLine commandLine;

    @Before
    public void setUp() {
        commandLine = new CommandLine();
    }

    @After
    public void tearDown() {
        commandLine = null;
    }

    @Test
    public void testAddArg_AddsArgumentToArgsList() throws Exception {
        String testArg = "testArgument";
        commandLine.addArg(testArg);

        List<String> expectedArgs = new ArrayList<>();
        expectedArgs.add(testArg);

        // Verify that the args list contains the added argument
        assert expectedArgs.containsAll(Arrays.asList(commandLine.getArgs()));
    }
}
