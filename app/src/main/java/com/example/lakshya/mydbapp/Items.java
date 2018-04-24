package com.example.lakshya.mydbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;

public class Items extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "COLLEGE_DATABASE.db";
    public static final String TABLE_NAME = "college_table";
    public static final String COLUMN_1 = "ROLL_NO";
    public static final String COLUMN_2 ="NAME";
    public static final String COLUMN_3 ="COURSE";
    public static final String COLUMN_4 ="BRANCH";


    public Items(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table " + TABLE_NAME + " (ROLL_NO TEXT PRIMARY KEY AUTOINCREMENT,NAME TEXT,COURSE TEXT,BRANCH TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String course,String branch,String roll_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cnt =new ContentValues();
        cnt.put(COLUMN_1,roll_no);
        cnt.put(COLUMN_2,name);
        cnt.put(COLUMN_3,course);
        cnt.put(COLUMN_4,branch);
        long result =db.insert(TABLE_NAME,null,cnt);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(query, null);
        return res;

    }
    public boolean updateData(String name,String course,String branch,String roll_no){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues catv = new ContentValues();
        catv.put(COLUMN_1,roll_no);
        catv.put(COLUMN_2,name);
        catv.put(COLUMN_3,course);
        catv.put(COLUMN_4,branch);
            db.update(TABLE_NAME,catv,"ROLL_NO= ?",new String[] { roll_no });
                return true;
    }
    public Integer deleteData(String roll_no){
        SQLiteDatabase db =this.getWritableDatabase();
       return db.delete(TABLE_NAME,"ROLL_NO = ?",new String[] {roll_no});

    }
}
