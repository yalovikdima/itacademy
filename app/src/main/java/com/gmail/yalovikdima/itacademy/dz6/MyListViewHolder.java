package com.gmail.yalovikdima.itacademy.dz6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.gmail.yalovikdima.itacademy.R;

public class MyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    ItemClickListener itemClickListener;

    public MyListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bind(Offer item, int position) {

        ImageLoaderUtill.clear(imageView);
        if (!TextUtils.isEmpty(item.getPicture())) {
            ImageLoaderUtill.loadImage(imageView, item.getPicture());
        } else {
            imageView.setImageDrawable(null);
        }

        if (item.getName() != null) {
            textView.setText(item.getName());
        } else {
            textView.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}