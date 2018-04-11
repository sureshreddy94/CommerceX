package com.kryptoblocks.commercex.adapterss;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.fragmentss.OrderDetailsFragment;
import com.kryptoblocks.commercex.pojo.OrderPojo;

import java.util.List;

/**
 * Created by Achu on 19-02-2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context mContext;
    private List<OrderPojo> orderPojos;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView product_discrp,product_date;
        public ImageView order_thumbnail;

        public MyViewHolder(View view) {
            super(view);
            product_discrp = view.findViewById(R.id.product_discrp);
            product_date = view.findViewById(R.id.product_date);
            order_thumbnail =  view.findViewById(R.id.order_thumbnail);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String pos = String.valueOf(getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            switch (pos) {
                case "0":

                    OrderDetailsFragment orderDetailsFragment = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFragment)
                            .addToBackStack(null).commit();
                                break;

                case "1":
                    OrderDetailsFragment orderDetailsFragmentt = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFragmentt)
                            .addToBackStack(null).commit();
                    break;

                case "2":
                    OrderDetailsFragment orderDetailsFragmen = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFragmen)
                            .addToBackStack(null).commit();
                    break;

                case "3":
                    OrderDetailsFragment orderDetailsFragme = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFragme)
                            .addToBackStack(null).commit();
                    break;

                case "4":
                    OrderDetailsFragment orderDetailsFragm = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFragm)
                            .addToBackStack(null).commit();
                    break;

                case "5":
                    OrderDetailsFragment orderDetailsFrag = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFrag)
                            .addToBackStack(null).commit();
                    break;

                case "6":
                    OrderDetailsFragment orderDetailsFra = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFra)
                            .addToBackStack(null).commit();
                    break;

                case "7":
                    OrderDetailsFragment orderDetailsFr = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsFr)
                            .addToBackStack(null).commit();
                    break;

                case "8":
                    OrderDetailsFragment orderDetailsF = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetailsF)
                            .addToBackStack(null).commit();
                    break;

                case "9":
                    OrderDetailsFragment orderDetails = new OrderDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, orderDetails)
                            .addToBackStack(null).commit();
                break;


                default:
                    System.out.println("Out of order");
            }
            Toast.makeText(mContext, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    public OrderAdapter(Context mContext, List<OrderPojo> orderPojos) {
        this.mContext = mContext;
        this.orderPojos = orderPojos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OrderPojo orderPojo = orderPojos.get(position);
        holder.product_discrp.setText(orderPojo.getName());
        holder.product_date.setText(orderPojo.getDevlivry_status());
        Glide.with(mContext).load(orderPojo.getThumbnail()).into(holder.order_thumbnail);
    }

    @Override
    public int getItemCount() {
        return orderPojos.size();
    }
}

