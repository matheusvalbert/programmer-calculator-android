package com.matheusvalbert.programmercalculator.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.core.event.InputEvent
import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
  private val calculatorUseCases: CalculatorUseCases
) : ViewModel() {

  private val _baseEventFlow = MutableSharedFlow<BaseEvent>()
  val baseEventFlow = _baseEventFlow.asSharedFlow()

  private val _result = mutableStateOf(ResultSate())
  val result: State<ResultSate> = _result

  fun onChangeBaseEvent(event: BaseEvent) {
    when (event) {
      is BaseEvent.Hex -> _result.value = result.value.copy(baseInput = BaseEvent.Hex)
      is BaseEvent.Dec -> _result.value = result.value.copy(baseInput = BaseEvent.Dec)
      is BaseEvent.Oct -> _result.value = result.value.copy(baseInput = BaseEvent.Oct)
      is BaseEvent.Bin -> _result.value = result.value.copy(baseInput = BaseEvent.Bin)
    }

    viewModelScope.launch {
      _result.value = calculatorUseCases.copyResultForInput(result.value)
    }
  }

  fun onInputEvent(event: InputEvent) {
    when (event) {
      is InputEvent.Digit -> viewModelScope.launch {
        _result.value = calculatorUseCases.newDigitToExpression(result.value, event.digit)
      }

      is InputEvent.Operation -> viewModelScope.launch {
        _result.value = calculatorUseCases.newOperationToExpression(result.value, event.operation)
      }

      is InputEvent.Clear -> viewModelScope.launch {
        _result.value = calculatorUseCases.clearUseCase(result.value)
      }

      is InputEvent.OpenParentheses -> viewModelScope.launch {
        _result.value = calculatorUseCases.openParentheses(result.value)
      }

      is InputEvent.CloseParentheses -> viewModelScope.launch {
        _result.value = calculatorUseCases.closeParentheses(result.value)
      }

      is InputEvent.Shl -> viewModelScope.launch {
        _result.value = calculatorUseCases.shiftLeftUseCase(result.value)
      }

      is InputEvent.Shr -> viewModelScope.launch {
        _result.value = calculatorUseCases.shiftRightUseCase(result.value)
      }

      is InputEvent.Delete -> viewModelScope.launch {
        _result.value = calculatorUseCases.deleteUseCase(result.value)
      }

      is InputEvent.Equal -> viewModelScope.launch {
        _result.value = calculatorUseCases.equalUseCase(result.value)
      }
    }

    viewModelScope.launch {
      _result.value = calculatorUseCases.generateResultsBeforeInput(result.value)
    }
  }
}
