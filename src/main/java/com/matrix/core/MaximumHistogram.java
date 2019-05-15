package com.matrix.core;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class MaximumHistogram {

    public SubMatrix compute(int currentRow, int[] input) {
        Stack<Integer> results = new Stack<>();
        int resultPointer = 0;
        SubMatrix details = new SubMatrix(0,0,0,0,0);

        while(resultPointer < input.length) {
            if (resultIsEmptyOrTopIsLessThanCurrent(results, input, resultPointer)) {
                results.push(resultPointer++);
            } else {
                details = computeMaxArea(results, currentRow, input, resultPointer, details);
            }
        }

        while (!results.isEmpty()) {
            details = computeMaxArea(results, currentRow, input, resultPointer, details);
        }
        return details;
    }

    private SubMatrix computeMaxArea(Stack<Integer> results, int currentRow, int[] input, int i, SubMatrix details) {
        int top = results.pop();
        int histogramLength;
        int histogramArea;

        histogramLength = computeOrDefaultToIndexForEmptyResults(results, i);

        histogramArea = input[top] * histogramLength;

        if (histogramArea > details.getMaxArea()) {
            int x = computeOrDefaultToZeroForXBelowTheFloor((currentRow - (input[top] - 1)));
            return new SubMatrix(input[top], histogramLength, x, i - histogramLength, histogramArea);
        }
        return details;
    }

    private int computeOrDefaultToIndexForEmptyResults(Stack<Integer> results, int i) {
        return results.isEmpty() ? i : (i - results.peek() - 1);
    }

    private int computeOrDefaultToZeroForXBelowTheFloor(int i) {
        return i >= 0 ? i : 0;
    }

    private boolean resultIsEmptyOrTopIsLessThanCurrent(Stack<Integer> results, int [] input, int resultPointer) {
        return results.isEmpty() || input[results.peek()] <= input[resultPointer];
    }
}
