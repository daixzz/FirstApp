package com.zhen.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    private DBhelper dbhelper;
    private String TBNAME;


    public  RateManager(Context context){
        dbhelper = new DBhelper(context);
        TBNAME = DBhelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void addAll(List<RateItem> list){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        for (RateItem item :list){
            ContentValues values = new ContentValues();
            values.put("curname",item.getCurName());
            values.put("currate",item.getCurRate());
            db.insert(TBNAME,null,values);
        }
        db.close();
    }
    public List<RateItem> listAll(){
        List<RateItem> ratelist = null;
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if (cursor != null){
            ratelist = new ArrayList<RateItem>();
            while(cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));

                item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                ratelist.add(item);

            }
            cursor.close();
        }
        db.close();
        return ratelist;
    }
}
