package com.example.calculator.util

import org.junit.Assert.assertEquals
import org.junit.Test

class OperatorTest {

    @Test
    fun operatorPlusTest() {
        assertEquals(Operator.calculate(Operator.PLUS, 1.0, 2.0), 3.0, 0.0)
    }

    @Test
    fun operatorMinTest() {
        assertEquals(Operator.calculate(Operator.MIN, 2.0, 1.0), 1.0, 0.0)
    }

    @Test
    fun operatorMulTest() {
        assertEquals(Operator.calculate(Operator.MUL, 1.0, 2.0), 2.0, 0.0)
    }

    @Test
    fun operatorDivTest() {
        assertEquals(Operator.calculate(Operator.DIV, 1.0, 2.0), 0.5, 0.0)
    }

    @Test
    fun operatorDivElseTest() {
        assertEquals(Operator.calculate(Operator.DIV, 0.0, 2.0), 0.0, 0.0)
    }
}