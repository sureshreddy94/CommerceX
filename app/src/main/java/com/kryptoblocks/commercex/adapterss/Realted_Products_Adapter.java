package com.kryptoblocks.commercex.adapterss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.pojo.Related_Products;

import java.util.List;

/**
 * Created by Admin on 20-02-2018.
 */

public class Realted_Products_Adapter extends RecyclerView.Adapter<Realted_Products_Adapter.MyViewHolder> {

    private Context mContext;
    private List<Related_Products> products_lists;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.related_prod_name);
            image =  view.findViewById(R.id.related_prod_image);

        }
    }

    public Realted_Products_Adapter(Context mContext, List<Related_Products> prod_lists) {
        this.mContext = mContext;
        this.products_lists = prod_lists;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_slider_related_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Realted_Products_Adapter.MyViewHolder holder, int position) {
        Related_Products lis = products_lists.get(position);
        Glide.with(mContext).load(lis.getProduct_image()).into(holder.image);
        holder.name.setText(String.valueOf(lis.getProduct_name()));

    }


    @Override
    public int getItemCount() {

        return products_lists.size();

    }
}
