package com.example.tejas.hosteladmission;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Map;

public class Main5Activity extends AppCompatActivity  {
    RecyclerView listView;
    ArrayList<Data>arrayList;
    Firebase ref;
    StuAdapter stuAdapter;
    Data data;
    Bundle bundle;
    String ebr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView = (RecyclerView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        bundle=getIntent().getExtras();
        ebr=bundle.getString("BR");
        ref=new Firebase("https://hosteladmission-83032.firebaseio.com/Students/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()){
                    String prn= (String) childSnapShot.child("_prn").getValue();
                    String branch= (String) childSnapShot.child("_branch").getValue();
                    Double ptr=(Double) childSnapShot.child("_pointer").getValue();
                    String phno=(String) childSnapShot.child("_phone_number").getValue();
                    String cls=(String) childSnapShot.child("_presentclass").getValue();
                    data=new Data(prn,branch,phno,cls,ptr);
                    if(ebr.equals(data.get_branch()))
                        arrayList.add(data);
                }
                Collections.sort(arrayList, new Comparator<Data>() {
                    @Override
                    public int compare(Data data, Data t1) {
                        return Double.compare(data.get_pointer(),t1.get_pointer());
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        stuAdapter = new StuAdapter(this,arrayList);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(stuAdapter);
    }

}

