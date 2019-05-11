package com.matrix.http;

import com.matrix.core.SubMatrix;
import com.matrix.core.SubMatrixCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@EnableAutoConfiguration
@ComponentScan("com.matrix.core")
public class Application {

    private final SubMatrixCalculator calculator;

    @Autowired
    public Application(SubMatrixCalculator calculator) {
        this.calculator = calculator;
    }

    @RequestMapping(value = "/get-submatrix-of-1", method = POST)
    public @ResponseBody ResponseEntity<SubMatrix> getSubMatrixOf1s(@RequestBody int [][] matrix) {
        return ResponseEntity.ok(calculator.maximum(matrix));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
