package com.example.androidfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends  SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table USERS(ID integer primary key autoincrement, NAME text, SURNAMES text, EMAIL text, PASSWORD text);");
        db.execSQL("create table EXPEDIENT(ID integer primary key autoincrement, REFERENCE text, FINISHED boolean, CONTENT text);");
        db.execSQL("create table USER_EXPEDIENT(USER_ID integer, EXPEDIENT_ID integer, primary key(USER_ID, EXPEDIENT_ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
