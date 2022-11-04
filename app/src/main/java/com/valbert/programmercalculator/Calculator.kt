package com.valbert.programmercalculator

import android.content.res.Resources
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.math.exp

open class Calculator {
    var base = "hex"
    private var displayExpression: String = "0"
    private var displayResult: String = "0"
    private var expression: String = ""

    private var openedParentheses: Int = 0
    private var closedParentheses: Int = 0

    private var hexResult: String = "0"
    private var decResult: String = "0"
    private var octResult: String = "0"
    private var binResult: String = "0"

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

    fun concatToEquation(value: String) {
        if (displayExpression == "0") {
            displayExpression = value
            return
        }

        if (displayExpression.last() == ')') {
            displayExpression += "×"
        }

        displayExpression += value
    }

    fun concatOperationToEquation(value: String) {
        val last: Char = displayExpression.last()

        if (displayExpression == "0" || last == '(') {
            return
        }

        if (last == '÷' || last == '×' || last == '-' || last == '+') {
            displayExpression = displayExpression.dropLast(1)
            displayExpression += value
            return
        }

        displayExpression += value
    }

    fun removeCharacter() {
        if (displayExpression == "0") {
            return
        }

        if (displayExpression.count() == 1) {
            displayExpression = "0"
            return
        }

        displayExpression = displayResult.dropLast(1)
    }

    fun resetCalculator() {
        displayExpression = "0"
//        mainResult = 0
        hexResult = "0"
        decResult = "0"
        octResult = "0"
        binResult = "0"
        openedParentheses = 0
        closedParentheses = 0
    }

    fun openParentheses() {
        val last: Char = displayExpression.last()

        if(last == '(') {
            displayExpression += "("
            openedParentheses += 1
            return
        }

        if(displayExpression == "0") {
            displayExpression = "("
            openedParentheses += 1
            return
        }

        if (last != '÷' && last != '×' && last != '-' && last != '+') {
            displayExpression += "×"
        }

        displayExpression += "("
        openedParentheses += 1
    }

    fun closeParentheses() {
        if (closedParentheses >= openedParentheses || displayExpression.last() == '(') {
            return
        }

        displayExpression += ")"
        closedParentheses += 1
    }

    fun fixParenthese() {

        var newOpenedParentheses = openedParentheses
        var newClosedParentheses = closedParentheses

        if (newOpenedParentheses == 0) { return }

        var countOpenedParentheses = 0

        for (i in 1..newOpenedParentheses) {
            if (expression.last() == '(') {
                expression = expression.dropLast(1)
                countOpenedParentheses += 1
            }
        }

        newOpenedParentheses -= countOpenedParentheses

        val addCloseParentheses: Int = newOpenedParentheses - newClosedParentheses

        if(addCloseParentheses == 0) { return }

        for(i in 1..addCloseParentheses) {
            expression += ")"
            newClosedParentheses += 1
        }
    }

    fun fixLastCharacter() {
        val last: Char = displayExpression.last()

        if (last == '÷' || last == '×' || last == '-' || last == '+') {
            expression = expression.dropLast(1)
        }
    }

    fun substituteOperationSymbol() {
        expression = expression.replace("÷", "/")
        expression = expression.replace("×", "*")
    }

    fun checkIfHasEquation() {
        if (expression.count() == 0) {
            expression = "0"
        }
    }

    fun separeEquation(): List<String> {

        val isNumberOrLetter: MutableList<Boolean> = ArrayList()

        for (char: Char in expression) {
            if (char.isDigit() || char.isLetter()) {
                isNumberOrLetter.add(true)
            }
            else {
                isNumberOrLetter.add(false)
            }
        }

        val separatedEquation: MutableList<String> = ArrayList()
        var newString = ""

        for (i in 0 until isNumberOrLetter.count()) {
            if (i != 0 && isNumberOrLetter[i-1] != isNumberOrLetter[i]) {
                separatedEquation.add(newString)
                newString = ""
            }

            newString += expression[i]
        }
        separatedEquation.add(newString)

        return separatedEquation

    }

    fun changeBase(newEquation: List<String>) {
        var finalEquation = ""

        newEquation.forEach {
            if (it[0].isDigit() || it[0].isLetter()) {
                when (base) {
                    "hex" -> finalEquation += Integer.toHexString(it.toInt())
                    "dec" -> finalEquation += it
                    "oct" -> finalEquation += Integer.toOctalString(it.toInt())
                    "bin" -> finalEquation += Integer.toBinaryString(it.toInt())
                    else -> finalEquation = "0"
                }
        }
            else {
                finalEquation += it
            }
        }

        expression = finalEquation
    }

    fun prepareToCalculate() {

        expression = displayExpression

        fixParenthese()
        fixLastCharacter()
        substituteOperationSymbol()
        checkIfHasEquation()

        val newEquation = separeEquation()
        changeBase(newEquation)

        calc()
        toHex()
        toDec()
        toOct()
        toBin()
    }

    fun calc() {
        displayResult = engine.eval(expression).toString()
    }

    fun result(): String {
        when(base) {
            "hex" -> return hexResult
            "dec" -> return decResult
            "oct" -> return octResult
            "bin" -> return binResult
            else -> return "0"
        }
    }

    fun toEquation() {
        when(base) {
            "hex" -> expression = hexResult
            "dec" -> expression = decResult
            "oct" -> expression = octResult
            "bin" -> expression = binResult
        }
    }

    fun toHex() {
        hexResult = Integer.toHexString(displayResult.toInt())
    }

    fun toDec() {
        decResult = displayResult.toString()
    }

    fun toOct() {
        octResult = Integer.toOctalString(displayResult.toInt())
    }

    fun toBin() {
        binResult = Integer.toBinaryString(displayResult.toInt())
    }

    fun shiftRight() {
        displayExpression = displayResult.dropLast(1)
    }

    fun shiftLeft() {
        displayExpression = displayResult + "0"
    }
}

//    fun executeControlOperation(value: String) {
//
//    }
//
//    fun concatOrExecuteOperation(operation: String): String {
//        if(operation == "equal") displayExpression = getResult()
//        else {
//            displayExpression += when(operation) {
//                "divide" -> "÷"
//                "times" -> "×"
//                "minus" -> "−"
//                "plus" -> "+"
//                else -> ""
//            }
//        }
//        return displayExpression
//    }
//
//    fun concatValue(value: String): String {
//        displayExpression += value
//        expression += when(base) {
//            "hex" -> "0x$value"
//            "dec" -> "0d$value"
//            "oct" -> "0o$value"
//            "bin" -> "0b$value"
//            else -> ""
//        }
//        return displayExpression
//    }

//    private fun getResult(): String {
//        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
//        return engine.eval(expression).toString()
//        return "4"
//    }
//}