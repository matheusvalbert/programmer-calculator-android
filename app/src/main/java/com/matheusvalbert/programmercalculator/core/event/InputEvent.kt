package com.matheusvalbert.programmercalculator.core.event

sealed class InputEvent {
  data class Digit(val digit: String) : InputEvent()
  data class Operation(val operation: String): InputEvent()
  object Clear: InputEvent()
  object OpenParentheses: InputEvent()
  object CloseParentheses: InputEvent()
  object Shl: InputEvent()
  object Shr: InputEvent()
  object Delete: InputEvent()
  object Equal: InputEvent()
}
