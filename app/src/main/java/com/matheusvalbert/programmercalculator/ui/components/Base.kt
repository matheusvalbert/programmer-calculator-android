package com.matheusvalbert.programmercalculator.ui.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.core.util.RequestReviewUtil
import com.matheusvalbert.programmercalculator.ui.theme.ProgrammerCalculatorTheme

@Composable
fun Base(
  name: String,
  result: String,
  active: Boolean,
  modifier: Modifier = Modifier,
  height: Int = (LocalConfiguration.current.screenHeightDp * 0.05).toInt(),
  calculatorViewModel: CalculatorViewModel = viewModel()
) {
  val activity = LocalContext.current as Activity
  val coroutineScope = rememberCoroutineScope()

  val textColor = MaterialTheme.colorScheme.primary

  var boxHeight by remember {
    mutableStateOf(0)
  }

  Box(Modifier.clickable {
    calculatorViewModel.onChangeBaseEvent(
      when (name) {
        "HEX" -> BaseEvent.Hex
        "DEC" -> BaseEvent.Dec
        "OCT" -> BaseEvent.Oct
        "BIN" -> BaseEvent.Bin
        else -> BaseEvent.Hex
      }
    )
    RequestReviewUtil.requestReviewIfNeeded(activity, coroutineScope, calculatorViewModel)
  }) {
    Row(modifier = modifier
      .height(height.dp)
      .onGloballyPositioned { boxHeight = it.size.height }) {
      Spacer(
        modifier = modifier
          .height(boxHeight.dp)
          .width(8.dp)
          .background(if (active) MaterialTheme.colorScheme.surfaceVariant else Color.Unspecified)
      )
      Row(
        modifier = modifier
          .fillMaxWidth()
          .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .height(height.dp)
        ) {
          AutoResizedText(
            text = name,
            textColor = textColor,
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier
          )
        }
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .height(height.dp)
        ) {
          AutoResizedText(
            text = result,
            textColor = textColor,
            style = MaterialTheme.typography.headlineLarge,
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun BasePreview() {
  ProgrammerCalculatorTheme {
    Base(name = "HEX", result = "0", active = true)
  }
}
