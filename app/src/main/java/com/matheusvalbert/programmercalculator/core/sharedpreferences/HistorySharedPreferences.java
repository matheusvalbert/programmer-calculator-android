package com.matheusvalbert.programmercalculator.core.sharedpreferences;

public interface HistorySharedPreferences extends SharedPreferences {
    void setHistoryAvailable(boolean isAvailable);
    boolean isHistoryAvailable();
}
