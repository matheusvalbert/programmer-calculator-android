package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class DeleteUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input == "0" || state.input.count() == 1) {
      state.copy(input = "0")
    } else {
      state.copy(input = state.input.dropLast(1))
    }
  }
}
