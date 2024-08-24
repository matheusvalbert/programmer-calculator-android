package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.addCharacterInPosition
import com.matheusvalbert.programmercalculator.core.util.afterCursorValue

class OpenParentheses {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input.isBlank()) {
      state.copy(
        input = "(",
        cursorPosition = state.inputPosition + 1
      )
    } else if (state.input.afterCursorValue(state.inputPosition) != '÷' &&
      state.input.afterCursorValue(state.inputPosition) != '×' &&
      state.input.afterCursorValue(state.inputPosition) != '-' &&
      state.input.afterCursorValue(state.inputPosition) != '+' &&
      state.input.afterCursorValue(state.inputPosition) != '('
    ) {
      state.copy(
        input = state.input.addCharacterInPosition("×(", state.inputPosition),
        cursorPosition = state.inputPosition + 2
      )
    } else {
      state.copy(
        input = state.input.addCharacterInPosition("(", state.inputPosition),
        cursorPosition = state.inputPosition + 1
      )
    }
  }
}
