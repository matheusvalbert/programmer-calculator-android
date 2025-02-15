package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;

import java.util.List;

public class LoadHistoryUseCaseImpl implements LoadHistoryUseCase {
    private final HistoryRepository mHistoryRepository;

    public LoadHistoryUseCaseImpl(HistoryRepository historyRepository) {
        mHistoryRepository = historyRepository;
    }

    @Override
    public List<History> invoke() {
        return mHistoryRepository.load();
    }
}
