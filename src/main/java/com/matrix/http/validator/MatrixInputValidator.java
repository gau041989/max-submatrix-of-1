package com.matrix.http.validator;

import com.matrix.http.model.Input;
import com.matrix.http.model.InvalidMatrixException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MatrixInputValidator {

    public Input validate(Input input) throws InvalidMatrixException {
        if(input.getMatrix().length == 0 || input.getMatrix().length != input.getColLength()) {
            throw new InvalidMatrixException();
        }

        if(input.getMatrix().length > 0) {
            if(Arrays.stream(input.getMatrix()).anyMatch(arr -> arr.length != input.getRowLength())) {
                throw new InvalidMatrixException();
            }
        }
        return input;
    }
}
