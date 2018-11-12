package com.mobiquityinc.util;

/**
 * This class provides a set of regex pattern related to Knapsack problem.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class KnapsackRegexUtils {

    private KnapsackRegexUtils() {
    }

    public static final String INTEGER_PATTERN = "\\d+?";
    public static final String DECIMAL_PATTERN = String.format("%s(\\.%s)?", INTEGER_PATTERN, INTEGER_PATTERN);
    public static final String OPTIONAL_SPACE_PATTERN = "\\s*?";

    public static final String ITEM_INDEX_PATTERN = String.format("%s%s%s", OPTIONAL_SPACE_PATTERN, INTEGER_PATTERN, OPTIONAL_SPACE_PATTERN);
    public static final String ITEM_WEIGHT_PATTERN = String.format("%s%s%s", OPTIONAL_SPACE_PATTERN, DECIMAL_PATTERN, OPTIONAL_SPACE_PATTERN);
    public static final String ITEM_COST_PATTERN = String.format("%s\\D%s%s", OPTIONAL_SPACE_PATTERN, INTEGER_PATTERN, OPTIONAL_SPACE_PATTERN);

    public static final String ITEM_PATTERN = String.format("\\((%s,%s,%s)\\)", ITEM_INDEX_PATTERN, ITEM_WEIGHT_PATTERN, ITEM_COST_PATTERN);
    public static final String WEIGHT_LIMIT_PATTERN = String.format("^%s%s%s:", OPTIONAL_SPACE_PATTERN, DECIMAL_PATTERN, OPTIONAL_SPACE_PATTERN);
    public static final String LINE_PATTERN = String.format("%s(%s%s)+?", WEIGHT_LIMIT_PATTERN, OPTIONAL_SPACE_PATTERN, ITEM_PATTERN);

}
