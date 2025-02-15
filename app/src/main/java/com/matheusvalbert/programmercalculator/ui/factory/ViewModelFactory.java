package com.matheusvalbert.programmercalculator.ui.factory;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.matheusvalbert.programmercalculator.CalculatorApplication;
import com.matheusvalbert.programmercalculator.core.database.Database;
import com.matheusvalbert.programmercalculator.core.database.dao.HistoryDao;
import com.matheusvalbert.programmercalculator.core.factory.DatabaseFactory;
import com.matheusvalbert.programmercalculator.core.factory.RepositoryFactory;
import com.matheusvalbert.programmercalculator.core.factory.SharedPreferencesFactory;
import com.matheusvalbert.programmercalculator.core.factory.UseCaseFactory;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferences;
import com.matheusvalbert.programmercalculator.core.sharedpreferences.ReviewSharedPreferences;
import com.matheusvalbert.programmercalculator.core.usecase.expression.RequestReviewUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.ChangeHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.DeleteHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.LoadHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.SaveNewResultUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.VerifyHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.ui.base.BaseViewModel;
import com.matheusvalbert.programmercalculator.ui.expression.ExpressionViewModel;
import com.matheusvalbert.programmercalculator.ui.history.HistoryViewModel;

import java.io.InvalidClassException;

public class ViewModelFactory {
    public static <T extends BaseViewModel> ViewModelInitializer createViewModel(Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(ExpressionViewModel.class)) {
            return createExpressionViewModel();
        } else if (clazz.equals(HistoryViewModel.class)) {
            return createHistoryViewModel();
        } else {
            throw new InvalidClassException("must be an existent view model class");
        }
    }

    private static ViewModelInitializer<ExpressionViewModel> createExpressionViewModel() {
        return new ViewModelInitializer<>(
            ExpressionViewModel.class,
            creationExtras -> {
                CalculatorApplication application = (CalculatorApplication) creationExtras.get(APPLICATION_KEY);
                assert application != null;
                try {
                    ReviewSharedPreferences reviewSharedPreferences = (ReviewSharedPreferences)
                        SharedPreferencesFactory.createSharedPreferences(
                            application.getApplicationContext(), ReviewSharedPreferences.class);
                    RequestReviewUseCase requestReviewUseCase = (RequestReviewUseCase)
                        UseCaseFactory.createUseCase(reviewSharedPreferences, RequestReviewUseCase.class);
                    return new ExpressionViewModel(
                        UseCaseFactory.createExpressionUseCase(),
                        UseCaseFactory.createEvaluatorUseCase(),
                        requestReviewUseCase);
                } catch (InvalidClassException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }

    private static ViewModelInitializer<HistoryViewModel> createHistoryViewModel() {
        return new ViewModelInitializer<>(
            HistoryViewModel.class,
            creationExtras -> {
                CalculatorApplication application = (CalculatorApplication) creationExtras.get(APPLICATION_KEY);
                assert application != null;
                try {
                    Database database = DatabaseFactory.createDatabase(application.getApplicationContext());
                    HistoryDao historyDao = (HistoryDao) DatabaseFactory.createDao(database, HistoryDao.class);
                    HistoryRepository historyRepository = (HistoryRepository) RepositoryFactory.createRepository(historyDao, HistoryRepository.class);
                    HistorySharedPreferences historySharedPreferences = (HistorySharedPreferences) SharedPreferencesFactory.createSharedPreferences(application.getApplicationContext(), HistorySharedPreferences.class);
                    SaveNewResultUseCase saveNewResultUseCase = (SaveNewResultUseCase) UseCaseFactory.createUseCase(historyRepository, SaveNewResultUseCase.class);
                    LoadHistoryUseCase loadHistoryUseCase = (LoadHistoryUseCase) UseCaseFactory.createUseCase(historyRepository, LoadHistoryUseCase.class);
                    DeleteHistoryUseCase deleteHistoryUseCase = (DeleteHistoryUseCase) UseCaseFactory.createUseCase(historyRepository, DeleteHistoryUseCase.class);
                    ChangeHistoryAvailabilityUseCase changeHistoryAvailabilityUseCase = (ChangeHistoryAvailabilityUseCase) UseCaseFactory.createUseCase(historySharedPreferences, ChangeHistoryAvailabilityUseCase.class);
                    VerifyHistoryAvailabilityUseCase verifyHistoryAvailabilityUseCase = (VerifyHistoryAvailabilityUseCase) UseCaseFactory.createUseCase(historySharedPreferences, VerifyHistoryAvailabilityUseCase.class);
                    return new HistoryViewModel(saveNewResultUseCase, loadHistoryUseCase, deleteHistoryUseCase, changeHistoryAvailabilityUseCase, verifyHistoryAvailabilityUseCase);
                } catch (InvalidClassException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }
}
