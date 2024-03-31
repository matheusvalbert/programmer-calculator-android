package com.matheusvalbert.programmercalculator.core.di

import com.matheusvalbert.programmercalculator.core.repository.RequestReviewRepository
import com.matheusvalbert.programmercalculator.core.repository.RequestReviewRepositoryImpl
import com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
import com.matheusvalbert.programmercalculator.core.usecase.ClearUseCase
import com.matheusvalbert.programmercalculator.core.usecase.CloseParentheses
import com.matheusvalbert.programmercalculator.core.usecase.CopyResultForInput
import com.matheusvalbert.programmercalculator.core.usecase.DeleteUseCase
import com.matheusvalbert.programmercalculator.core.usecase.EqualUseCase
import com.matheusvalbert.programmercalculator.core.usecase.GenerateResultsBeforeInput
import com.matheusvalbert.programmercalculator.core.usecase.HasBeenRequestedUseCase
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
  abstract fun bindsRequestReviewRepository(requestReviewRepositoryImpl: RequestReviewRepositoryImpl): RequestReviewRepository

  companion object {
    @Singleton
    @Provides
    fun bindsHasBeenRequestedUseCase(requestReviewRepository: RequestReviewRepository) =
      HasBeenRequestedUseCase(requestReviewRepository)

    @Singleton
    @Provides
    fun bindsShouldRequestReviewUseCase(requestReviewRepository: RequestReviewRepository) =
      ShouldRequestReviewUseCase(requestReviewRepository)

    @Singleton
    @Provides
    fun bindsUpdateNumberOfInteractionsUseCase(requestReviewRepository: RequestReviewRepository) =
      UpdateNumberOfInteractionsUseCase(requestReviewRepository)

    @Singleton
    @Provides
    fun providesCalculatorUseCases(
      hasBeenRequestedUseCase: HasBeenRequestedUseCase,
      updateNumberOfInteractionsUseCase: UpdateNumberOfInteractionsUseCase,
      shouldRequestReviewUseCase: ShouldRequestReviewUseCase
    ) = CalculatorUseCases(
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
      copyResultForInput = CopyResultForInput(),
      hasBeenRequestedUseCase = hasBeenRequestedUseCase,
      updateNumberOfInteractionsUseCase = updateNumberOfInteractionsUseCase,
      shouldRequestReviewUseCase = shouldRequestReviewUseCase
    )
  }
}
