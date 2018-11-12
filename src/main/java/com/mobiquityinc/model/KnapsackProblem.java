package com.mobiquityinc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class is used to raise the backpack problem.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackProblem implements Serializable {

    private final BigDecimal weightLimit;
    private final List<KnapsackItem> items;

    public KnapsackProblem(BigDecimal weightLimit, List<KnapsackItem> items) {
        this.weightLimit = Optional.of(weightLimit).orElse(BigDecimal.ZERO);
        this.items = Optional.of(items).orElse(Collections.emptyList());
    }

    public BigDecimal getWeightLimit() {
        return weightLimit;
    }

    public List<KnapsackItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnapsackProblem that = (KnapsackProblem) o;
        return (weightLimit.compareTo(that.weightLimit) == 0) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weightLimit, items);
    }

    @Override
    public String toString() {
        return String.format("KnapsackProblem{weightLimit=%s, items=%s}", weightLimit, items);
    }
}
