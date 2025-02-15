package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferences;

public class ChangeHistoryAvailabilityUseCaseImpl implements ChangeHistoryAvailabilityUseCase {

    private final HistorySharedPreferences mHistorySharedPreferences;

    public ChangeHistoryAvailabilityUseCaseImpl(HistorySharedPreferences historySharedPreferences) {
        mHistorySharedPreferences = historySharedPreferences;
    }

    @Override
    public void invoke(boolean isAvailable) {
        mHistorySharedPreferences.setHistoryAvailable(isAvailable);
    }
}
