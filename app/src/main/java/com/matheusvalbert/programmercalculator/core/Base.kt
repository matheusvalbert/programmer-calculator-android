package com.matheusvalbert.programmercalculator.core

sealed class Base {
  data object Hex: Base()
  data object Dec: Base()
  data object Oct: Base()
  data object Bin: Base()
}
