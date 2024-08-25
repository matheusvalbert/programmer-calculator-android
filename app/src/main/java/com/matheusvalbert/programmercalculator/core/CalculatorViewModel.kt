package com.matheusvalbert.programmercalculator.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.core.event.InputEvent
import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
  private val calculatorUseCases: CalculatorUseCases
) : ViewModel() {

  private val _result = mutableStateOf(ResultSate())
  val result: State<ResultSate> = _result

  fun onChangeBaseEvent(event: BaseEvent) {
    when (event) {
      is BaseEvent.Hex -> _result.value = result.value.copy(baseInput = BaseEvent.Hex)
      is BaseEvent.Dec -> _result.value = result.value.copy(baseInput = BaseEvent.Dec)
      is BaseEvent.Oct -> _result.value = result.value.copy(baseInput = BaseEvent.Oct)
      is BaseEvent.Bin -> _result.value = result.value.copy(baseInput = BaseEvent.Bin)
    }

    _result.value = calculatorUseCases.copyResultForInput(result.value)

    _result.value = result.value.copy(cursorPosition = result.value.input.length)
  }

  fun onInputEvent(event: InputEvent) {
    try {
      when (event) {
        is InputEvent.Digit -> _result.value = calculatorUseCases.newDigitToExpression(result.value, event.digit)

        is InputEvent.Operation -> _result.value = calculatorUseCases.newOperationToExpression(result.value, event.operation)

        is InputEvent.Clear -> _result.value = calculatorUseCases.clearUseCase(result.value)

        is InputEvent.OpenParentheses -> _result.value = calculatorUseCases.openParentheses(result.value)

        is InputEvent.CloseParentheses -> _result.value = calculatorUseCases.closeParentheses(result.value)

        is InputEvent.Shl -> _result.value = calculatorUseCases.shiftLeftUseCase(result.value)

        is InputEvent.Shr -> _result.value = calculatorUseCases.shiftRightUseCase(result.value)

        is InputEvent.Delete -> _result.value = calculatorUseCases.deleteUseCase(result.value)

        is InputEvent.Equal -> _result.value = calculatorUseCases.equalUseCase(result.value)
      }
    } catch (e: Exception) {
      Firebase.crashlytics.setCustomKeys {
        key("input", result.value.input)
        key("input_position", result.value.inputPosition)
        key("cursor_position", result.value.cursorPosition)
        key("base_input", result.value.baseInput.toString())
      }
      Firebase.crashlytics.recordException(e)
    }

    viewModelScope.launch {
      _result.value = calculatorUseCases.generateResultsBeforeInput(result.value)
    }
  }

  fun onChangeInputEvent(position: Int) {
    _result.value = result.value.copy(inputPosition = position)
  }

  fun onPasteInputEvent(input: String) {
    _result.value = result.value.copy(input = input, cursorPosition = input.length)

    viewModelScope.launch {
      _result.value = calculatorUseCases.generateResultsBeforeInput(result.value)
    }
  }

  suspend fun shouldRequestReview(): Boolean {
    if(calculatorUseCases.hasBeenRequestedUseCase()) {
      return false
    }
    calculatorUseCases.updateNumberOfInteractionsUseCase()

    return calculatorUseCases.shouldRequestReviewUseCase()
  }
}
