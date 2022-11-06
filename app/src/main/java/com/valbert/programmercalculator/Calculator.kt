package com.valbert.programmercalculator

import android.content.res.Resources
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.math.exp

open class Calculator {
    var base = "hex"
    var displayExpression: String = "0"
    private var displayResult: String = "0"
    private var expression: String = ""

    private var openedParentheses: Int = 0
    private var closedParentheses: Int = 0

    private var hexResult: String = "0"
    private var decResult: String = "0"
    private var octResult: String = "0"
    private var binResult: String = "0"

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

    fun concatValue(value: String) {
        when(displayExpression) {
            "0" -> displayExpression = value
            ")" -> displayExpression += "×$value"
            else -> displayExpression += value
        }
    }

    fun concatOperation(value: String) {
        val last: Char = displayExpression.last()

        if (displayExpression == "0" || last == '(')
            return

        if (last == '÷' || last == '×' || last == '-' || last == '+')
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

        if(closedParentheses >= openedParentheses || displayExpression.last() == '(') {
            return
        }

        displayExpression += ")"
        closedParentheses++
    }
}