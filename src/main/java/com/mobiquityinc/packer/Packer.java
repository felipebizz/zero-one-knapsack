package com.mobiquityinc.packer;

import com.mobiquityinc.model.KnapsackResult;
import com.mobiquityinc.service.reader.KnapsackProblemReader;
import com.mobiquityinc.service.reader.KnapsackSimpleFileReader;
import com.mobiquityinc.service.solver.KnapsackEfficientRecursiveSolver;
import com.mobiquityinc.service.solver.KnapsackSolver;

/**
 * This class is the entry point of the solution, and will return all the results as a string.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class Packer {

    public static String pack(String path) {
        final KnapsackProblemReader reader = new KnapsackSimpleFileReader();
        final KnapsackSolver solver = new KnapsackEfficientRecursiveSolver();
        return reader.read(path)
                .map(solver::solve)
                .map(KnapsackResult::toString)
                .reduce((first, second) -> String.format("%s\r\n%s", first, second))
                .orElse("-");
    }
}
