package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.addCharacterInPosition
import com.matheusvalbert.programmercalculator.core.util.afterCursorValue
import com.matheusvalbert.programmercalculator.core.util.numberOfCloseParentheses
import com.matheusvalbert.programmercalculator.core.util.numberOfOpenParentheses

class CloseParentheses {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input.numberOfCloseParentheses() >=
      state.input.numberOfOpenParentheses(state.inputPosition)
      || state.input.afterCursorValue(state.inputPosition) == '('
      || state.input.afterCursorValue(state.inputPosition) == '÷'
      || state.input.afterCursorValue(state.inputPosition) == '×'
      || state.input.afterCursorValue(state.inputPosition) == '-'
      || state.input.afterCursorValue(state.inputPosition) == '+'
    ) {
      state
    } else if (state.input.length != state.inputPosition) {
      state.copy(
        input = state.input.addCharacterInPosition(")×", state.inputPosition),
        cursorPosition = state.inputPosition + 2
      )
    } else {
      state.copy(
        input = state.input.addCharacterInPosition(")", state.inputPosition),
        cursorPosition = state.inputPosition + 1
      )
    }
  }
}
