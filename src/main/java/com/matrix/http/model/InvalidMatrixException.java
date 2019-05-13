package com.matrix.http.model;

public class InvalidMatrixException extends Exception {

    public InvalidMatrixException() {
        super("Matrix of invalid data provided");
    }
}
