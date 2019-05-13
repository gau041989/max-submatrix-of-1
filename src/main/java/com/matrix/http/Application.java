package com.matrix.http;

import com.matrix.core.SubMatrix;
import com.matrix.core.SubMatrixCalculator;
import com.matrix.http.model.Input;
import com.matrix.http.model.InvalidMatrixException;
import com.matrix.http.validator.MatrixInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@EnableAutoConfiguration
@ComponentScan({"com.matrix.*"})
public class Application {

    private final SubMatrixCalculator calculator;
    private final MatrixInputValidator validator;

    @Autowired
    public Application(SubMatrixCalculator calculator, MatrixInputValidator validator) {
        this.calculator = calculator;
        this.validator = validator;
    }

    @RequestMapping(value = "/get-submatrix-of-1", method = POST)
    public @ResponseBody ResponseEntity<SubMatrix> getSubMatrixOf1s(@RequestBody Input input) throws InvalidMatrixException {
        Input validated = validator.validate(input);
        return ResponseEntity.ok(calculator.maximum(validated.getMatrix()));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
