package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.usecase.UseCase;

public interface VerifyHistoryAvailabilityUseCase extends UseCase {
    boolean invoke();
}
