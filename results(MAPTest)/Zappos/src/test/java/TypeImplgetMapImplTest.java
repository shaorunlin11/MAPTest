package com.zappos.json.util;

import org.junit.Test;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class TypeImplgetMapImplTest {

    @Test
    public void testGetMapImpl_MapClass_returnsHashMap() {
        TypeImpl result = TypeImpl.getMapImpl(Map.class);
        assertNotNull(result);
        assertEquals(Map.class, getInfClass(result));
        assertEquals(HashMap.class, getImplClass(result));
    }

    @Test
    public void testGetMapImpl_AbstractMapClass_returnsHashMap() {
        TypeImpl result = TypeImpl.getMapImpl(AbstractMap.class);
        assertNotNull(result);
        assertEquals(AbstractMap.class, getInfClass(result));
        assertEquals(HashMap.class, getImplClass(result));
    }

    @Test
    public void testGetMapImpl_ConcurrentMapClass_returnsConcurrentHashMap() {
        TypeImpl result = TypeImpl.getMapImpl(ConcurrentMap.class);
        assertNotNull(result);
        assertEquals(ConcurrentMap.class, getInfClass(result));
        assertEquals(ConcurrentHashMap.class, getImplClass(result));
    }

    @Test
    public void testGetMapImpl_SortedMapClass_returnsTreeMap() {
        TypeImpl result = TypeImpl.getMapImpl(SortedMap.class);
        assertNotNull(result);
        assertEquals(SortedMap.class, getInfClass(result));
        assertEquals(TreeMap.class, getImplClass(result));
    }

    @Test
    public void testGetMapImpl_AbstractClass_throwsRuntimeException() {
        try {
            TypeImpl.getMapImpl(java.util.AbstractList.class);
            // Should not reach here
        } catch (RuntimeException e) {
            assertEquals("Cannot find appropriate implementation of collection type: java.util.AbstractList", e.getMessage());
        }
    }

    @Test
    public void testGetMapImpl_Interface_throwsRuntimeException() {
        try {
            TypeImpl.getMapImpl(List.class);
            // Should not reach here
        } catch (RuntimeException e) {
            assertEquals("Cannot find appropriate implementation of collection type: java.util.List", e.getMessage());
        }
    }

    @Test
    public void testGetMapImpl_DefaultClass_returnsItself() {
        TypeImpl result = TypeImpl.getMapImpl(ArrayList.class);
        assertNotNull(result);
        assertEquals(ArrayList.class, getInfClass(result));
        assertEquals(ArrayList.class, getImplClass(result));
    }

    private Object getInfClass(TypeImpl instance) {
        try {
            java.lang.reflect.Field field = TypeImpl.class.getDeclaredField("infClass");
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getImplClass(TypeImpl instance) {
        try {
            java.lang.reflect.Field field = TypeImpl.class.getDeclaredField("implClass");
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
