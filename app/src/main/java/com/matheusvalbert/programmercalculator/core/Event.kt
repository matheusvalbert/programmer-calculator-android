package com.matheusvalbert.programmercalculator.core

sealed class Event {
  data class ChangeBase(val base: Base): Event()
  data class Keyboard(val input: String): Event()
  data class ChangePosition(val inputPosition: Int): Event()
  data class Digit(val digit: String) : Event()
  data class Operation(val operation: String): Event()
  data object Clear: Event()
  data object OpenParentheses: Event()
  data object CloseParentheses: Event()
  data object Shl: Event()
  data object Shr: Event()
  data object Delete: Event()
  data object Equal: Event()
}
