package org.apache.commons.cli;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GnuParserflattenTest {

    @Test
    public void testFlattenWithEmptyArguments() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[0], false);
        assertEquals(0, result.length);
    }

    @Test
    public void testFlattenWithSingleDash() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"-"}, false);
        assertEquals(1, result.length);
        assertEquals("-", result[0]);
    }

    @Test
    public void testFlattenWithDoubleDash() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"--"}, false);
        assertEquals(1, result.length);
        assertEquals("--", result[0]);
    }

    @Test
    public void testFlattenWithOptionWithoutValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        String[] result = parser.flatten(options, new String[] {"-a"}, false);
        assertEquals(1, result.length);
        assertEquals("-a", result[0]);
    }

    @Test
    public void testFlattenWithOptionWithValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        String[] result = parser.flatten(options, new String[] {"--a=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--a", result[0]);
        assertEquals("value", result[1]);
    }

    @Test
    public void testFlattenWithMultiCharacterOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("ab", "option ab");
        String[] result = parser.flatten(options, new String[] {"--ab=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--ab", result[0]);
        assertEquals("value", result[1]);
    }

    @Test
    public void testFlattenWithNonOptionAndStopAtNonOptionTrue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        String[] result = parser.flatten(options, new String[] {"-a", "non-option", "rest"}, true);
        assertEquals(3, result.length);
        assertEquals("-a", result[0]);
        assertEquals("non-option", result[1]);
        assertEquals("rest", result[2]);
    }

    @Test
    public void testFlattenWithNonOptionAndStopAtNonOptionFalse() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        String[] result = parser.flatten(options, new String[] {"-a", "non-option", "rest"}, false);
        assertEquals(3, result.length);
        assertEquals("-a", result[0]);
        assertEquals("non-option", result[1]);
        assertEquals("rest", result[2]);
    }

    @Test
    public void testFlattenWithMultipleOptionsAndValues() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        options.addOption("b", "option b");
        String[] result = parser.flatten(options, new String[] {"-a", "--b=value", "arg"}, false);
        assertEquals(4, result.length);
        assertEquals("-a", result[0]);
        assertEquals("--b", result[1]);
        assertEquals("value", result[2]);
        assertEquals("arg", result[3]);
    }

    @Test
    public void testFlattenWithOptionSplitByEquals() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }

    @Test
    public void testFlattenWithOptionSplitByTwoCharacters() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("ab", "option ab");
        String[] result = parser.flatten(options, new String[] {"--ab=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--ab", result[0]);
        assertEquals("value", result[1]);
    }

@Test
    public void testFlattenWithSpecialPropertiesOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop=value"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop=value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatMatchesFirstTwoCharsButNotFullOpt() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop", result[1]);
    }

@Test
    public void testFlattenWithOptionThatMatchesFirstTwoCharsAndHasValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop=value"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop=value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatMatchesFirstTwoCharsAndNoValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop", result[1]);
    }

@Test
    public void testFlattenWithDoubleDashToEatTheRest() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"--", "arg1", "arg2"}, false);
        assertEquals(3, result.length);
        assertEquals("--", result[0]);
        assertEquals("arg1", result[1]);
        assertEquals("arg2", result[2]);
    }

@Test
    public void testFlattenWithDoubleDashAndStopAtNonOptionTrue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"--", "-a", "arg"}, true);
        assertEquals(3, result.length);
        assertEquals("--", result[0]);
        assertEquals("-a", result[1]);
        assertEquals("arg", result[2]);
    }

@Test
    public void testFlattenWithDoubleDashAndStopAtNonOptionFalse() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"--", "-a", "arg"}, false);
        assertEquals(3, result.length);
        assertEquals("--", result[0]);
        assertEquals("-a", result[1]);
        assertEquals("arg", result[2]);
    }

@Test
    public void testFlattenWithOptionThatDoesNotMatchButHasAnotherOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        options.addOption("b", "option b");
        String[] result = parser.flatten(options, new String[] {"-c"}, false);
        assertEquals(1, result.length);
        assertEquals("-c", result[0]);
    }

@Test
    public void testFlattenWithOptionThatMatchesFirstTwoCharsButNotFullOptAndNoValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop", result[1]);
    }

@Test
    public void testFlattenWithOptionThatMatchesFirstTwoCharsButNotFullOptAndHasValue() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("D", "property");
        String[] result = parser.flatten(options, new String[] {"-Dprop=value"}, false);
        assertEquals(2, result.length);
        assertEquals("-D", result[0]);
        assertEquals("prop=value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatDoesNotHaveOptionButHasAnotherOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("a", "option a");
        String[] result = parser.flatten(options, new String[] {"-b"}, false);
        assertEquals(1, result.length);
        assertEquals("-b", result[0]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignButNoMatchingOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(1, result.length);
        assertEquals("--key=value", result[0]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignAndNoMatchingOptionButHasAnotherOption() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignAndNoMatchingOptionButHasAnotherOptionWithSamePrefix() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        options.addOption("keys", "keys option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignAndNoMatchingOptionButHasAnotherOptionWithDifferentPrefix() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        options.addOption("other", "other option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignAndNoMatchingOptionButHasAnotherOptionWithSameName() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }

@Test
    public void testFlattenWithOptionThatHasEqualSignAndNoMatchingOptionButHasAnotherOptionWithSameNameAndDifferentType() {
        GnuParser parser = new GnuParser();
        Options options = new Options();
        options.addOption("key", "key option");
        options.addOption("key", "another key option");
        String[] result = parser.flatten(options, new String[] {"--key=value"}, false);
        assertEquals(2, result.length);
        assertEquals("--key", result[0]);
        assertEquals("value", result[1]);
    }
}
