package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database1";
    private static final String TABLE_NAME = "Table1";
    private static final String TABLE_NAME2 = "Table2";
    private static final String TABLE_NAME3 = "Favorites";
    private static final String TABLE_NAME4 = "Blocks";

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TABLE_NAME + "(Name TEXT PRIMARY KEY, Open TEXT, Close TEXT, Dis TEXT)";
        String createTable2 = "create table " + TABLE_NAME2 + "(id INTEGER PRIMARY KEY AUTOINCREMENT, Rating TEXT, Comment TEXT, PTable TEXT, FOREIGN KEY(PTable) REFERENCES Table1(Name))";
        String createTable3 = "create table " + TABLE_NAME3 + "(Name TEXT PRIMARY KEY)";
        String createTable4 = "create table " + TABLE_NAME4 + "(Name TEXT PRIMARY KEY)";
        db.execSQL(createTable);
        db.execSQL(createTable2);
        db.execSQL(createTable3);
        db.execSQL(createTable4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME4);
        onCreate(db);
    }

    public boolean addText(String NAME, String OPEN, String CLOSE, String DIS){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",NAME);
        contentValues.put("Open",OPEN);
        contentValues.put("Close",CLOSE);
        contentValues.put("Dis",DIS);
        db.insert(TABLE_NAME,null,contentValues);
        return true;
    }

    public boolean addReview(String rate, String com, String Place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Rating",rate);
        contentValues.put("Comment",com);
        contentValues.put("PTable",Place);
        db.insert(TABLE_NAME2,null,contentValues);
        return true;
    }

    public boolean addFavorites(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",NAME);
        db.insert(TABLE_NAME3,null,contentValues);
        return true;
    }

    public boolean addBlocks(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",NAME);
        db.insert(TABLE_NAME4,null,contentValues);
        return true;
    }

    public ArrayList getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getAllFavorites(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from  Favorites",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getAllBlocks(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from Blocks",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return arrayList;
    }
}
