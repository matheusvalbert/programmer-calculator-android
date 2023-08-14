package com.matheusvalbert.programmercalculator.core.di

import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import com.matheusvalbert.programmercalculator.core.usecase.ClearUseCase
import com.matheusvalbert.programmercalculator.core.usecase.CloseParentheses
import com.matheusvalbert.programmercalculator.core.usecase.CopyResultForInput
import com.matheusvalbert.programmercalculator.core.usecase.DeleteUseCase
import com.matheusvalbert.programmercalculator.core.usecase.EqualUseCase
import com.matheusvalbert.programmercalculator.core.usecase.GenerateResultsBeforeInput
import com.matheusvalbert.programmercalculator.core.usecase.NewDigitToExpression
import com.matheusvalbert.programmercalculator.core.usecase.NewOperationToExpression
import com.matheusvalbert.programmercalculator.core.usecase.OpenParentheses
import com.matheusvalbert.programmercalculator.core.usecase.ShiftLeftUseCase
import com.matheusvalbert.programmercalculator.core.usecase.ShiftRightUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

  @Singleton
  @Provides
  fun providesCalculatorUseCases(): CalculatorUseCases {
    return CalculatorUseCases(
      newDigitToExpression = NewDigitToExpression(),
      newOperationToExpression = NewOperationToExpression(),
      clearUseCase = ClearUseCase(),
      openParentheses = OpenParentheses(),
      closeParentheses = CloseParentheses(),
      shiftLeftUseCase = ShiftLeftUseCase(),
      shiftRightUseCase = ShiftRightUseCase(),
      deleteUseCase = DeleteUseCase(),
      equalUseCase = EqualUseCase(),
      generateResultsBeforeInput = GenerateResultsBeforeInput(),
      copyResultForInput = CopyResultForInput()
    )
  }
}
