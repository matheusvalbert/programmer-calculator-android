package com.matheusvalbert.programmercalculator.core.database.dao;

import com.matheusvalbert.programmercalculator.core.domain.History;

import java.util.List;

public interface HistoryDao extends Dao {
    void insert(History history);
    List<History> fetchAll();
    void wipe();
}
