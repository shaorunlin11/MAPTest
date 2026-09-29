package com.zappos.json.wrapper;

import org.junit.Test;
import static org.junit.Assert.*;

public class TypeWrappergetTargetTest {
    @Test
    public void testGetTarget() {
        // Since TypeWrapper is an interface and getTarget is an abstract method,
        // we need to create a concrete implementation to test it
        TypeWrapper<String> wrapper = new TypeWrapper<String>() {
            private String target = "testValue";

            @Override
            public String getTarget() {
                return target;
            }

            @Override
            public void setTarget(String target) {
                this.target = target;
            }
        };

        assertEquals("testValue", wrapper.getTarget());
    }
}
