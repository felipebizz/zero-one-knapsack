package com.mobiquityinc.packer;

import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;
import com.mobiquityinc.service.reader.KnapsackSimpleFileReader;
import com.mobiquityinc.service.solver.KnapsackEfficientRecursiveSolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Packer.class)
public class PackerTest {

    private KnapsackSimpleFileReader reader;
    private KnapsackEfficientRecursiveSolver solver;

    @Before
    public void setup() throws Exception {
        reader = Mockito.mock(KnapsackSimpleFileReader.class);
        PowerMockito.whenNew(KnapsackSimpleFileReader.class).withNoArguments().thenReturn(reader);

        solver = Mockito.mock(KnapsackEfficientRecursiveSolver.class);
        PowerMockito.whenNew(KnapsackEfficientRecursiveSolver.class).withNoArguments().thenReturn(solver);
    }

    @Test
    public void testConcatenation() {
        final KnapsackProblem anyProblem = new KnapsackProblem(BigDecimal.ZERO, Collections.emptyList());
        PowerMockito.doReturn(Stream.of(anyProblem, anyProblem)).when(reader).read(any());
        PowerMockito.doReturn(KnapsackResult.empty()).when(solver).solve(any());

        final String actual = Packer.pack("anyPath");

        Assert.assertEquals("-\r\n-", actual);
    }

    @Test
    public void testNothingToRead() {
        PowerMockito.doReturn(Stream.of()).when(reader).read(any());

        final String actual = Packer.pack("anyPath");

        Assert.assertEquals("-", actual);
    }
}
