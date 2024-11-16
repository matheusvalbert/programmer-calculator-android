package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class ChangeInputPosition {

  operator fun invoke(state: ResultSate, inputPosition: Int): ResultSate {
    return state.copy(inputPosition = inputPosition, cursorPosition = inputPosition)
  }
}
