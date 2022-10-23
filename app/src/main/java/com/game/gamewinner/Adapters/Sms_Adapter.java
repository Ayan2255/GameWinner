package com.game.gamewinner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.game.gamewinner.R;

import java.util.ArrayList;

import Models.Event_model;
import Models.Sms_model;

public class Sms_Adapter extends RecyclerView.Adapter<Sms_Adapter.Holder>{
ArrayList<Sms_model>list;
Context context;

    public Sms_Adapter(ArrayList<Sms_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.event_list_demo,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Sms_model model=list.get(position);
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.game_date.setText(model.getGame_date());
        holder.game_time.setText(model.getGame_time());
        holder.sms.setText(model.getSms());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class Holder extends RecyclerView.ViewHolder{
     TextView date,name,game_date,game_time,sms;

        public Holder(@NonNull View itemView) {
            super(itemView);
            sms=itemView.findViewById(R.id.sms);
            date=itemView.findViewById(R.id.event_nf_cuurent_date);
            name=itemView.findViewById(R.id.event_name);
            game_date=itemView.findViewById(R.id.event_date);
            game_time=itemView.findViewById(R.id.event_time);
        }
    }
}