package com.matheusvalbert.programmercalculator.core.factory;

import com.matheusvalbert.programmercalculator.core.database.dao.Dao;
import com.matheusvalbert.programmercalculator.core.database.dao.HistoryDao;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepository;
import com.matheusvalbert.programmercalculator.core.repository.HistoryRepositoryImpl;
import com.matheusvalbert.programmercalculator.core.repository.Repository;

import java.io.InvalidClassException;

public class RepositoryFactory {
    public static <T extends Repository> Repository createRepository(Dao dao, Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(HistoryRepository.class)) {
            return new HistoryRepositoryImpl((HistoryDao) dao);
        } else {
            throw new InvalidClassException("must be an existent repository class");
        }
    }
}
