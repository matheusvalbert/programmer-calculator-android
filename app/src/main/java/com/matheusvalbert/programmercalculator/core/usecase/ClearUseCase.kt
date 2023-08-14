package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate

class ClearUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    return ResultSate(baseInput = state.baseInput)
  }
}
