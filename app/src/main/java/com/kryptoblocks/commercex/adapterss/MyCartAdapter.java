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
import com.kryptoblocks.commercex.fragmentss.AddAddressFragment;
import com.kryptoblocks.commercex.pojo.MyCart;

import java.util.List;

/**
 * Created by Admin on 21-02-2018.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context mContext;
    private List<MyCart> myCart;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mycart_discrp,mycart_date,qwty;
        public ImageView mycart_thumbnail,plus,minu;
        int value = 0;

        public MyViewHolder(View view) {
            super(view);
            mycart_discrp = view.findViewById(R.id.mycart_discrp);
            mycart_date = view.findViewById(R.id.mycart_date);
            plus = view.findViewById(R.id.add);
            minu = view.findViewById(R.id.minus);
            qwty = view.findViewById(R.id.qty);
            mycart_thumbnail =  view.findViewById(R.id.mycart_thumbnail);


            view.setOnClickListener(this);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    value = value + 1;
                    qwty.setText(Integer.toString(value));
                }
            });

            minu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CharSequence t = qwty.getText().toString();
                    int num1 =  Integer.parseInt(t.toString());
                    int a = num1 - 1;
                    qwty.setText(Integer.toString(a));
                }
            });
        }

        @Override
        public void onClick(View view) {
            String pos = String.valueOf(getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            switch (pos) {
                case "0":

                    AddAddressFragment addAddressFragment = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFragment)
                            .addToBackStack(null).commit();
                                break;

                case "1":
                    AddAddressFragment addAddressFragmen = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFragmen)
                            .addToBackStack(null).commit();
                    break;

                case "2":
                    AddAddressFragment addAddressFragme = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFragme)
                            .addToBackStack(null).commit();
                    break;

                case "3":
                    AddAddressFragment addAddressFragm = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFragm)
                            .addToBackStack(null).commit();
                    break;

                case "4":
                    AddAddressFragment addAddressFrag = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFrag)
                            .addToBackStack(null).commit();
                    break;

                case "5":
                    AddAddressFragment addAddressFra = new AddAddressFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, addAddressFra)
                            .addToBackStack(null).commit();
                    break;

                default:
                    System.out.println("Out of order");
            }
            Toast.makeText(mContext, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    public MyCartAdapter(Context mContext, List<MyCart> myCart) {
        this.mContext = mContext;
        this.myCart = myCart;
    }

    @Override
    public MyCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycart_card, parent, false);

        return new MyCartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyCartAdapter.MyViewHolder holder, int position) {
        MyCart myCarts = myCart.get(position);
        holder.mycart_discrp.setText(myCarts.getName());
        holder.mycart_date.setText(myCarts.getDevlivry_status());
        Glide.with(mContext).load(myCarts.getThumbnail()).into(holder.mycart_thumbnail);
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

}

