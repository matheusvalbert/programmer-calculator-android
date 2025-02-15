package com.matheusvalbert.programmercalculator.core.listener;

import com.matheusvalbert.programmercalculator.core.domain.History;

public interface ResultListener {
    void onNewResult(History history);
}
