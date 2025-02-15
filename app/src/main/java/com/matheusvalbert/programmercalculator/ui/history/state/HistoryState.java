package com.matheusvalbert.programmercalculator.ui.history.state;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.ui.base.State;
import com.matheusvalbert.programmercalculator.ui.history.list.HistoryItem;
import com.matheusvalbert.programmercalculator.ui.history.mapper.HistoryMapper;

import java.util.ArrayList;
import java.util.List;

public class HistoryState implements State {
    private boolean mHistoryAvailability;
    private List<HistoryItem> mHistoryItems;

    private HistoryState(List<HistoryItem> historyItems) {
        mHistoryAvailability = false;
        mHistoryItems = historyItems;
    }

    public static HistoryState createHistoryState() {
        return new HistoryState(new ArrayList<>());
    }

    public void updateHistoryItems(List<History> historyItems) {
        mHistoryItems = HistoryMapper.toHistoryItemList(historyItems);
    }

    public void updateHistoryAvailability(boolean isAvailable) {
        mHistoryAvailability = isAvailable;
    }

    public List<HistoryItem> getHistoryItems() {
        return mHistoryItems;
    }

    public boolean getHistoryAvailability() {
        return mHistoryAvailability;
    }
}
