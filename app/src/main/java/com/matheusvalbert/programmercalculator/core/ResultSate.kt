package com.matheusvalbert.programmercalculator.core

data class ResultSate(
  val hex: String = "0",
  val dec: String = "0",
  val oct: String = "0",
  val bin: String = "0",
  val input: String = "",
  val inputPosition: Int = input.length,
  val cursorPosition: Int = input.length,
  val base: Base = Base.Hex,
)
