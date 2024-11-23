package com.matheusvalbert.programmercalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.Base
import com.matheusvalbert.programmercalculator.core.Event

@Composable
fun Keyboard(
  shouldRequestReview: () -> Unit,
  calculatorViewModel: CalculatorViewModel = viewModel()
) {
  val height = ((LocalConfiguration.current.screenHeightDp / 7) * 0.63).toInt()
  val width = ((LocalConfiguration.current.screenWidthDp / 4) * 0.95).toInt()
  val functionButtonColor = MaterialTheme.colorScheme.secondary
  val operationButtonColor = MaterialTheme.colorScheme.surfaceVariant
  val operationButtonTextColor = Color.White

  Column(verticalArrangement = Arrangement.SpaceBetween) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "C",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = {
          calculatorViewModel.onEvent(Event.Clear)
          shouldRequestReview()
        })
      Button(
        symbol = "(",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = { calculatorViewModel.onEvent(Event.OpenParentheses) })
      Button(
        symbol = ")",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        onClick = { calculatorViewModel.onEvent(Event.CloseParentheses) })
      Button(
        symbol = "<<",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        enabled = calculatorViewModel.result.value.base == Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Shl) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "D",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("D")) })
      Button(
        symbol = "E",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("E")) })
      Button(
        symbol = "F",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("F")) })
      Button(
        symbol = ">>",
        height = height,
        width = width,
        backgroundColor = functionButtonColor,
        enabled = calculatorViewModel.result.value.base == Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Shr) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "A",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("A")) })
      Button(
        symbol = "B",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("B")) })
      Button(
        symbol = "C",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex,
        onClick = { calculatorViewModel.onEvent(Event.Digit("C")) })
      Button(
        symbol = "÷",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onEvent(Event.Operation("÷")) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "7",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("7")) })
      Button(
        symbol = "8",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex || calculatorViewModel.result.value.base == Base.Dec,
        onClick = { calculatorViewModel.onEvent(Event.Digit("8")) })
      Button(
        symbol = "9",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base == Base.Hex || calculatorViewModel.result.value.base == Base.Dec,
        onClick = { calculatorViewModel.onEvent(Event.Digit("9")) })
      Button(
        symbol = "×",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onEvent(Event.Operation("×")) }
      )
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "4",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("4")) })
      Button(
        symbol = "5",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("5")) })
      Button(
        symbol = "6",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("6")) })
      Button(
        symbol = "-",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onEvent(Event.Operation("-")) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "1",
        height = height,
        width = width,
        onClick = { calculatorViewModel.onEvent(Event.Digit("1")) })
      Button(
        symbol = "2",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("2")) })
      Button(
        symbol = "3",
        height = height,
        width = width,
        enabled = calculatorViewModel.result.value.base != Base.Bin,
        onClick = { calculatorViewModel.onEvent(Event.Digit("3")) })
      Button(
        symbol = "+",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = { calculatorViewModel.onEvent(Event.Operation("+")) })
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Button(
        symbol = "0",
        height = height,
        width = width * 2 + 8,
        onClick = { calculatorViewModel.onEvent(Event.Digit("0")) })
      Button(
        symbol = "⌫",
        height = height,
        width = width,
        onClick = { calculatorViewModel.onEvent(Event.Delete) })
      Button(
        symbol = "=",
        height = height,
        width = width,
        backgroundColor = operationButtonColor,
        textColor = operationButtonTextColor,
        onClick = {
          calculatorViewModel.onEvent(Event.Equal)
          shouldRequestReview()
        })
    }
  }
}
