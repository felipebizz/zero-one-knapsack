package com.mobiquityinc.service.solver;

import com.mobiquityinc.model.KnapsackItem;
import com.mobiquityinc.model.KnapsackProblem;
import com.mobiquityinc.model.KnapsackResult;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * This class is an efficient implementation of {@link KnapsackSolver KnapsackSolver}
 * which works according to dynamic programing algorithm recursively.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackEfficientRecursiveSolver implements KnapsackSolver {

    @Override
    public KnapsackResult solve(KnapsackProblem problem) {
        final BigDecimal weightLimit = problem.getWeightLimit();
        final List<KnapsackItem> allItems = problem.getItems();
        final Integer itemsSize = allItems.size();
        if ((weightLimit.compareTo(ZERO) <= 0) || (itemsSize <= 0)) {
            return KnapsackResult.empty();
        }

        final KnapsackItem currentItem = allItems.get(itemsSize - 1);
        final List<KnapsackItem> previousItems = allItems.subList(0, itemsSize - 1);

        final KnapsackResult previousResult = solve(new KnapsackProblem(weightLimit, previousItems));
        final KnapsackResult remainedResult = solve(new KnapsackProblem(weightLimit.subtract(currentItem.getWeight()), previousItems));
        final KnapsackResult currentResult = remainedResult.clone().addItem(currentItem);

        return max(previousResult, currentResult, weightLimit);
    }

    private KnapsackResult max(KnapsackResult first, KnapsackResult second, BigDecimal weightLimit) {
        if (second.getWeight().compareTo(weightLimit) > 0) {
            return first.getWeight().compareTo(weightLimit) > 0 ? KnapsackResult.empty() : first;
        }
        return (first.getWeight().compareTo(weightLimit) > 0) || (first.compareTo(second) < 0) ? second : first;
    }
}
