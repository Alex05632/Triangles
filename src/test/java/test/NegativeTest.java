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
public class NegativeTest {
    private static final Logger LOG = LoggerFactory.getLogger(NegativeTest.class);
    private String fileName;

    public NegativeTest(String fileName) {
        this.fileName = fileName;
    }


    @Parameterized.Parameters
    public static Collection<String> params() {
        return Arrays.asList("11false.json", "1false.json", "2false.json");
    }

    @Test(expected = FileIsNotValidated.class)
    public void test() throws FileIsNotValidated {
        CreateTriangle triangle = new CreateTriangle();
        LOG.info("fileName: " + fileName);
        Assert.assertTrue(triangle.isTriangleGet(triangle.createTriangle(HelperUtil.getJSON(fileName))));
    }
}
