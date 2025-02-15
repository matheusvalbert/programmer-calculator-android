package com.matheusvalbert.programmercalculator.ui.history;

import static com.matheusvalbert.programmercalculator.ui.util.Constants.ERROR;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.usecase.history.ChangeHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.DeleteHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.LoadHistoryUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.SaveNewResultUseCase;
import com.matheusvalbert.programmercalculator.core.usecase.history.VerifyHistoryAvailabilityUseCase;
import com.matheusvalbert.programmercalculator.ui.base.BaseViewModel;
import com.matheusvalbert.programmercalculator.ui.history.state.HistoryState;

import java.util.List;

public class HistoryViewModel extends BaseViewModel {
    private final SaveNewResultUseCase mSaveNewResultUseCase;
    private final LoadHistoryUseCase mLoadHistoryUseCase;
    private final DeleteHistoryUseCase mDeleteHistoryUseCase;
    private final ChangeHistoryAvailabilityUseCase mChangeHistoryAvailabilityUseCase;
    private final VerifyHistoryAvailabilityUseCase mVerifyHistoryAvailabilityUseCase;

    public HistoryViewModel(SaveNewResultUseCase saveNewResultUseCase, LoadHistoryUseCase loadHistoryUseCase,
                            DeleteHistoryUseCase deleteHistoryUseCase,
                            ChangeHistoryAvailabilityUseCase changeHistoryAvailabilityUseCase,
                            VerifyHistoryAvailabilityUseCase verifyHistoryAvailabilityUseCase) {
        super(HistoryState.createHistoryState());
        mSaveNewResultUseCase = saveNewResultUseCase;
        mLoadHistoryUseCase = loadHistoryUseCase;
        mDeleteHistoryUseCase = deleteHistoryUseCase;
        mChangeHistoryAvailabilityUseCase = changeHistoryAvailabilityUseCase;
        mVerifyHistoryAvailabilityUseCase = verifyHistoryAvailabilityUseCase;
        updateHistoryButtonState();
    }

    public void updateHistoryButtonState() {
        HistoryState historyState = (HistoryState) mUiState.getValue();
        boolean isAvailable = mVerifyHistoryAvailabilityUseCase.invoke();
        historyState.updateHistoryAvailability(isAvailable);
        mUiState.postValue(historyState);
    }

    public void addNewHistory(History history) {
        if (history.getResult().equals(ERROR)) return;
        mSaveNewResultUseCase.invoke(history);
        changeHistoryAvailability(true);
    }

    public void updateHistory() {
        HistoryState historyState = (HistoryState) mUiState.getValue();
        List<History> historyList = mLoadHistoryUseCase.invoke();
        historyState.updateHistoryItems(historyList);
        mUiState.postValue(historyState);
    }

    public void deleteHistory() {
        mDeleteHistoryUseCase.invoke();
        changeHistoryAvailability(false);
    }

    private void changeHistoryAvailability(boolean isAvailable) {
        HistoryState historyState = (HistoryState) mUiState.getValue();
        mChangeHistoryAvailabilityUseCase.invoke(isAvailable);
        historyState.updateHistoryAvailability(isAvailable);
        mUiState.postValue(historyState);
    }
}
