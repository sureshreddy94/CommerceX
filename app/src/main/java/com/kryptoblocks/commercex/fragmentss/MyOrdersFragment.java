package com.kryptoblocks.commercex.fragmentss;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.adapterss.OrderAdapter;
import com.kryptoblocks.commercex.pojo.OrderPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    private RecyclerView order_recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderPojo> orderList;


    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        order_recyclerView = view.findViewById(R.id.order_recycler_view);
        orderList = new ArrayList<>();
        orderAdapter = new   OrderAdapter(getContext(), orderList);



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        order_recyclerView.setLayoutManager(mLayoutManager);
        //order_recyclerView.addItemDecoration(new OrderActivity().GridSpacingItemDecoration(2, dpToPx(10), true));
        order_recyclerView.setItemAnimator(new DefaultItemAnimator());
        order_recyclerView.setAdapter(orderAdapter);

        orderAlbums();

        return view;
    }

    private void orderAlbums() {
        int[] covers = new int[]{
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji,
                R.drawable.bakasuji};

        OrderPojo a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18", covers[0]);
        orderList.add(a);

        a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18",covers[1]);
        orderList.add(a);

        a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18", covers[2]);
        orderList.add(a);

        a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18",covers[3]);
        orderList.add(a);

        a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18",covers[4]);
        orderList.add(a);

        a = new OrderPojo("Rare Casual 3/4th Sleeve Solid Women's Bron Top",".Delivered on Thu,Jan 10th 18", covers[5]);
        orderList.add(a);

        orderAdapter.notifyDataSetChanged();
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

}
