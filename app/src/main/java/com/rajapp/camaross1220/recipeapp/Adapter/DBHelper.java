package com.rajapp.camaross1220.recipeapp.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "recipe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table recipe_details(id INTEGER PRIMARY KEY AUTOINCREMENT,image_url TEXT,name TEXT, description TEXT," +
                "ingredients TEXT,instruction TEXT, type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists recipe_details");
        onCreate(sqLiteDatabase);
    }
}
