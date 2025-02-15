package com.matheusvalbert.programmercalculator.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "programmer_calculator.db";
    private static final int DATABASE_VERSION = 1;

    public static class HistoryTable {
        public static final String TABLE_NAME = "results";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_EXPRESSION = "expression";
        public static final String COLUMN_BASE = "base";
        public static final String COLUMN_RESULT = "result";

        public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPRESSION + " TEXT NOT NULL, " +
                COLUMN_BASE + " INTEGER NOT NULL, " +
                COLUMN_RESULT + " TEXT NOT NULL)";
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HistoryTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryTable.TABLE_NAME);
        onCreate(db);
    }
}
