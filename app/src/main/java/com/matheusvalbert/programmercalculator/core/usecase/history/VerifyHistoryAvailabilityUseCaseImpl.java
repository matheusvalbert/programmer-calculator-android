package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.sharedpreferences.HistorySharedPreferences;

public class VerifyHistoryAvailabilityUseCaseImpl implements VerifyHistoryAvailabilityUseCase {

    private final HistorySharedPreferences mHistorySharedPreferences;

    public VerifyHistoryAvailabilityUseCaseImpl(HistorySharedPreferences historySharedPreferences) {
        mHistorySharedPreferences = historySharedPreferences;
    }

    @Override
    public boolean invoke() {
        return mHistorySharedPreferences.isHistoryAvailable();
    }
}
