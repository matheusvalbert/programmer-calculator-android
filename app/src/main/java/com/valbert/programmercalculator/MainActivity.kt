package com.valbert.programmercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private val calculator: Calculator = Calculator()

    private lateinit var buttonHex: Button
    private lateinit var buttonDec: Button
    private lateinit var buttonOct: Button
    private lateinit var buttonBin: Button
    private lateinit var buttonShl: Button
    private lateinit var buttonShr: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button
    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var buttonD: Button
    private lateinit var buttonE: Button
    private lateinit var buttonF: Button
    private lateinit var expression: EditText
    private lateinit var hexResult: TextView
    private lateinit var decResult: TextView
    private lateinit var octResult: TextView
    private lateinit var binResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
        enableDisableButtons()
        expression.showSoftInputOnFocus = false
//        expression.setSelection(expression.length())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun setUp() {
        buttonHex = findViewById(R.id.hexButton)
        buttonDec = findViewById(R.id.decButton)
        buttonOct = findViewById(R.id.octButton)
        buttonBin = findViewById(R.id.binButton)
        buttonShl = findViewById(R.id.shiftLeft)
        buttonShr = findViewById(R.id.shiftRight)
        buttonTwo = findViewById(R.id.two)
        buttonThree = findViewById(R.id.three)
        buttonFour = findViewById(R.id.four)
        buttonFive = findViewById(R.id.five)
        buttonSix = findViewById(R.id.six)
        buttonSeven = findViewById(R.id.seven)
        buttonEight = findViewById(R.id.eight)
        buttonNine = findViewById(R.id.nine)
        buttonA = findViewById(R.id.a)
        buttonB = findViewById(R.id.b)
        buttonC = findViewById(R.id.c)
        buttonD = findViewById(R.id.d)
        buttonE = findViewById(R.id.e)
        buttonF = findViewById(R.id.f)
        expression = findViewById(R.id.primaryResult)
        hexResult = findViewById(R.id.hexResult)
        decResult = findViewById(R.id.decResult)
        octResult = findViewById(R.id.octResult)
        binResult = findViewById(R.id.binResult)
    }

    private fun enableDisableButtons() {
        when(calculator.base) {
            "hex" -> {
                buttonTwo.isEnabled = true
                buttonThree.isEnabled = true
                buttonFour.isEnabled = true
                buttonFive.isEnabled = true
                buttonSix.isEnabled = true
                buttonSeven.isEnabled = true
                buttonEight.isEnabled = true
                buttonNine.isEnabled = true
                buttonA.isEnabled = true
                buttonB.isEnabled = true
                buttonC.isEnabled = true
                buttonD.isEnabled = true
                buttonE.isEnabled = true
                buttonF.isEnabled = true
                buttonShl.isEnabled = false
                buttonShr.isEnabled = false
            }
            "dec" -> {
                buttonTwo.isEnabled = true
                buttonThree.isEnabled = true
                buttonFour.isEnabled = true
                buttonFive.isEnabled = true
                buttonSix.isEnabled = true
                buttonSeven.isEnabled = true
                buttonEight.isEnabled = true
                buttonNine.isEnabled = true
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
                buttonShl.isEnabled = false
                buttonShr.isEnabled = false
            }
            "oct" -> {
                buttonTwo.isEnabled = true
                buttonThree.isEnabled = true
                buttonFour.isEnabled = true
                buttonFive.isEnabled = true
                buttonSix.isEnabled = true
                buttonSeven.isEnabled = true
                buttonEight.isEnabled = false
                buttonNine.isEnabled = false
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
                buttonShl.isEnabled = false
                buttonShr.isEnabled = false
            }
            "bin" -> {
                buttonTwo.isEnabled = false
                buttonThree.isEnabled = false
                buttonFour.isEnabled = false
                buttonFive.isEnabled = false
                buttonSix.isEnabled = false
                buttonSeven.isEnabled = false
                buttonEight.isEnabled = false
                buttonNine.isEnabled = false
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
                buttonShl.isEnabled = true
                buttonShr.isEnabled = true
            }
        }
    }

    private fun setResult() {
        hexResult.text = calculator.hexResult
        decResult.text = calculator.decResult
        octResult.text = calculator.octResult
        binResult.text = calculator.binResult
    }

    private fun inactivateBase() {
        buttonHex.setBackgroundResource(0)
        buttonDec.setBackgroundResource(0)
        buttonOct.setBackgroundResource(0)
        buttonBin.setBackgroundResource(0)
    }

    private fun activateBase(view: View) {
        calculator.base = view.tag.toString()
        view.setBackgroundResource(R.drawable.base_green)
    }

    private fun changeResult() {
        expression.setText(when(calculator.base) {
            "hex" -> calculator.hexResult
            "dec" -> calculator.decResult
            "oct" -> calculator.octResult
            "bin" -> calculator.binResult
            else -> "0"
        })
    }

    fun changeBase(view: View) {
        inactivateBase()
        activateBase(view)
        changeResult()
        calculator.calculate()
        enableDisableButtons()
    }

    fun controlButtonClick(view: View) {
        when(view.tag.toString()) {
            "reset" -> calculator.reset()
            "equal" -> {
                calculator.calculate()
                expression.setText(calculator.displayExpression)
            }
            "delete" -> calculator.removeLast()
            "open_parentheses" -> calculator.openParentheses()
            "close_parentheses" -> calculator.closeParentheses()
            "shift_right" -> {
                calculator.shiftRight()
                expression.setText(calculator.displayExpression)
                setResult()
            }
            "shift_left" -> {
                calculator.shiftLeft()
                expression.setText(calculator.displayExpression)
                setResult()
            }
        }

        expression.setText(calculator.displayExpression)
        setResult()
        expression.setSelection(expression.length())
    }

    fun operationButtonClick(view: View) {
        calculator.concatOperation(view.tag.toString())
        expression.setText(calculator.displayExpression)
        setResult()
        expression.setSelection(expression.length())
    }

    fun valueButtonClick(view: View) {
        calculator.concatValue(view.tag.toString())
        expression.setText(calculator.displayExpression)
        calculator.prepareToCalculate()
        setResult()
        expression.setSelection(expression.length())
    }
}