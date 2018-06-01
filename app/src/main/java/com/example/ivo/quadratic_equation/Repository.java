package com.example.ivo.quadratic_equation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ivo on 5/30/2018.
 */

public class Repository extends  Database {

    public Repository(Context context) {
        super(context);
    }

    public boolean AddData(Model model)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, model.a);
        contentValues.put(COL_3, model.b);
        contentValues.put(COL_4, model.c);
        contentValues.put(COL_5, model.d);
        contentValues.put(COL_6, model.x1);
        contentValues.put(COL_7, model.x2);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void DeleteAll()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }
}
