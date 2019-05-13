package com.matrix.http.validator;

import com.matrix.http.model.Input;
import com.matrix.http.model.InvalidMatrixException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MatrixInputValidator {

    private static Predicate<Input> isMatrixNonEmpty = (input -> input.getMatrix().length == 0);

    private static Predicate<Input> doesMatrixDimensionMatchColumnLength = (input -> input.getMatrix().length != input.getColLength());

    private static Predicate<Input> doesMatrixHaveEqualRowLength = (input ->
        (input.getMatrix().length > 0)
                &&
        (Arrays.stream(input.getMatrix()).anyMatch(arr -> arr.length != input.getRowLength()))
    );

    private static List<Predicate<Input>> ALL_FILTERS = Arrays.asList(
            isMatrixNonEmpty, doesMatrixDimensionMatchColumnLength, doesMatrixHaveEqualRowLength);

    public Input validate(Input input) throws InvalidMatrixException {

        if(ALL_FILTERS.stream().anyMatch(predicate -> predicate.test(input))) {
            throw new InvalidMatrixException();
        }

        return input;
    }
}
