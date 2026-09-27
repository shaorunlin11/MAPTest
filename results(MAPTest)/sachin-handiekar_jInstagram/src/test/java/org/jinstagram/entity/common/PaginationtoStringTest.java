package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class PaginationtoStringTest {

    @Test
    public void testToString() throws Exception {
        Pagination pagination = new Pagination();
        // Use reflection to set private fields
        Field depreciationWarningField = Pagination.class.getDeclaredField("depreciationWarning");
        depreciationWarningField.setAccessible(true);
        depreciationWarningField.set(pagination, "Deprecation warning message");

        Field minTagIdField = Pagination.class.getDeclaredField("minTagId");
        minTagIdField.setAccessible(true);
        minTagIdField.set(pagination, "min_tag_id_value");

        Field nextMaxIdField = Pagination.class.getDeclaredField("nextMaxId");
        nextMaxIdField.setAccessible(true);
        nextMaxIdField.set(pagination, "next_max_id_value");

        Field nextMaxTagIdField = Pagination.class.getDeclaredField("nextMaxTagId");
        nextMaxTagIdField.setAccessible(true);
        nextMaxTagIdField.set(pagination, "next_max_tag_id_value");

        Field nextMinIdField = Pagination.class.getDeclaredField("nextMinId");
        nextMinIdField.setAccessible(true);
        nextMinIdField.set(pagination, "next_min_id_value");

        Field nextUrlField = Pagination.class.getDeclaredField("nextUrl");
        nextUrlField.setAccessible(true);
        nextUrlField.set(pagination, "https://example.com/next");

        String result = pagination.toString();

        assertEquals("Pagination [depreciationWarning=Deprecation warning message, minTagId=min_tag_id_value, nextMaxId=next_max_id_value, nextMaxTagId=next_max_tag_id_value, nextMinId=next_min_id_value, nextUrl=https://example.com/next]", result);
    }
}
