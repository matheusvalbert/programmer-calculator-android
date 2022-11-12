package com.valbert.programmercalculator

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

open class Calculator {
    var base = "hex"
    var displayExpression: String = "0"
    private var displayResult: String = "0"
    private var expression: String = ""

    private var openedParentheses: Int = 0
    private var closedParentheses: Int = 0

    var hexResult: String = "0"
    var decResult: String = "0"
    var octResult: String = "0"
    var binResult: String = "0"

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

    fun concatValue(value: String) {
        if (displayExpression == "0") {
            displayExpression = value
            return
        }

        if(displayExpression.last() == ')')
            displayExpression += '×'

        displayExpression += value
        prepareToCalculate()
    }

    fun concatOperation(value: String) {
        val last: Char = displayExpression.last()

        if(displayExpression == "0")
            return

        println(last)
        if(last == '÷' || last == '×' || last == '-' || last == '+')
            displayExpression = displayExpression.dropLast(1)

        displayExpression += value
    }

    fun removeLast() {
        when(displayExpression.last()) {
            '(' -> openedParentheses--
            ')' -> closedParentheses--
        }

        if(displayExpression == "0")
            return

        if(displayExpression.count() == 1) {
            displayExpression = "0"
            return
        }

        displayExpression = displayExpression.dropLast(1)
    }

    fun reset() {
        displayExpression = "0"
        displayResult = "0"
        expression = ""
        openedParentheses = 0
        closedParentheses = 0
        hexResult = "0"
        decResult = "0"
        octResult = "0"
        binResult = "0"
    }

    fun openParentheses() {
        val last: Char = displayExpression.last()

        if(last == '(') {
            displayExpression += "("
            openedParentheses++
            return
        }

        if(displayExpression == "0") {
            displayExpression = "("
            openedParentheses++
            return
        }

        if(last != '÷' && last != '×' && last != '-' && last != '+') {
            displayExpression += "×"
        }

        displayExpression += "("
        openedParentheses++
    }

    fun closeParentheses() {
        val last: Char = displayExpression.last()

        if(closedParentheses >= openedParentheses || last == '(' || last == '÷' || last == '×' || last == '-' || last == '+') {
            return
        }

        displayExpression += ")"
        closedParentheses++
    }

    private fun fixParenthese() {

        var newOpenedParentheses = openedParentheses
        var newClosedParentheses = closedParentheses

        if(newOpenedParentheses == 0)
            return

        var countOpenedParentheses = 0

        for(i in 1..newOpenedParentheses) {
            if(expression.last() == '(') {
                expression = expression.dropLast(1)
                countOpenedParentheses += 1
            }
        }

        newOpenedParentheses -= countOpenedParentheses
        val addCloseParentheses = newOpenedParentheses - newClosedParentheses

        if(addCloseParentheses == 0)
            return

        for( i in 1..addCloseParentheses) {
            expression += ")"
            newClosedParentheses += 1
        }
    }

    private fun fixLastCharacter() {
        val last: Char = displayExpression.last()

        if(last == '÷' || last == '×' || last == '-' || last == '+')
            expression = expression.dropLast(1)
    }

    private fun substituteOperationSymbol() {
        expression = expression.replace("÷", "/")
        expression = expression.replace("×", "*")
    }

    private fun checkIfHasEquation() {
        if(expression.isEmpty())
            expression = "0"
    }

    private fun separeEquation(): List<String> {
        val isNumberOrLetter: MutableList<Boolean> = arrayListOf()

        for(char: Char in expression) {
            if(char.isDigit() || char.isLetter())
                isNumberOrLetter.add(true)
            else
                isNumberOrLetter.add(false)
        }

        val separatedEquation: MutableList<String> = arrayListOf()
        var newString = ""

        for(i: Int in 0 until isNumberOrLetter.count()) {
            if(i != 0 && isNumberOrLetter[i-1] != isNumberOrLetter[i]) {
                separatedEquation.add(newString)
                newString = ""
            }

            newString += expression[i]
        }
        separatedEquation.add(newString)

        return separatedEquation

    }

    private fun changeBase(newEquation: List<String>) {
        var finalEquation = ""

        newEquation.forEach {
            if(it[0].isDigit() || it[0].isLetter())
                finalEquation += when(base) {
                    "hex" -> it.toBigInteger(16)
                    "dec" -> it
                    "oct" -> it.toBigInteger(8)
                    "bin" -> it.toBigInteger(2)
                    else -> "0"
                }
            else
                finalEquation += it
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
    }

    private fun calc() {
        decResult = engine.eval(expression).toString().split(".")[0]
        hexResult = Integer.toHexString(decResult.toInt())
        octResult = Integer.toOctalString(decResult.toInt())
        binResult = Integer.toBinaryString(decResult.toInt())
    }

    fun calculate() {
        displayExpression = when(base) {
            "hex" -> hexResult
            "dec" -> decResult
            "oct" -> octResult
            "bin" -> binResult
            else -> "0"
        }

        prepareToCalculate()
    }

    fun shiftShowResult() {
        binResult = displayExpression
        decResult = displayExpression.toLong(2).toString()
        hexResult = Integer.toHexString(decResult.toInt())
        octResult = Integer.toOctalString(decResult.toInt())
    }

    fun shiftRight() {
        calculate()
        displayExpression = displayExpression.dropLast(1)
        shiftShowResult()
    }

    fun shiftLeft() {
        calculate()
        displayExpression += "0"
        shiftShowResult()
    }
}