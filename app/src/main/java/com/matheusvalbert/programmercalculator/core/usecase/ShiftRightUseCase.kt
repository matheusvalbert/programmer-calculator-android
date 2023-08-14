package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class ShiftRightUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return when (state.input) {
      "0" -> state
      "1" -> state.copy(input = "0")
      else -> state.copy(input = state.input.dropLast(1))
    }
  }
}
