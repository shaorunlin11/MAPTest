package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.lang.reflect.Field;


public class CommandLinegetOptionsTest {

    @Test
    public void testGetOptionsReturnsEmptyArrayWhenNoOptions() throws Exception {
        CommandLine commandLine = new CommandLine();
        Option[] options = commandLine.getOptions();
        assertTrue(options.length == 0);
    }

    @Test
    public void testGetOptionsReturnsArrayOfOptionsWhenOptionsArePresent() throws Exception {
        CommandLine commandLine = new CommandLine();
        List<Option> optionsList = new ArrayList<>();
        optionsList.add(new Option("a", "option a"));
        optionsList.add(new Option("b", "option b"));

        // Use reflection to set the private 'options' field
        Field optionsField = CommandLine.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(commandLine, optionsList);

        Option[] options = commandLine.getOptions();
        assertEquals(2, options.length);
        assertTrue(options[0] instanceof Option);
        assertTrue(options[1] instanceof Option);
    }
}
