package com.skthakur.mobileprogramminglab.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private static final String TAG = DatabaseOperations.class.getSimpleName();
    private DatabaseHelper dbHelper;

    public DatabaseOperations(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertData(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);
        Log.d(TAG, "Inserted row id: " + newRowId + "Data:" + name);
        //db.close();
    }

    public List<NoteModel> getAllData() {
        List<NoteModel> dataList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                Log.d(TAG, "Cursor Data - ID = " + id + ", Name = " + name);
                NoteModel dataModel = new NoteModel(id, name);
                dataList.add(dataModel);
                Log.d(TAG, "getAllData: Data - ID = " + dataModel.getNoteId() + ", Name = " + dataModel.getNote());
            } while (cursor.moveToNext());
            cursor.close();
        }
        //db.close();
        return dataList;
    }

    public void updateData(String newName, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, newName);
        int rowsAffected = db.update(DatabaseHelper.TABLE_NAME, values,
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        Log.d(TAG, "Rows affected: " + rowsAffected);
        //db.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        Log.d(TAG, "Rows affected: " + rowsAffected);
        //db.close();
    }
}

