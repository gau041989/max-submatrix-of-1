package com.matrix.core

import spock.lang.Specification
import spock.lang.Unroll

class SubMatrixCalculatorTest extends Specification {

    @Unroll
    def "should work for use case #index"(int index, int [][] input, SubMatrix expected) {
        given:
        MaximumHistogram mh = new MaximumHistogram()
        SubMatrixCalculator mrs = new SubMatrixCalculator(mh)

        when:
        SubMatrix actual =  mrs.maximum(input)

        then:
        actual == expected

        where:
        index | input               || expected
            1 | [
                    [1,1,1,0],
                    [1,1,1,1],
                    [0,1,1,0],
                    [0,1,1,1],
                    [1,0,0,1],
                    [1,1,1,1]
                ]                  || new SubMatrix(4,2,0,1,8)


            2 | [
                    [1,1,1,0],
                    [1,1,1,1],
                ]                   || new SubMatrix(2, 3,0,0,6)

            3 | [
                    [0,1,1,0],
                    [1,1,1,1],
                    [1,1,1,1],
                    [1,1,0,0]
                ]                   || new SubMatrix(2, 4, 1, 0, 8)

            4 | [
                    [1,0,0,0,0,1],
                    [0,1,1,1,0,0],
                    [0,1,1,1,0,0],
                    [0,0,0,1,1,0]
                ]                   || new SubMatrix(2, 3, 1, 1, 6)

    }
}
