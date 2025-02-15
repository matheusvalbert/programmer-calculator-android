package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.usecase.UseCase;

import java.util.List;

public interface LoadHistoryUseCase extends UseCase {
    List<History> invoke();
}
