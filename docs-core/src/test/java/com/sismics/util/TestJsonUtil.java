package com.sismics.util;

import com.sismics.util.mime.MimeType;
import com.sismics.util.mime.MimeTypeUtil;
import jakarta.json.JsonValue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of the JSON utilities (JsonUtil) and MimeTypeUtil.getFileExtension().
 * Tests nullable() methods for String, Integer, and Long overloads,
 * covering both null and non-null branches.
 * Also tests all switch-case branches in MimeTypeUtil.getFileExtension().
 */
public class TestJsonUtil {

    @Test
    public void nullableStringNullTest() {
        // Branch: null String should return JsonValue.NULL
        JsonValue result = JsonUtil.nullable((String) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }

    @Test
    public void nullableStringNonNullTest() {
        // Branch: non-null String should return a JsonString (not NULL)
        JsonValue result = JsonUtil.nullable("hello");
        Assert.assertNotNull(result);
        Assert.assertNotEquals(JsonValue.NULL, result);
        Assert.assertEquals(JsonValue.ValueType.STRING, result.getValueType());
    }

    @Test
    public void nullableIntegerNullTest() {
        // Branch: null Integer should return JsonValue.NULL
        JsonValue result = JsonUtil.nullable((Integer) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }

    @Test
    public void nullableIntegerNonNullTest() {
        // Branch: non-null Integer should return a JsonNumber (not NULL)
        JsonValue result = JsonUtil.nullable(42);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(JsonValue.NULL, result);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, result.getValueType());
    }

    @Test
    public void nullableLongNullTest() {
        // Branch: null Long should return JsonValue.NULL
        JsonValue result = JsonUtil.nullable((Long) null);
        Assert.assertEquals(JsonValue.NULL, result);
    }

    @Test
    public void nullableLongNonNullTest() {
        // Branch: non-null Long should return a JsonNumber (not NULL)
        JsonValue result = JsonUtil.nullable(123456789L);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(JsonValue.NULL, result);
        Assert.assertEquals(JsonValue.ValueType.NUMBER, result.getValueType());
    }

    @Test
    public void getFileExtensionTest() {
        // Tests all branches of MimeTypeUtil.getFileExtension() switch statement
        Assert.assertEquals("zip", MimeTypeUtil.getFileExtension(MimeType.APPLICATION_ZIP));
        Assert.assertEquals("gif", MimeTypeUtil.getFileExtension(MimeType.IMAGE_GIF));
        Assert.assertEquals("jpg", MimeTypeUtil.getFileExtension(MimeType.IMAGE_JPEG));
        Assert.assertEquals("png", MimeTypeUtil.getFileExtension(MimeType.IMAGE_PNG));
        Assert.assertEquals("pdf", MimeTypeUtil.getFileExtension(MimeType.APPLICATION_PDF));
        Assert.assertEquals("odt", MimeTypeUtil.getFileExtension(MimeType.OPEN_DOCUMENT_TEXT));
        Assert.assertEquals("docx", MimeTypeUtil.getFileExtension(MimeType.OFFICE_DOCUMENT));
        Assert.assertEquals("txt", MimeTypeUtil.getFileExtension(MimeType.TEXT_PLAIN));
        Assert.assertEquals("csv", MimeTypeUtil.getFileExtension(MimeType.TEXT_CSV));
        Assert.assertEquals("mp4", MimeTypeUtil.getFileExtension(MimeType.VIDEO_MP4));
        Assert.assertEquals("webm", MimeTypeUtil.getFileExtension(MimeType.VIDEO_WEBM));
        // Default branch: unknown MIME type
        Assert.assertEquals("bin", MimeTypeUtil.getFileExtension("application/unknown-type"));
    }
}
