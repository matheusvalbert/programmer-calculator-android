package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class ShiftLeftUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input == "0") {
      state
    } else {
      state.copy(input = state.input + "0")
    }
  }
}
