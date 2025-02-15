package com.matheusvalbert.programmercalculator.ui.history.mapper;

import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.ui.expression.state.ExpressionState;
import com.matheusvalbert.programmercalculator.ui.history.list.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper {
    public static List<HistoryItem> toHistoryItemList(List<History> historyList) {
        List<HistoryItem> historyItemList = new ArrayList<>();

        for (History history : historyList) {
            historyItemList.add(toHistoryItem(history));
        }

        return historyItemList;
    }

    public static HistoryItem toHistoryItem(History history) {
        return new HistoryItem(
            history.getId(),
            history.getBase(),
            history.getExpression(),
            history.getResult()
        );
    }

    public static History toHistory(ExpressionState expressionState) {
        @Base int base = expressionState.getBase();
        return new History(
            expressionState.getExpression(),
            base,
            expressionState.getResult(base)
        );
    }

    public static History toHistory(HistoryItem historyItem) {
        return new History(
            historyItem.getExpression(),
            historyItem.getBase(),
            historyItem.getResult()
        );
    }
}
