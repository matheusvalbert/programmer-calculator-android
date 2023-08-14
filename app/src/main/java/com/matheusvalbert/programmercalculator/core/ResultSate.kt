package com.matheusvalbert.programmercalculator.core

import com.matheusvalbert.programmercalculator.core.event.BaseEvent

data class ResultSate(
  val hex: String = "0",
  val dec: String = "0",
  val oct: String = "0",
  val bin: String = "0",
  val input: String = "0",
  val baseInput: BaseEvent = BaseEvent.Hex
)
