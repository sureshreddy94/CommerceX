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

import com.kryptoblocks.commercex.adapterss.AlbumsAdapter;
import com.kryptoblocks.commercex.pojo.Album;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    Toolbar wishlist_toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishlist_toolbar = findViewById(R.id.toolbar_wishlist);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new   AlbumsAdapter(getApplication(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        initToolBar();
        wishlist_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        wishlist_toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);
                    }
                }
        );

        prepareAlbums();
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji};

        Album a = new Album("Bakasur","80$","Remove", covers[0]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove",covers[1]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove", covers[2]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove",covers[3]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove", covers[4]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove", covers[5]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove", covers[6]);
        albumList.add(a);

        a = new Album("Bakasur","80$","Remove", covers[7]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
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
        wishlist_toolbar.setTitle(R.string.toolbar_for_wishlist);
        wishlist_toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(wishlist_toolbar);

    }
}
