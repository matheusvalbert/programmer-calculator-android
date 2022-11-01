package com.valbert.programmercalculator

import android.content.res.Resources
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

open class Calculator {
    var base = "hex"
    private var displayExpression = "0"
    private var displayResult = "0"
    private var expression = ""

    private var hexResult = "0"
    private var decResult = "0"
    private var octResult = "0"
    private var binResult = "0"

    fun executeControlOperation(value: String) {

    }

    fun concatOrExecuteOperation(operation: String): String {
        if(operation == "equal") displayExpression = getResult()
        else {
            displayExpression += when(operation) {
                "divide" -> "÷"
                "times" -> "×"
                "minus" -> "−"
                "plus" -> "+"
                else -> ""
            }
        }
        return displayExpression
    }

    fun concatValue(value: String): String {
        displayExpression += value
        expression += when(base) {
            "hex" -> "0x$value"
            "dec" -> "0d$value"
            "oct" -> "0o$value"
            "bin" -> "0b$value"
            else -> ""
        }
        return displayExpression
    }

    private fun getResult(): String {
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
//        return engine.eval(expression).toString()
        return "4"
    }
}