package com.mobiquityinc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This data structure is used to hold the result of Knapsack problem.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackResult implements Serializable, Cloneable, Comparable<KnapsackResult> {

    private static final KnapsackResult EMPTY = new KnapsackResult();

    private BigDecimal weight = BigDecimal.ZERO;
    private Integer cost = 0;
    private List<KnapsackItem> items = new ArrayList<>();

    private KnapsackResult() {
    }

    public static KnapsackResult empty() {
        return EMPTY;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Integer getCost() {
        return cost;
    }

    public List<KnapsackItem> getItems() {
        return items;
    }

    public KnapsackResult addItem(KnapsackItem item) {
        this.weight = this.weight.add(item.getWeight());
        this.cost += item.getCost();
        this.items.add(item);
        return this;
    }

    @Override
    public KnapsackResult clone() {
        final KnapsackResult clone = new KnapsackResult();
        clone.weight = this.weight;
        clone.cost = this.cost;
        clone.items = new ArrayList<>(this.items);
        return clone;
    }

    @Override
    public int compareTo(KnapsackResult other) {
        if (other == null) {
            return 1;
        }
        if (this.cost.compareTo(other.cost) == 0) {
            return other.weight.compareTo(this.weight);
        }
        return this.cost.compareTo(other.cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnapsackResult that = (KnapsackResult) o;
        return (weight.compareTo(that.weight) == 0) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, cost, items);
    }

    @Override
    public String toString() {
        return items.stream()
                .map(KnapsackItem::getIndex).map(Object::toString)
                .reduce((first, second) -> String.format("%s, %s", first, second)).orElse("-");
    }
}
