package com.kryptoblocks.commercex.adapterss;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.pojo.Album;

import java.util.List;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView product_title,product_cost,product_remove;
        public Button add_cart;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            product_title = view.findViewById(R.id.product_title);
            product_cost = view.findViewById(R.id.product_cost);
            product_remove =  view.findViewById(R.id.product_remove);
            add_cart =  view.findViewById(R.id.add_to_cart);
            thumbnail = view.findViewById(R.id.thumbnail);

            product_remove.setPaintFlags(product_remove.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String pos = String.valueOf(getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            switch (pos) {
               /* case "0":

                    EatOutFragment eatOutFragment = new EatOutFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, eatOutFragment)
                            .addToBackStack(null).commit();
                                break;

                case "1":
                    SpaFragment spaFragment = new SpaFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, spaFragment)
                            .addToBackStack(null).commit();
                    break;

                case "2":
                    ActiviesFragment activiesFragment = new ActiviesFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, activiesFragment)
                            .addToBackStack(null).commit();
                    break;

                case "3":
                    SalonFragment salonFragment = new SalonFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, salonFragment)
                            .addToBackStack(null).commit();
                    break;

                case "4":
                    MoviesFragment moviesFragment = new MoviesFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, moviesFragment)
                            .addToBackStack(null).commit();
                    break;

                case "5":
                    HotelFragment hotelFragment = new HotelFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, hotelFragment)
                            .addToBackStack(null).commit();
                    break;

                case "6":
                     FreeFragment freeFragment = new FreeFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, freeFragment)
                            .addToBackStack(null).commit();
                    break;

                case "7":
                    FitnessFragment fitnessFragment = new FitnessFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fitnessFragment)
                            .addToBackStack(null).commit();
                    break;

                case "8":
                    HealthFragment healthFragment = new HealthFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, healthFragment)
                            .addToBackStack(null).commit();
                    break;

                case "9":
                    ShoppingFragment shoppingFragment = new ShoppingFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, shoppingFragment)
                            .addToBackStack(null).commit();
                break;

                case "10":
                    HomeautoFragment homeautoFragment = new HomeautoFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, homeautoFragment)
                            .addToBackStack(null).commit();
                    break;

                case "11":
                    HobbiesFragment hobbiesFragment = new HobbiesFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, hobbiesFragment)
                            .addToBackStack(null).commit();
                    break;*/

                default:
                    System.out.println("Out of order");
            }
            Toast.makeText(mContext, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.product_title.setText(album.getName());
        holder.product_cost.setText(album.getCost());
        holder.product_remove.setText(album.getRemove());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


}
