package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programExceptions.FileIsNotValidated;
import test.utils.HelperUtil;
import triangles.CreateTriangle;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class PositiveTest {
    private static final Logger LOG = LoggerFactory.getLogger(PositiveTest.class);
    private String fileName;

    public PositiveTest(String fileName) {
        this.fileName = fileName;
    }

    @Parameterized.Parameters
    public static Collection<String> params() {
        return Arrays.asList("1true.json", "2true.json", "3true.json");
    }

    @Test
    public void test() throws FileIsNotValidated {
        CreateTriangle triangle = new CreateTriangle();
        LOG.info("fileName: " + fileName);
        Assert.assertTrue(triangle.isTriangleGet(triangle.createTriangle(HelperUtil.getJSON(fileName))));
    }
}
