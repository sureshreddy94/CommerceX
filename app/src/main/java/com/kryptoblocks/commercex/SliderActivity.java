package com.kryptoblocks.commercex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kryptoblocks.commercex.adapterss.Realted_Products_Adapter;
import com.kryptoblocks.commercex.pojo.Related_Products;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {

    RecyclerView recycler_product;

    private List<Related_Products> related_products_items;
    Realted_Products_Adapter related_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        recycler_product = findViewById(R.id.activity_slider_recyler_view);

        recycler_product.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        related_products_items = new ArrayList<>();
        related_adapter = new Realted_Products_Adapter(getApplicationContext(), related_products_items);

       /* RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 1);
        recycler_product.setLayoutManager(mLayoutManager);*/
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycler_product.setItemAnimator(new DefaultItemAnimator());
        recycler_product.setHasFixedSize(true);
        recycler_product.setNestedScrollingEnabled(false);
        recycler_product.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));
        recycler_product.setAdapter(related_adapter);

        prepareValues();
    }

    private void prepareValues() {

        int[] covers = new int[]{
                R.drawable.sandal,
                R.drawable.sandal,
                R.drawable.sandal,
                R.drawable.sandal,
                R.drawable.sandal

        };

        Related_Products p = new Related_Products("High Heels1", covers[0]);
        related_products_items.add(p);

         p = new Related_Products("High Heels2", covers[1]);
        related_products_items.add(p);

         p = new Related_Products("High Heels3", covers[2]);
        related_products_items.add(p);

        p = new Related_Products("High Heels4", covers[3]);
        related_products_items.add(p);

        p = new Related_Products("High Heels5", covers[4]);
        related_products_items.add(p);
    }
}