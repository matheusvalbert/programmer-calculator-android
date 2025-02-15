package com.matheusvalbert.programmercalculator.core.repository;

import com.matheusvalbert.programmercalculator.core.domain.History;

import java.util.List;

public interface HistoryRepository extends Repository {
    List<History> load();
    void save(History history);
    void delete();
}
