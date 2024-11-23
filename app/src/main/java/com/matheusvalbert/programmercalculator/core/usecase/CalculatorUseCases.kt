package com.matheusvalbert.programmercalculator.core.usecase

data class CalculatorUseCases(
  val keyboardInput: KeyboardInput,
  val changeInputPosition: ChangeInputPosition,
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
  val changeBaseEvent: ChangeBaseEvent,
  val updateNumberOfInteractionsUseCase: UpdateNumberOfInteractionsUseCase,
  val shouldRequestReviewUseCase: ShouldRequestReviewUseCase
)
