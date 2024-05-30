package com.example.shopping_list.data

import android.provider.BaseColumns

object DbNameClass {
    const val TABLE_NAME = "shop_list"
    const val COLUMN_NAME_CONTENT = "content"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "database.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_CONTENT TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}