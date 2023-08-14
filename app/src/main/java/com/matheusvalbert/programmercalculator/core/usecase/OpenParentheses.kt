package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class OpenParentheses {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input == "0") {
      state.copy(input = "(")
    } else if (state.input.last() != '÷' && state.input.last() != '×' && state.input.last() != '-' && state.input.last() != '+' && state.input.last() != '(') {
      state.copy(input = state.input + "×(")
    } else {
      state.copy(input = state.input + "(")
    }
  }
}
