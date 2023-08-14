package com.matheusvalbert.programmercalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matheusvalbert.programmercalculator.ui.theme.ProgrammerCalculatorTheme

@Composable
fun Result(
  result: String,
  modifier: Modifier = Modifier,
  height: Int = (LocalConfiguration.current.screenHeightDp * 0.1).toInt()
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .height(height.dp)
  ) {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .padding(end = 4.dp),
      horizontalArrangement = Arrangement.End,
    ) {
      AutoResizedText(text = result, textColor = MaterialTheme.colorScheme.primary)
    }
  }
}

@Preview
@Composable
fun ResultPreview() {
  ProgrammerCalculatorTheme {
    Result("50")
  }
}
