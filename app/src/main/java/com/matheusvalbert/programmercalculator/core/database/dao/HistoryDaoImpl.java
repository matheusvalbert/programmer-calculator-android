package com.matheusvalbert.programmercalculator.core.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matheusvalbert.programmercalculator.core.database.Database;
import com.matheusvalbert.programmercalculator.core.domain.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryDaoImpl implements HistoryDao {
    private final Database mDatabase;

    public HistoryDaoImpl(Database database) {
        mDatabase = database;
    }

    @Override
    public void insert(History history) {
        SQLiteDatabase db = mDatabase.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Database.HistoryTable.COLUMN_EXPRESSION, history.getExpression());
        values.put(Database.HistoryTable.COLUMN_BASE, history.getBase());
        values.put(Database.HistoryTable.COLUMN_RESULT, history.getResult());

        db.insert(Database.HistoryTable.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public List<History> fetchAll() {
        SQLiteDatabase db = mDatabase.getReadableDatabase();
        List<History> historyList = new ArrayList<>();

        Cursor cursor = db.query(Database.HistoryTable.TABLE_NAME, new String[]{
                Database.HistoryTable.COLUMN_ID,
                Database.HistoryTable.COLUMN_EXPRESSION,
                Database.HistoryTable.COLUMN_BASE,
                Database.HistoryTable.COLUMN_RESULT
            },
            null,
            null,
            null,
            null,
            null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.HistoryTable.COLUMN_ID));
            String expression =
                cursor.getString(cursor.getColumnIndexOrThrow(Database.HistoryTable.COLUMN_EXPRESSION));
            int base = cursor.getInt(cursor.getColumnIndexOrThrow(Database.HistoryTable.COLUMN_BASE));
            String result =
                cursor.getString(cursor.getColumnIndexOrThrow(Database.HistoryTable.COLUMN_RESULT));
            historyList.add(new History(id, expression, base, result));
        }

        cursor.close();
        db.close();

        return historyList;
    }

    @Override
    public void wipe() {
        SQLiteDatabase db = mDatabase.getWritableDatabase();
        db.delete(Database.HistoryTable.TABLE_NAME, null, null);
        db.close();
    }
}
