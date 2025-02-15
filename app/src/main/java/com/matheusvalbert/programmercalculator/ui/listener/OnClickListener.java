package com.matheusvalbert.programmercalculator.ui.listener;

import com.matheusvalbert.programmercalculator.core.domain.History;

public interface OnClickListener {
    void onClick(History history);
    void onLongClick(History history);
}
