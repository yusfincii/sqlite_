package com.example.sqlitedatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "guide", null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE person(" +
                "person_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "person_name TEXT," +
                "person_age INTEGER," +
                "person_price DOUBLE);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS person")
        onCreate(db)
    }

}