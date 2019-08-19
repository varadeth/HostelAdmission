package com.example.tejas.hosteladmission;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    RecyclerView listView;
    ArrayList<Data> arrayList;
    DatabaseHandler a;
    Data data;
    StuAdapter1 stuAdapter;
    private static final String TABLE_NAME="Students";  //Table name
    private static final String COLUMN_PRN="_PRN";
    private static final String COLUMN_BRANCH="_BRANCH";
    private static final String COLUMN_PHONENUMBER="_PHONE_NUMBER";
    private static final String COLUMN_PRESENT="_PRESENT_CLASS";
    private static final String COLUMN_POINTER="_POINTER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        a=new DatabaseHandler(getApplicationContext(),null);
        listView= (RecyclerView) findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        add();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        stuAdapter = new StuAdapter1(this,arrayList);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(stuAdapter);
    }
    public void add(){
        String prn,precls,branch,phone_number,sptr;
        Double ptr;
        SQLiteDatabase db=a.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME +" WHERE 1";
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex(COLUMN_PRN))!=null){
                prn=cursor.getString(cursor.getColumnIndex(COLUMN_PRN))+"    ";
                branch=cursor.getString(cursor.getColumnIndex(COLUMN_BRANCH))+"    ";
                precls=cursor.getString(cursor.getColumnIndex(COLUMN_PRESENT))+"    ";
                phone_number=cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER))+"    ";
                sptr=cursor.getString(cursor.getColumnIndex(COLUMN_POINTER))+"    ";
                ptr=Double.parseDouble(sptr);
                data = new Data(prn,branch,phone_number,precls,ptr);
                arrayList.add(data);
                cursor.moveToNext();
            }
        }
        db.close();
    }

}
