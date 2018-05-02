package com.example.aryawirasandi.belariaapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aryawirasandi.belariaapp.R;
import com.example.aryawirasandi.belariaapp.Interface.ItemClickListener;
public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener ItemClickListener;


    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener ItemClickListener) {
        this.ItemClickListener = ItemClickListener;
    }

    @Override
    public void onClick(View view) {
        ItemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
