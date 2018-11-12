package com.mobiquityinc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * This class contains all the attributes of an item which you might choose.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackItem implements Serializable {

    private Integer index;
    private BigDecimal weight = BigDecimal.ZERO;
    private Integer cost;

    public KnapsackItem() {
    }

    public KnapsackItem(Integer index, BigDecimal weight, Integer cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = Optional.of(weight).orElse(BigDecimal.ZERO);
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnapsackItem that = (KnapsackItem) o;
        return Objects.equals(index, that.index) &&
                (weight.compareTo(that.weight) == 0) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, cost);
    }

    @Override
    public String toString() {
        return String.format("KnapsackItem{index=%d, weight=%s, cost=%d}", index, weight, cost);
    }
}
