package com.example.aryawirasandi.belariaapp.ViewHolder;

// ini adalah foodViewHolder dimana class ini berfungsi untuk pelemparan data pada kelas food

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aryawirasandi.belariaapp.Interface.itemClickListener;
import com.example.aryawirasandi.belariaapp.R;
public class foodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView food_name;
    public ImageView food_image;
    private itemClickListener ItemClickListener;

    public foodViewHolder(View itemView, itemClickListener itemClickListener) {
        super(itemView);
        ItemClickListener = itemClickListener;
    }

    public foodViewHolder(View itemView) {
        super(itemView);
        food_name = (TextView)itemView.findViewById(R.id.food_name);
        food_image = (ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }


    public itemClickListener getItemClickListener() {
        return ItemClickListener;
    }

    public void setItemClickListener(itemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        ItemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
