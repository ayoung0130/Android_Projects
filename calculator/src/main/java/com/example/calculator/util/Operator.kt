package com.example.calculator.util

enum class Operator {
    PLUS, MIN, MUL, DIV;

    companion object {
        fun calculate(type: Operator, num1: Double, num2: Double): Double {
            return when (type) {
                PLUS -> num1 + num2
                MIN -> num1 - num2
                MUL -> num1 * num2
                DIV -> if (num1 != 0.0 && num2 != 0.0) {
                    (num1 / num2)
                } else {
                    0.0
                }
            }
        }
    }
}