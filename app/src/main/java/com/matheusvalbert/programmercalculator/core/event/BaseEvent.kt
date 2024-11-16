package com.matheusvalbert.programmercalculator.core.event

sealed class BaseEvent {
  data object Hex: BaseEvent()
  data object Dec: BaseEvent()
  data object Oct: BaseEvent()
  data object Bin: BaseEvent()
}
