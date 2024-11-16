package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class KeyboardInput {

  operator fun invoke(state: ResultSate, input: String): ResultSate {
    return state.copy(input = input)
  }
}
