package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.addCharacterInPosition
import com.matheusvalbert.programmercalculator.core.util.afterCursorValue
import com.matheusvalbert.programmercalculator.core.util.substituteCharacter

class NewOperationToExpression {

  operator fun invoke(state: ResultSate, operation: String): ResultSate {
    return if (state.input.isBlank() || state.input.afterCursorValue(state.inputPosition) == '(') {
      state
    } else if (state.input.afterCursorValue(state.inputPosition) == '÷' ||
      state.input.afterCursorValue(state.inputPosition) == '×' ||
      state.input.afterCursorValue(state.inputPosition) == '-' ||
      state.input.afterCursorValue(state.inputPosition) == '+'
    ) {
      state.copy(input = state.input.substituteCharacter(operation, state.inputPosition))
    } else {
      state.copy(
        input = state.input.addCharacterInPosition(operation, state.inputPosition),
        cursorPosition = state.inputPosition + 1
      )
    }
  }
}
