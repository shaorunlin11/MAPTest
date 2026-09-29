package com.zappos.json;

import org.junit.Test;
import org.junit.Assert;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import com.zappos.json.util.JsonUtils;
import com.zappos.json.ZapposJson;

public class JsonWriterwriteIterableTest {

    @Test
    public void testWriteIterableWithEmptyIterable() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();
        Iterable<?> iterable = new ArrayList<>();
        JsonWriter.writeIterable(zapposJson, iterable, writer);
        Assert.assertEquals("[]", writer.toString());
    }

    @Test
    public void testWriteIterableWithSingleElement() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();
        List<String> list = new ArrayList<>();
        list.add("test");
        JsonWriter.writeIterable(zapposJson, list, writer);
        Assert.assertEquals("[\"test\"]", writer.toString());
    }

    @Test
    public void testWriteIterableWithMultipleElements() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        JsonWriter.writeIterable(zapposJson, list, writer);
        Assert.assertEquals("[\"test1\",\"test2\",\"test3\"]", writer.toString());
    }

    @Test
    public void testWriteIterableWithNullElement() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();
        List<String> list = new ArrayList<>();
        list.add(null);
        JsonWriter.writeIterable(zapposJson, list, writer);
        Assert.assertEquals("[null]", writer.toString());
    }

    @Test
    public void testWriteIterableWithMixedElements() throws Exception {
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();
        List<Object> list = new ArrayList<>();
        list.add("string");
        list.add(123);
        list.add(true);
        list.add(null);
        JsonWriter.writeIterable(zapposJson, list, writer);
        Assert.assertEquals("[\"string\",123,true,null]", writer.toString());
    }
}
