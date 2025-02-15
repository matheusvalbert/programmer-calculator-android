package com.matheusvalbert.programmercalculator.core.repository;

import com.matheusvalbert.programmercalculator.core.database.dao.HistoryDao;
import com.matheusvalbert.programmercalculator.core.domain.History;

import java.util.List;

public class HistoryRepositoryImpl implements HistoryRepository {
    private final HistoryDao mHistoryDao;

    public HistoryRepositoryImpl(HistoryDao historyDao) {
        mHistoryDao = historyDao;
    }

    @Override
    public List<History> load() {
        return mHistoryDao.fetchAll();
    }

    @Override
    public void save(History history) {
        mHistoryDao.insert(history);
    }

    @Override
    public void delete() {
        mHistoryDao.wipe();
    }
}
