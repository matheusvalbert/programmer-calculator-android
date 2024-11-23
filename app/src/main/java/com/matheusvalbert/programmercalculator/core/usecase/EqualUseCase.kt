package com.matheusvalbert.programmercalculator.core.usecase

import com.matheusvalbert.programmercalculator.core.ResultSate
import com.matheusvalbert.programmercalculator.core.Base

class EqualUseCase {

  operator fun invoke(state: ResultSate): ResultSate {
    val result = if (state.hex == "Overflow") state.copy(input = "") else
      when (state.base) {
        is Base.Hex -> state.copy(input = state.hex, inputPosition = state.hex.length, cursorPosition = state.hex.length)
        is Base.Dec -> state.copy(input = state.dec, inputPosition = state.dec.length, cursorPosition = state.dec.length)
        is Base.Oct -> state.copy(input = state.oct, inputPosition = state.oct.length, cursorPosition = state.oct.length)
        is Base.Bin -> state.copy(input = state.bin, inputPosition = state.bin.length, cursorPosition = state.bin.length)
      }
    if (result.input == "0") return result.copy(input = "")
    return result
  }
}
