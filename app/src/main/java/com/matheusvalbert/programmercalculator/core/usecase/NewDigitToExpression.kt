package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class NewDigitToExpression {

  operator fun invoke(state: ResultSate, digit: String): ResultSate {
    return if (state.input == "0") {
      state.copy(input = digit)
    } else if (state.input.last() == ')') {
      state.copy(input = "${state.input}×$digit")
    } else {
      state.copy(input = state.input + digit)
    }
  }
}
