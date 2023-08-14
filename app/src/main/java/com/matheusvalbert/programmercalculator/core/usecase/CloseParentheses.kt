package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.util.numberOfCloseParentheses
import com.matheusvalbert.programmercalculator.core.util.numberOfOpenParentheses

class CloseParentheses {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.input.numberOfCloseParentheses() >= state.input.numberOfOpenParentheses()
      || state.input.last() == '(' || state.input.last() == '÷' || state.input.last() == '×'
      || state.input.last() == '-' || state.input.last() == '+'
    ) {
      state
    } else {
      state.copy(input = state.input + ")")
    }
  }
}
