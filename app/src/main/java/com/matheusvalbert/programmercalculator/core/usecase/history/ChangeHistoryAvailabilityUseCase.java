package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.usecase.UseCase;

public interface ChangeHistoryAvailabilityUseCase extends UseCase {
    void invoke(boolean isAvailable);
}
