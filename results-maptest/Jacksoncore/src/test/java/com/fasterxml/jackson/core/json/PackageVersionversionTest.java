package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public class PackageVersionversionTest {
    @Test
    public void testVersionMethod() {
        PackageVersion packageVersion = new PackageVersion();
        Version result = packageVersion.version();

        Assert.assertNotNull("version() should not return null", result);
        Assert.assertEquals("version() should return the correct version string", "2.10.0-SNAPSHOT", result.toString());
        Assert.assertEquals("version() should return the correct group ID", "com.fasterxml.jackson.core", result.getGroupId());
        Assert.assertEquals("version() should return the correct artifact ID", "jackson-core", result.getArtifactId());
    }
}
