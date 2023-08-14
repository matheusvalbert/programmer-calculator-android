package com.matheusvalbert.programmercalculator.core.event

sealed class BaseEvent {
  object Hex: BaseEvent()
  object Dec: BaseEvent()
  object Oct: BaseEvent()
  object Bin: BaseEvent()
}
