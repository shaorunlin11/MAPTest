package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;


public class RulePhonemeExprTest {

    @Test
    public void testPhonemeExprInterface() {
        // Since PhonemeExpr is an interface, we cannot instantiate it directly
        // We can verify that the interface exists and has the correct method
        assertTrue(Rule.PhonemeExpr.class.isInterface());

        // Check that the getPhonemes method exists
        Method[] methods = Rule.PhonemeExpr.class.getDeclaredMethods();
        boolean methodFound = false;
        for (Method method : methods) {
            if ("getPhonemes".equals(method.getName()) && 
                Iterable.class.isAssignableFrom(method.getReturnType())) {
                methodFound = true;
                break;
            }
        }
        assertTrue("PhonemeExpr should have a getPhonemes method returning Iterable<Phoneme>", methodFound);
    }
}
