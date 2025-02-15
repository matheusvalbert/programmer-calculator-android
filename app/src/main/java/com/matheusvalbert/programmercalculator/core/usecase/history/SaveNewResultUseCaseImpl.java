package com.matheusvalbert.programmercalculator.core.usecase.history;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.domain.Tokenizer;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;

public class SaveNewResultUseCaseImpl implements SaveNewResultUseCase {
    private final Tokenizer mTokenizer;
    private final HistoryRepository mHistoryRepository;

    public SaveNewResultUseCaseImpl(Tokenizer tokenizer, HistoryRepository historyRepository) {
        mTokenizer = tokenizer;
        mHistoryRepository = historyRepository;
    }

    @Override
    public void invoke(History history) {
        String formatedExpression = mTokenizer.format(history.getExpression(), history.getBase());
        history.updateExpression(formatedExpression);
        mHistoryRepository.save(history);
    }
}
