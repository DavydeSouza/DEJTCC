package com.example.dejtcc.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dejtcc.R;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<Model> datalist;

    public myadapter(ArrayList<Model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getComida());
        holder.t2.setText(datalist.get(position).getTipo());
       // holder.t3.setText(datalist.get(position).getDescricao());
       // holder.t4.setText(datalist.get(position).getPreco());
       // holder.t5.setText(datalist.get(position).getEstoque());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
           // t3=itemView.findViewById(R.id.t3);
           // t4=itemView.findViewById(R.id.t4);
           // t5=itemView.findViewById(R.id.t5);
        }
    }
}
