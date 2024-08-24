package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.addCharacterInPosition
import com.matheusvalbert.programmercalculator.core.util.afterCursorValue

class NewDigitToExpression {

  operator fun invoke(state: ResultSate, digit: String): ResultSate {
    return if (state.input.isBlank()) {
      state.copy(
        input = digit,
        cursorPosition = state.inputPosition + 1
      )
    } else if (state.input.afterCursorValue(state.inputPosition) == ')') {
      state.copy(
        input = state.input.addCharacterInPosition("×$digit", state.inputPosition),
        cursorPosition = state.inputPosition + 2
      )
    } else {
      state.copy(
        input = state.input.addCharacterInPosition(digit, state.inputPosition),
        cursorPosition = state.inputPosition + 1
      )
    }
  }
}
