package com.example.tejas.hosteladmission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;    //Version of database   .If we change some attributes then increment the version
    private static final String DATABASE_NAME="hostel.db";  //Database name
    private static final String TABLE_NAME="Students";  //Table name

    //Column Names
    private static final String COLUMN_ID="_ID";
    private static final String COLUMN_PRN="_PRN";
    private static final String COLUMN_BRANCH="_BRANCH";
    private static final String COLUMN_PHONENUMBER="_PHONE_NUMBER";
    private static final String COLUMN_PRESENT="_PRESENT_CLASS";
    private static final String COLUMN_POINTER="_POINTER";


    public DatabaseHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE " + TABLE_NAME +"(" +
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                COLUMN_PRN+" TEXT , "+
                COLUMN_BRANCH+" TEXT , "+
                COLUMN_PHONENUMBER+" TEXT , "+
                COLUMN_PRESENT+" TEXT , "+
                COLUMN_POINTER+" REAL "+
        ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Add row to database
    public void addData(Data data){
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRN, data.get_prn());
            values.put(COLUMN_BRANCH, data.get_branch());
            values.put(COLUMN_PHONENUMBER, data.get_phone_number());
            values.put(COLUMN_PRESENT, data.get_presentclass());
            values.put(COLUMN_POINTER, data.get_pointer());

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_NAME, null, values);
            db.close();
    }

    public boolean compare(Data data) {
        String query="SELECT * FROM "+TABLE_NAME+" WHERE _PRN= \""+data.get_prn()+"\"";
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if (cursor.getCount()==0){
            return true;
        }
        return false;
    }

    public String printData(){
        String value="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME +" WHERE 1";
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex(COLUMN_PRN))!=null){
                value+=cursor.getString(cursor.getColumnIndex(COLUMN_PRN))+"    ";
                value+=cursor.getString(cursor.getColumnIndex(COLUMN_BRANCH))+"    ";
                value+=cursor.getString(cursor.getColumnIndex(COLUMN_PRESENT))+"    ";
                value+=cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER))+"    ";
                value+=cursor.getString(cursor.getColumnIndex(COLUMN_POINTER))+"    ";
                value+="\n";
                cursor.moveToNext();
            }
        }
        db.close();
        return value;
    }

}
