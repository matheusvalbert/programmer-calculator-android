package com.matheusvalbert.programmercalculator.core.di

import com.matheusvalbert.programmercalculator.core.datastore.DataStoreHelper
import com.matheusvalbert.programmercalculator.core.datastore.DataStoreHelperImpl
import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import com.matheusvalbert.programmercalculator.core.usecase.ChangeBaseEvent
import com.matheusvalbert.programmercalculator.core.usecase.ChangeInputPosition
import com.matheusvalbert.programmercalculator.core.usecase.ClearUseCase
import com.matheusvalbert.programmercalculator.core.usecase.CloseParentheses
import com.matheusvalbert.programmercalculator.core.usecase.DeleteUseCase
import com.matheusvalbert.programmercalculator.core.usecase.EqualUseCase
import com.matheusvalbert.programmercalculator.core.usecase.GenerateResultsBeforeInput
import com.matheusvalbert.programmercalculator.core.usecase.KeyboardInput
import com.matheusvalbert.programmercalculator.core.usecase.NewDigitToExpression
import com.matheusvalbert.programmercalculator.core.usecase.NewOperationToExpression
import com.matheusvalbert.programmercalculator.core.usecase.OpenParentheses
import com.matheusvalbert.programmercalculator.core.usecase.ShiftLeftUseCase
import com.matheusvalbert.programmercalculator.core.usecase.ShiftRightUseCase
import com.matheusvalbert.programmercalculator.core.usecase.ShouldRequestReviewUseCase
import com.matheusvalbert.programmercalculator.core.usecase.UpdateNumberOfInteractionsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

  @Singleton
  @Binds
  abstract fun bindsDataStoreHelper(dataStoreHelperImpl: DataStoreHelperImpl): DataStoreHelper

  companion object {
    @Singleton
    @Provides
    fun providesShouldRequestReviewUseCase(dataStoreHelper: DataStoreHelper) =
      ShouldRequestReviewUseCase(dataStoreHelper)

    @Singleton
    @Provides
    fun providesUpdateNumberOfInteractionsUseCase(dataStoreHelper: DataStoreHelper) =
      UpdateNumberOfInteractionsUseCase(dataStoreHelper)

    @Singleton
    @Provides
    fun providesCalculatorUseCases(
      updateNumberOfInteractionsUseCase: UpdateNumberOfInteractionsUseCase,
      shouldRequestReviewUseCase: ShouldRequestReviewUseCase
    ) = CalculatorUseCases(
      keyboardInput = KeyboardInput(),
      changeInputPosition = ChangeInputPosition(),
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
      changeBaseEvent = ChangeBaseEvent(),
      updateNumberOfInteractionsUseCase = updateNumberOfInteractionsUseCase,
      shouldRequestReviewUseCase = shouldRequestReviewUseCase
    )
  }
}
