package com.matheusvalbert.programmercalculator.core.util

import com.matheusvalbert.programmercalculator.core.Base

fun String.afterEvaluateConverter(base: Base): String {
  return changeBase(
    removeOpenParenthesesInTheEnd().removeLastCharacterIfWasAnOperator().addCloseParentheses()
      .replaceOperatorCharacter().checkIfIsNotEmpty().splitExpression(), base
  )
}

private fun String.removeOpenParenthesesInTheEnd(): String {
  var string = this

  while (string.isNotEmpty() && string.last() == '(') {
    string = string.dropLast(1)
  }
  return string.ifEmpty { "0" }
}

private fun String.addCloseParentheses(): String {
  val numberOfMissingCloseParentheses = numberOfOpenParentheses(this.length) - numberOfCloseParentheses()
  return this + ")".repeat(numberOfMissingCloseParentheses)
}

private fun String.removeLastCharacterIfWasAnOperator(): String {
  if (last() == '÷' || last() == '×' || last() == '-' || last() == '+') {
    return dropLast(1)
  }
  return this
}

private fun String.replaceOperatorCharacter(): String {
  return replace("÷", "/").replace("×", "*")
}

private fun String.checkIfIsNotEmpty(): String {
  if (isEmpty()) {
    return "0"
  }
  return this
}

private fun String.splitExpression(): List<String> {
  val result = mutableListOf<String>()
  val currentPart = StringBuilder()

  forEachIndexed { i, char ->
    val isNumOrLetter = char.isDigit() || char.isLetter()

    if (i != 0 && isNumOrLetter != (this[i - 1].isDigit() || this[i - 1].isLetter())) {
      result.add(currentPart.toString())
      currentPart.clear()
    }
    currentPart.append(char)
  }
  result.add(currentPart.toString())

  return result
}

private fun changeBase(splitExpression: List<String>, base: Base): String {
  var finalEquation = ""

  splitExpression.forEach {
    if (it[0].isDigit() || it[0].isLetter())
      finalEquation += when (base) {
        Base.Hex -> it.toBigInteger(16)
        Base.Dec -> it
        Base.Oct -> it.toBigInteger(8)
        Base.Bin -> it.toBigInteger(2)
      }
    else
      finalEquation += it
  }

  return finalEquation
}

