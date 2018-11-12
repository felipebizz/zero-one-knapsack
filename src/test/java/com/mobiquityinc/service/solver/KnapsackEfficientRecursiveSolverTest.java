package com.mobiquityinc.service.solver;

import com.mobiquityinc.model.KnapsackItem;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackEfficientRecursiveSolverTest {

    private KnapsackEfficientRecursiveSolver solver = new KnapsackEfficientRecursiveSolver();

    @Test
    public void testHappyPath() {
        final KnapsackItem firstItem = new KnapsackItem(1, BigDecimal.valueOf(53.38), 45);
        final KnapsackItem secondItem = new KnapsackItem(2, BigDecimal.valueOf(88.62), 98);
        final KnapsackProblem problem = new KnapsackProblem(BigDecimal.valueOf(81), Arrays.asList(firstItem, secondItem));

        final KnapsackResult result = solver.solve(problem);
        final List<KnapsackItem> resultItems = result.getItems();

        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(firstItem, resultItems.get(0));
    }

    @Test
    public void testLessWeight() {
        final KnapsackItem firstItem = new KnapsackItem(1, BigDecimal.valueOf(53.38), 45);
        final KnapsackItem secondItem = new KnapsackItem(2, BigDecimal.valueOf(38.62), 45);
        final KnapsackProblem problem = new KnapsackProblem(BigDecimal.valueOf(81), Arrays.asList(firstItem, secondItem));

        final KnapsackResult result = solver.solve(problem);
        final List<KnapsackItem> resultItems = result.getItems();

        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(secondItem, resultItems.get(0));
    }

    @Test
    public void testMoreCost() {
        final KnapsackItem firstItem = new KnapsackItem(1, BigDecimal.valueOf(53.38), 45);
        final KnapsackItem secondItem = new KnapsackItem(2, BigDecimal.valueOf(53.38), 46);
        final KnapsackProblem problem = new KnapsackProblem(BigDecimal.valueOf(81), Arrays.asList(firstItem, secondItem));

        final KnapsackResult result = solver.solve(problem);
        final List<KnapsackItem> resultItems = result.getItems();

        Assert.assertEquals(1, resultItems.size());
        Assert.assertEquals(secondItem, resultItems.get(0));
    }

    @Test
    public void testNotMatched() {
        final KnapsackItem firstItem = new KnapsackItem(1, BigDecimal.valueOf(53.38), 45);
        final KnapsackItem secondItem = new KnapsackItem(2, BigDecimal.valueOf(88.62), 98);
        final KnapsackProblem problem = new KnapsackProblem(BigDecimal.valueOf(11), Arrays.asList(firstItem, secondItem));

        final KnapsackResult result = solver.solve(problem);
        final List<KnapsackItem> resultItems = result.getItems();

        Assert.assertEquals(0, resultItems.size());
    }
}