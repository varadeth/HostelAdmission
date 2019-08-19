package com.example.tejas.hosteladmission;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tejas on 08-09-2017.
 */

public class StuAdapter1 extends RecyclerView.Adapter<StuAdapter1.MyViewHolder1> {
    private Context context;
    private List<Data> list;
    class MyViewHolder1 extends RecyclerView.ViewHolder {
        private TextView tprn,tbr,tphno,tprecls,tptr;
        public MyViewHolder1(View view) {
            super(view);
            tprn = (TextView) view.findViewById(R.id.cardtext1);
            tbr = (TextView) view.findViewById(R.id.cardtext2);
            tphno = (TextView) view.findViewById(R.id.cardtext3);
            tprecls = (TextView) view.findViewById(R.id.cardtext4);
            tptr = (TextView)view.findViewById(R.id.cardtext5);
        }


    }


    @Override
    public void onBindViewHolder(StuAdapter1.MyViewHolder1 holder, int position) {
        Data data=list.get(position);
        Double ptr=data.get_pointer();
        String p=ptr.toString();
        holder.tptr.setText(p);
        holder.tprecls.setText(data.get_presentclass());
        holder.tphno.setText(data.get_phone_number());
        holder.tprn.setText(data.get_prn());
        holder.tbr.setText(data.get_branch());
    }

    public int getItemCount() {
        return list.size();
    }

    public StuAdapter1(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public StuAdapter1.MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.y,parent,false);
        return new StuAdapter1.MyViewHolder1(view);
    }
}
