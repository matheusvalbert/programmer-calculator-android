package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.event.BaseEvent

class CopyResultForInput {

  operator fun invoke(state: ResultSate): ResultSate {
    return if (state.hex == "Overflow") ResultSate(baseInput = state.baseInput) else
      when (state.baseInput) {
        is BaseEvent.Hex -> state.copy(input = state.hex)
        is BaseEvent.Dec -> state.copy(input = state.dec)
        is BaseEvent.Oct -> state.copy(input = state.oct)
        is BaseEvent.Bin -> state.copy(input = state.bin)
      }
  }
}
