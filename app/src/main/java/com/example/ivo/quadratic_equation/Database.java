package com.example.ivo.quadratic_equation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "QuadraficEquation.db";
    public static final String TABLE_NAME = "Information";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "A";
    public static final String COL_3 = "B";
    public static final String COL_4 = "C";
    public static final String COL_5 = "D";
    public static final String COL_6 = "X1";
    public static final String COL_7 = "X2";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, A TEXT, B TEXT, C TEXT, D TEXT, X1 TEXT, X2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db );
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
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}