package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.usecase.UseCase;

public interface SaveNewResultUseCase extends UseCase {
    void invoke(History history);
}
