package com.example.myapplication.module;

import android.provider.BaseColumns;

/**
 * Created by jiechao on 6/21/16.
 */
public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DatabaseContract() {}

    public static final String DB_NAME = "My Database";

    /* Inner class that defines the table contents */
    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Students";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_GRADE = "Grade";
    }
}

