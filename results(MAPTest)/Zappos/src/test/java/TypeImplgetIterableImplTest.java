package com.zappos.json.util;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.AbstractMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class TypeImplgetIterableImplTest {
    @Test
    public void testGetIterableImplForList() throws NoSuchFieldException, IllegalAccessException {
        TypeImpl result = TypeImpl.getIterableImpl(List.class);
        Field infClassField = result.getClass().getDeclaredField("infClass");
        Field implClassField = result.getClass().getDeclaredField("implClass");
        infClassField.setAccessible(true);
        implClassField.setAccessible(true);
        Assert.assertEquals(List.class, infClassField.get(result));
        Assert.assertEquals(ArrayList.class, implClassField.get(result));
    }

    @Test
    public void testGetIterableImplForCollection() throws NoSuchFieldException, IllegalAccessException {
        TypeImpl result = TypeImpl.getIterableImpl(Collection.class);
        Field infClassField = result.getClass().getDeclaredField("infClass");
        Field implClassField = result.getClass().getDeclaredField("implClass");
        infClassField.setAccessible(true);
        implClassField.setAccessible(true);
        Assert.assertEquals(Collection.class, infClassField.get(result));
        Assert.assertEquals(ArrayList.class, implClassField.get(result));
    }

    @Test
    public void testGetIterableImplForIterable() throws NoSuchFieldException, IllegalAccessException {
        TypeImpl result = TypeImpl.getIterableImpl(Iterable.class);
        Field infClassField = result.getClass().getDeclaredField("infClass");
        Field implClassField = result.getClass().getDeclaredField("implClass");
        infClassField.setAccessible(true);
        implClassField.setAccessible(true);
        Assert.assertEquals(Iterable.class, infClassField.get(result));
        Assert.assertEquals(ArrayList.class, implClassField.get(result));
    }

    @Test
    public void testGetIterableImplForConcreteClass() throws NoSuchFieldException, IllegalAccessException {
        TypeImpl result = TypeImpl.getIterableImpl(String.class);
        Field infClassField = result.getClass().getDeclaredField("infClass");
        Field implClassField = result.getClass().getDeclaredField("implClass");
        infClassField.setAccessible(true);
        implClassField.setAccessible(true);
        Assert.assertEquals(String.class, infClassField.get(result));
        Assert.assertEquals(String.class, implClassField.get(result));
    }

    @Test(expected = RuntimeException.class)
    public void testGetIterableImplForInterface() {
        TypeImpl.getIterableImpl(Map.class);
    }

    @Test(expected = RuntimeException.class)
    public void testGetIterableImplForAbstractClass() {
        TypeImpl.getIterableImpl(AbstractMap.class);
    }
}
