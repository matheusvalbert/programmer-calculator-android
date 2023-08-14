package com.matheusvalbert.programmercalculator.core.usecase

data class CalculatorUseCases(
  val newDigitToExpression: NewDigitToExpression,
  val newOperationToExpression: NewOperationToExpression,
  val clearUseCase: ClearUseCase,
  val openParentheses: OpenParentheses,
  val closeParentheses: CloseParentheses,
  val shiftLeftUseCase: ShiftLeftUseCase,
  val shiftRightUseCase: ShiftRightUseCase,
  val deleteUseCase: DeleteUseCase,
  val equalUseCase: EqualUseCase,
  val generateResultsBeforeInput: GenerateResultsBeforeInput,
  val copyResultForInput: CopyResultForInput
)
