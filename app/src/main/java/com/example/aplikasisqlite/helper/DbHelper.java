package com.example.aplikasisqlite.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    static final String Database_Name = "coba.db";
    private static final int Database_Version = 2;
    public static final String id_col = "id";
    public static final String name_col = "name";
    public static final String address_col = "address";

    public static final String TABLE_SQLite = "sqlite";

    public DbHelper(@Nullable Context context) {
        super(context, Database_Name , null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                id_col + " INTEGER PRIMARY KEY autoincrement, " +
                name_col + " TEXT NOT NULL, " +
                address_col + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(id_col, cursor.getString(0));
                map.put(name_col, cursor.getString(1));
                map.put(address_col, cursor.getString(2));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String name, String address){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (name, address) " +
                "VALUES ('" + name + "', '" + address + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String address){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + name_col + "='" + name + "', "
                + address_col + "='" + address + "'"
                + " WHERE " + id_col + "=" + "'" + id + "'";
        Log.e("upadte sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " where " + id_col + "=" + "'" + id + "'";
        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
