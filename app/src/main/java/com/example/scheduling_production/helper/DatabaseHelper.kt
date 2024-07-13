package com.example.scheduling_production.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.scheduling_production.model.DataModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MyDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "myTable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_1 = "column1"
        private const val COLUMN_2 = "column2"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_1 TEXT,"
                + "$COLUMN_2 TEXT)")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addData(column1: String, column2: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_1, column1)
            put(COLUMN_2, column2)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllData(): List<DataModel> {
        val dataList = mutableListOf<DataModel>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val data = DataModel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    column1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_1)),
                    column2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_2))
                )
                dataList.add(data)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dataList
    }
}