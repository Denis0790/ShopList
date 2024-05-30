package com.example.shopping_list.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.shopping_list.InfoInList


class DbManager(context: Context) {
    private val dbHelper = DbHelper(context)
    private var db:SQLiteDatabase? = null

    fun openDb(){
        db = dbHelper.writableDatabase
    }

    fun insertToDb(infoInList: InfoInList){
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME_CONTENT, infoInList.content)
        }
        db?.insert(DbNameClass.TABLE_NAME, null, values)
    }

    fun deleteItem(id:String){
        //val selection = BaseColumns._ID + "=$id"
        db?.delete(DbNameClass.TABLE_NAME, "${DbNameClass.COLUMN_NAME_CONTENT} = ?", arrayOf(id))
    }

    @SuppressLint("Recycle", "Range")
    fun readDb():ArrayList<String>{
        val dbList = ArrayList<String>()

        val cursor = db?.query(DbNameClass.TABLE_NAME, null, null, null,null,null,null)
        while (cursor?.moveToNext()!!){
            val dataNext = cursor.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_CONTENT))
            dbList.add(dataNext)
        }
        cursor.close()
        return dbList
    }

    fun closeDb(){
        db?.close()
    }
}