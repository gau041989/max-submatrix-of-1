package com.matrix.core

import spock.lang.Specification
import spock.lang.Unroll

class MaximumHistogramTest extends Specification {

    @Unroll
    def "should compute histogram for usecase #index"(int index, int [] input, SubMatrix expected) {
        given:
        MaximumHistogram mh = new MaximumHistogram()

        when:
        def computedMatrix = mh.compute(0, input)

        then:
        computedMatrix == expected

        where:
        index   |   input           ||  expected
        1       |   [0, 0, 0, 0]    ||  new SubMatrix(0, 0, 0, 0, 0)
        2       |   []              ||  new SubMatrix(0, 0, 0, 0, 0)
        3       |   [0, 1, 1, 0]    ||  new SubMatrix(1, 2, 0, 1, 2)
        4       |   [1, 1, 1, 1]    ||  new SubMatrix(1, 4, 0, 0, 4)
        5       |   [0, 4, 4, 2]    ||  new SubMatrix(4, 2, 0, 1, 8)
        6       |   [2, 2, 0, 0]    ||  new SubMatrix(2, 2, 0, 0, 4)
    }
}
