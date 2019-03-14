package com.gmail.yalovikdima.itacademy.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.yalovikdima.itacademy.R;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter  extends RecyclerView.Adapter<MyListViewHolder> {

    private List<Offer> list = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Offer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_offers, viewGroup, false);

        final MyListViewHolder holder = new MyListViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(listener != null){
                    listener.onClick(list.get(position), position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder myListViewHolder, int i) {


        myListViewHolder.bind(list.get(i), i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onClick(Offer item, int position);
    }
}
