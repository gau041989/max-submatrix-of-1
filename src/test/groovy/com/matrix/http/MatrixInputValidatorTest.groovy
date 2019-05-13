package com.matrix.http

import com.matrix.http.model.Input
import com.matrix.http.model.InvalidMatrixException
import com.matrix.http.validator.MatrixInputValidator
import spock.lang.Specification

class MatrixInputValidatorTest extends Specification {

    def unit = new MatrixInputValidator()


    def "should thrown InvalidMatrixException for 1d matrix"() {
        given:
        Input input = new Input([] as int[][], 0, 0)

        when:
        unit.validate(input)

        then:
        InvalidMatrixException exception = thrown()

    }

    def "should throw InvalidMatrixException for 2d matrix with uneven size"() {
        given:
        Input input = new Input([
                [0,1,0],
                [1,1,1,1]
        ] as int[][], 3, 3)

        when:
        unit.validate(input)

        then:
        InvalidMatrixException exception = thrown()
    }

    def "should pass for valid input"() {
        given:
        Input input = new Input([
                [0,0,0,0],
                [1,1,1,1]
        ] as int[][], 4, 2)

        when:
        def validated = unit.validate(input)

        then:
        validated == input
    }
}
