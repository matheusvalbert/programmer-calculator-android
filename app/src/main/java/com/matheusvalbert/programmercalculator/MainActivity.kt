package com.matheusvalbert.programmercalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.matheusvalbert.programmercalculator.core.CalculatorViewModel
import com.matheusvalbert.programmercalculator.core.event.BaseEvent
import com.matheusvalbert.programmercalculator.ui.components.Base
import com.matheusvalbert.programmercalculator.ui.components.Keyboard
import com.matheusvalbert.programmercalculator.ui.components.Result
import com.matheusvalbert.programmercalculator.ui.theme.ConfigureSystemUi
import com.matheusvalbert.programmercalculator.ui.theme.ProgrammerCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private lateinit var firebaseAnalytics: FirebaseAnalytics
  private val calculatorViewModel: CalculatorViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    firebaseAnalytics = Firebase.analytics

    setContent {
      ProgrammerCalculatorTheme {
        ConfigureSystemUi()
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          Column {
            Result(
              result = calculatorViewModel.result.value.input,
              cursorPositionAfterInsertion = calculatorViewModel.result.value.cursorPosition
            )
            Base(
              name = "HEX",
              result = calculatorViewModel.result.value.hex,
              active = calculatorViewModel.result.value.baseInput == BaseEvent.Hex
            )
            Base(
              name = "DEC",
              result = calculatorViewModel.result.value.dec,
              active = calculatorViewModel.result.value.baseInput == BaseEvent.Dec
            )
            Base(
              name = "OCT",
              result = calculatorViewModel.result.value.oct,
              active = calculatorViewModel.result.value.baseInput == BaseEvent.Oct
            )
            Base(
              name = "BIN",
              result = calculatorViewModel.result.value.bin,
              active = calculatorViewModel.result.value.baseInput == BaseEvent.Bin
            )
            Keyboard()
          }
        }
      }
    }
  }
}
