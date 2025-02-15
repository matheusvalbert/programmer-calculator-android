package com.matheusvalbert.programmercalculator.core.factory;

import android.content.Context;

import com.matheusvalbert.programmercalculator.core.database.Database;
import com.matheusvalbert.programmercalculator.core.database.dao.Dao;
import com.matheusvalbert.programmercalculator.core.database.dao.HistoryDao;
import com.matheusvalbert.programmercalculator.core.database.dao.HistoryDaoImpl;

import java.io.InvalidClassException;

public class DatabaseFactory {
    public static Database createDatabase(Context context) {
        return new Database(context);
    }

    public static <T extends Dao> Dao createDao(Database database, Class<T> clazz) throws InvalidClassException {
        if (clazz.equals(HistoryDao.class)) {
            return new HistoryDaoImpl(database);
        } else {
            throw new InvalidClassException("must be an existent dao class");
        }
    }
}
