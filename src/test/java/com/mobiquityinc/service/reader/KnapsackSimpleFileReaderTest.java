package com.mobiquityinc.service.reader;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.KnapsackProblem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(KnapsackSimpleFileReader.class)
@SuppressWarnings("ResultOfMethodCallIgnored")
public class KnapsackSimpleFileReaderTest {

    private KnapsackSimpleFileReader reader = new KnapsackSimpleFileReader();

    @Before
    public void setup() {
        PowerMockito.mockStatic(Files.class);
    }

    @Test
    public void testHappyPath() throws IOException {
        final String line = "58 : (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        final List<KnapsackProblem> problems = reader.read("anyPath").collect(Collectors.toList());

        Assert.assertEquals(1, problems.size());
        final KnapsackProblem problem = problems.get(0);

        Assert.assertEquals(BigDecimal.valueOf(58), problem.getWeightLimit());
        Assert.assertEquals(2, problem.getItems().size());
    }

    @Test
    public void testSkipIncorrectItem() throws IOException {
        final String line = "58 : (1,53.38,€45) (2,88.62,)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        final List<KnapsackProblem> problems = reader.read("anyPath").collect(Collectors.toList());

        Assert.assertEquals(1, problems.size());
        final KnapsackProblem problem = problems.get(0);

        Assert.assertEquals(BigDecimal.valueOf(58), problem.getWeightLimit());
        Assert.assertEquals(1, problem.getItems().size());
    }

    @Test(expected = APIException.class)
    public void testInvalidLineFormat() throws IOException {
        final String line = "58 :: (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        reader.read("anyPath").count();
    }

    @Test(expected = APIException.class)
    public void testLineWithoutWeightLimit() throws IOException {
        final String line = ": (1,53.38,€45) (2,88.62,€98)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        reader.read("anyPath").count();
    }

    @Test(expected = APIException.class)
    public void testLineWithoutItem() throws IOException {
        final String line = "58: ";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        reader.read("anyPath").count();
    }

    @Test(expected = APIException.class)
    public void testInvalidItemFormat() throws IOException {
        final String line = "58 : (1,53.38)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        reader.read("anyPath").count();
    }
}