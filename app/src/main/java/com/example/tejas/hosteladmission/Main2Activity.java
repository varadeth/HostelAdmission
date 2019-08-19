package com.example.tejas.hosteladmission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    EditText tprn,tbr,tph,tp,tpre;
    Data data;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3;
    Button br;
    String precls;
    DatabaseHandler a;
    Firebase rootref;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tprn.setText("");
            tbr.setText("");
            tp.setText("");
            tph.setText("");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tprn=(EditText)findViewById(R.id.edit1);
        tbr=(EditText)findViewById(R.id.edit2);
        tph=(EditText)findViewById(R.id.edit3);
        tpre=(EditText)findViewById(R.id.precls);
        tp=(EditText)findViewById(R.id.edit4);
        tpre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    select(view);
            }
        });
        rootref=new Firebase("https://hosteladmission-83032.firebaseio.com/Students/");
        br=(Button)findViewById(R.id.button1);
        br.setOnClickListener(this);
        a=new DatabaseHandler(getApplicationContext(),null);
    }

    public void select(View view) {
        final CharSequence[] cls={"FY","SY","TY"};
        final AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Select Present Class");
        builder.setSingleChoiceItems(cls, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(cls[i]=="FY") {
                    precls="FY";
                }
                else if(cls[i]=="SY")
                    precls="SY";
                else
                    precls="TY";
                tpre.setText(precls);
                return;
            }
        });
        AlertDialog alert= builder.create();
        alert.show();
    }


    public void onClick(View view){
        String prn=tprn.getText().toString();
        String br=tbr.getText().toString();
        String phno=tph.getText().toString();
        String abc=tpre.getText().toString();
        String p=tp.getText().toString();
        if(prn.equals("")||br.equals("")||phno.equals("")||abc.equals("")||p.equals(""))
        {
            Toast.makeText(Main2Activity.this,"Please Fill Out All the Fields",Toast.LENGTH_SHORT).show();
            return;
        }
        double ptr=Double.parseDouble(tp.getText().toString());
        data=new Data(prn,br,phno,precls,ptr);
        final int[] f = {0};
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null&&connectivityManager.getActiveNetworkInfo().isAvailable()
                &&connectivityManager.getActiveNetworkInfo().isConnected()){
            if(search()==true)
                Toast.makeText(Main2Activity.this,"Already Present",Toast.LENGTH_SHORT).show();
            else
                rootref.child(data.get_prn()).setValue(data);
        }
        else{
            if(a.compare(data)) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        a.addData(data);
                    }
                };
                Thread th1 = new Thread(runnable);
                th1.start();
                try {
                    th1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
                Toast.makeText(Main2Activity.this, "Successfully Registered Your Name", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Main2Activity.this, "Already Registered Your Name", Toast.LENGTH_SHORT).show();
            }
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private boolean search() {
        final String[] s = {""};
        final int[] f = {0};
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ch : dataSnapshot.getChildren())
                {
                    s[0] =(String)ch.child("_prn").getValue();
                    if(s[0].equals(data.get_prn())) {
                        f[0] =1;
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        if(f[0]==1)
            return true;
        return false;
    }
}
