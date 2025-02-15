package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;

public class DeleteHistoryUseCaseImpl implements DeleteHistoryUseCase {
    private final HistoryRepository mHistoryRepository;

    public DeleteHistoryUseCaseImpl(HistoryRepository historyRepository) {
        mHistoryRepository = historyRepository;
    }

    @Override
    public void invoke() {
        mHistoryRepository.delete();
    }
}
