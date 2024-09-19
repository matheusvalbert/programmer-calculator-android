package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class ShiftRightUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input.isBlank() || state.input == "1") {
      state.copy(
        input = "0",
        cursorPosition = 1
      )
    } else {
      state.copy(
        input = state.input.dropLast(1),
        cursorPosition = state.input.length - 1
      )
    }
  }
}
