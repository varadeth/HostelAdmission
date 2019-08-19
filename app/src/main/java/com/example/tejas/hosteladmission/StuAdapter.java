package com.example.tejas.hosteladmission;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tejas on 04-09-2017.
 */

public class StuAdapter extends RecyclerView.Adapter<StuAdapter.MyViewHolder> {
    Context context;
    private List<Data> list;
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        private TextView tprn,tbr,tphno,tprecls,tptr;
        public MyViewHolder(View view) {
            super(view);
            tprn = (TextView) view.findViewById(R.id.cardtext1);
            tbr = (TextView) view.findViewById(R.id.cardtext2);
            tphno = (TextView) view.findViewById(R.id.cardtext3);
            tprecls = (TextView) view.findViewById(R.id.cardtext4);
            tptr = (TextView)view.findViewById(R.id.cardtext5);
        }
        public boolean onLongClick(View view) {
            int pos=getAdapterPosition();
            Data data=list.get(pos);
            Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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

    public StuAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.x,parent,false);
        return new MyViewHolder(view);
    }
}
