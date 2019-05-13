package com.matrix.http;

import com.matrix.http.model.InvalidMatrixException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidMatrixHandlerAdvice {

    @ExceptionHandler(InvalidMatrixException.class)
    public ResponseEntity handleException(InvalidMatrixException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
