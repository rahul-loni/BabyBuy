package com.example.baby_buy.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DBNAME="items.db";
    public static final String TABLENAME="item";
    public static final int VER=1;
    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLENAME+"(id integer primary key , img blob,name text,price text,discreption text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query="drop table if exists "+TABLENAME+"";
       db.execSQL(query);

    }
}
