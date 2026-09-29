package org.apache.commons.codec.language.bm;

import org.junit.Test;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.apache.commons.codec.language.bm.Languages.LanguageSet;

import java.util.NoSuchElementException;


public class LanguagesLanguageSetTest {

    @Test
    public void testFromWithEmptySetReturnsNoLanguages() {
        Set<String> emptySet = Collections.emptySet();
        LanguageSet result = Languages.LanguageSet.from(emptySet);
        assertTrue(result instanceof Languages.LanguageSet);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFromWithNonEmptySetReturnsSomeLanguages() {
        Set<String> nonEmptySet = new HashSet<>();
        nonEmptySet.add("en");
        LanguageSet result = Languages.LanguageSet.from(nonEmptySet);
        assertTrue(result instanceof Languages.SomeLanguages);
    }

    @Test
    public void testNoLanguagesContainsReturnsFalse() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        assertFalse(noLanguages.contains("en"));
        assertFalse(noLanguages.contains("fr"));
    }

    @Test
    public void testNoLanguagesIsEmptyReturnsTrue() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        assertTrue(noLanguages.isEmpty());
    }

    @Test
    public void testNoLanguagesIsSingletonReturnsFalse() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        assertFalse(noLanguages.isSingleton());
    }

    @Test
    public void testAnyLanguageContainsReturnsTrue() {
        LanguageSet anyLanguage = Languages.ANY_LANGUAGE;
        assertTrue(anyLanguage.contains("en"));
        assertTrue(anyLanguage.contains("fr"));
    }

    @Test
    public void testAnyLanguageIsEmptyReturnsFalse() {
        LanguageSet anyLanguage = Languages.ANY_LANGUAGE;
        assertFalse(anyLanguage.isEmpty());
    }

    @Test
    public void testAnyLanguageIsSingletonReturnsFalse() {
        LanguageSet anyLanguage = Languages.ANY_LANGUAGE;
        assertFalse(anyLanguage.isSingleton());
    }

    @Test
    public void testRestrictToOnNoLanguagesReturnsSelf() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        LanguageSet other = Languages.ANY_LANGUAGE;
        LanguageSet result = noLanguages.restrictTo(other);
        assertSame(noLanguages, result);
    }

    @Test
    public void testMergeOnNoLanguagesReturnsOther() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        LanguageSet other = Languages.ANY_LANGUAGE;
        LanguageSet result = noLanguages.merge(other);
        assertSame(other, result);
    }

    @Test
    public void testRestrictToOnAnyLanguageReturnsOther() {
        LanguageSet anyLanguage = Languages.ANY_LANGUAGE;
        LanguageSet other = Languages.NO_LANGUAGES;
        LanguageSet result = anyLanguage.restrictTo(other);
        assertSame(other, result);
    }

    @Test
    public void testMergeOnAnyLanguageReturnsOther() {
        LanguageSet anyLanguage = Languages.ANY_LANGUAGE;
        LanguageSet other = Languages.NO_LANGUAGES;
        LanguageSet result = anyLanguage.merge(other);
        assertSame(other, result);
    }

@Test
    public void testNoLanguagesGetAnyThrowsNoSuchElementException() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        try {
            noLanguages.getAny();
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }

@Test
    public void testNoLanguagesToString() {
        LanguageSet noLanguages = Languages.NO_LANGUAGES;
        assertEquals("NO_LANGUAGES", noLanguages.toString());
    }
}
