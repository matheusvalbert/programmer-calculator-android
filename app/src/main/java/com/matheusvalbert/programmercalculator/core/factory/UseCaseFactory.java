package com.matheusvalbert.programmercalculator.core.factory;

import com.matheusvalbert.programmercalculator.core.domain.Evaluator;
import com.matheusvalbert.programmercalculator.core.domain.Expression;
import com.matheusvalbert.programmercalculator.core.domain.Tokenizer;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;
import com.matheusvalbert.programmercalculator.core.repository.Repository;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferences;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.ReviewSharedPreferences;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.SharedPreferences;
import com.matheusvalbert.programmercalculator.core.usecase.UseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.RequestReviewUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.RequestReviewUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.history.ChangeHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.ChangeHistoryAvailabilityUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.history.DeleteHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.DeleteHistoryUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.expression.EvaluatorUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.EvaluatorUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.expression.ExpressionUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.expression.ExpressionUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.history.LoadHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.LoadHistoryUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.history.SaveNewResultUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.SaveNewResultUseCaseImpl;
import com.matheusvalbert.programmercalculator.core.usecase.history.VerifyHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.VerifyHistoryAvailabilityUseCaseImpl;

import java.io.InvalidClassException;

public class UseCaseFactory {

    public static ExpressionUseCase createExpressionUseCase() {
        return new ExpressionUseCaseImpl(new Expression());
    }

    public static EvaluatorUseCase createEvaluatorUseCase() {
        return new EvaluatorUseCaseImpl(new Tokenizer(), new Evaluator());
    }

    public static <T extends UseCase> UseCase createUseCase(SharedPreferences sharedPreferences, Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(RequestReviewUseCase.class)) {
            return new RequestReviewUseCaseImpl((ReviewSharedPreferences) sharedPreferences);
        } else if (clazz.equals(ChangeHistoryAvailabilityUseCase.class)) {
            return new ChangeHistoryAvailabilityUseCaseImpl((HistorySharedPreferences) sharedPreferences);
        } else if (clazz.equals(VerifyHistoryAvailabilityUseCase.class)) {
            return new VerifyHistoryAvailabilityUseCaseImpl((HistorySharedPreferences) sharedPreferences);
        } else {
            throw new InvalidClassException("must be an existent use case class");
        }
    }

    public static <T extends UseCase> UseCase createUseCase(Repository repository, Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(SaveNewResultUseCase.class)) {
            return new SaveNewResultUseCaseImpl(new Tokenizer(), (HistoryRepository) repository);
        } else if (clazz.equals(LoadHistoryUseCase.class)) {
            return new LoadHistoryUseCaseImpl((HistoryRepository) repository);
        } else if (clazz.equals(DeleteHistoryUseCase.class)) {
            return new DeleteHistoryUseCaseImpl((HistoryRepository) repository);
        } else {
            throw new InvalidClassException("must be an existent use case class");
        }
    }
}
