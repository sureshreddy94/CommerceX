package com.kryptoblocks.commercex;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.kryptoblocks.commercex.adapterss.MyCartAdapter;
import com.kryptoblocks.commercex.fragmentss.AddAddressFragment;
import com.kryptoblocks.commercex.pojo.MyCart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Button cont;
    private RecyclerView mycart_recyclerView;
    private MyCartAdapter myCartAdapter;
    private List<MyCart> myCartList;
    Toolbar mycart_toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mycart_toolbar = findViewById(R.id.toolbar_mycart);
        cont = findViewById(R.id.cart_continue);

        mycart_recyclerView = findViewById(R.id.mycart_recycler_view);
        myCartList = new ArrayList<>();
        myCartAdapter = new   MyCartAdapter(getApplication(), myCartList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 1);
        mycart_recyclerView.setLayoutManager(mLayoutManager);
        //order_recyclerView.addItemDecoration(new OrderActivity().GridSpacingItemDecoration(2, dpToPx(10), true));
        mycart_recyclerView.setItemAnimator(new DefaultItemAnimator());
        mycart_recyclerView.setAdapter(myCartAdapter);

        initToolBar();
        mycart_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, AddAdressActivity.class);
                startActivity(i);

            }
        });
        mycart_toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(CartActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }
        );


        MyCartAlbums();


            }

    private void MyCartAlbums() {
        int[] covers = new int[]{
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji};

        MyCart a = new MyCart("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18", covers[0]);
        myCartList.add(a);

        a = new MyCart("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18",covers[1]);
        myCartList.add(a);

        a = new MyCart("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18", covers[2]);
        myCartList.add(a);

        a = new MyCart("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18",covers[3]);
        myCartList.add(a);

        myCartAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initToolBar() {
        //wishlist_toolbar = (Toolbar) findViewById(R.id.toolbar_mens_clothing);
        mycart_toolbar.setTitle(R.string.toolbar_for_mycart);
        mycart_toolbar.setTitleTextColor(0xFFFFFFFF);
       setSupportActionBar(mycart_toolbar);

    }
    }

