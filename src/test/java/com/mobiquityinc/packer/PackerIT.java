package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

public class PackerIT {

    @Test
    public void testHappyPath() {
        final String actual = Packer.pack("samples/mobiquity-sample.txt");

        Assert.assertEquals("4\r\n-\r\n2, 7\r\n8, 9", actual);
    }

    @Test
    public void testEmptyLines() {
        final String actual = Packer.pack("samples/empty-lines.txt");

        Assert.assertEquals("4\r\n-\r\n2, 7\r\n8, 9", actual);
    }

    @Test
    public void testEmptyFile() {
        final String actual = Packer.pack("samples/empty-file.txt");

        Assert.assertEquals("-", actual);
    }

    @Test(expected = APIException.class)
    public void testWrongPath() {
        Packer.pack("wrongPath");
    }
}
