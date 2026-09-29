package com.zappos.json;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.zappos.json.annot.JsonEnum.EnumValue;
import com.zappos.json.format.ValueFormatter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
public class JsonBeanAttributesetEnumValueTest {

    @Test
    public void testSetEnumValueWithNull() throws Exception {
        // Arrange
        Method method = Method.class.getDeclaredMethod("toString");
        Field field = Field.class.getDeclaredField("name");
        String attributeKey = "testAttribute";
        JsonBeanAttribute attribute = new JsonBeanAttribute(method, field, attributeKey);

        // Act
        JsonBeanAttribute result = attribute.setEnumValue(null);

        // Assert
        assertNotNull(result);
        assertEquals(attribute, result);
        Field enumValueField = JsonBeanAttribute.class.getDeclaredField("enumValue");
        enumValueField.setAccessible(true);
        assertNull(enumValueField.get(attribute));
    }
}
