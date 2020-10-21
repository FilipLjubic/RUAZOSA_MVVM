package com.fljubic.ruazosa_mvvm_dz

import org.junit.Assert
import org.junit.Test

/**
 * Created by dejannovak on 25/03/2018.
 */
class CalculatorUnitTest {
    @Test
    fun test_reset() {
        Calculator.reset()
        Assert.assertEquals(0.0, Calculator.result, 0.0)
        Assert.assertEquals(0, Calculator.expression.count())
    }

    @Test
    fun test_addNumber() {
        Calculator.reset()
        try {
            Calculator.addNumber("Not a number")
            Assert.fail()
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not valid number")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of numbers in expression")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }

    }

    @Test
    fun test_addOperator() {
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("*")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid operator")
        }

        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addOperator("-")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of operator in expression")
        }
    }

    @Test
    fun test_evaluate() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("200")
        Calculator.addOperator("-")
        Calculator.addNumber("300")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 0.00, 0.00)
    }

    // 13*2*5 = 130
    @Test
    fun test_multiply() {
        Calculator.reset()
        Calculator.addNumber("13")
        Calculator.addOperator("*")
        Calculator.addNumber("2")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 130.00, 0.00)
    }

    // 13/2*5 = 32.5
    @Test
    fun test_multiply_divide() {
        Calculator.reset()
        Calculator.addNumber("13")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 32.50, 0.00)
    }

    // 13/2*5 + 50= = 82.5
    @Test
    fun test_multiply_then_add() {
        Calculator.reset()
        Calculator.addNumber("13")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.addOperator("+")
        Calculator.addNumber("50")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 82.50, 0.00)
    }

    // 100 + 13*2*5 - 3 + 25*5 = 352
    @Test
    fun test_multiply_complex() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("13")
        Calculator.addOperator("*")
        Calculator.addNumber("2")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.addOperator("-")
        Calculator.addNumber("3")
        Calculator.addOperator("+")
        Calculator.addNumber("25")
        Calculator.addOperator("*")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 352.00, 0.00)
    }

    // 100 + 13*2/5 - 3 + 25/5 = 107.2
    @Test
    fun test_combined_operators() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("13")
        Calculator.addOperator("*")
        Calculator.addNumber("2")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.addOperator("-")
        Calculator.addNumber("3")
        Calculator.addOperator("+")
        Calculator.addNumber("25")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 107.20, 0.00)
    }

    // 100 + 13/2/5 - 3 + 25/5 = 103.3
    @Test
    fun test_divide_complex() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("13")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.addOperator("-")
        Calculator.addNumber("3")
        Calculator.addOperator("+")
        Calculator.addNumber("25")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 103.30, 0.00)
    }


}