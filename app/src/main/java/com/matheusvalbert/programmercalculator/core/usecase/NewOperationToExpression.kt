package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class NewOperationToExpression {

  operator fun invoke(state: ResultSate, operation: String): ResultSate {
    return if (state.input == "0" || state.input.last() == '(') {
      state
    } else if (state.input.last() == '÷' || state.input.last() == '×' || state.input.last() == '-' || state.input.last() == '+') {
      state.copy(input = state.input.dropLast(1) + operation)
    } else {
      state.copy(input = state.input + operation)
    }
  }
}
