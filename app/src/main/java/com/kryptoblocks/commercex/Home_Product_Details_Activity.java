package com.kryptoblocks.commercex;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.commercex.adapterss.Home_Products_Adapter;
import com.kryptoblocks.commercex.fragmentss.FashionFragment;
import com.kryptoblocks.commercex.fragmentss.HomeFragment;
import com.kryptoblocks.commercex.fragmentss.OffersFragment;
import com.kryptoblocks.commercex.pojo.HomePro_Details;

import java.util.ArrayList;
import java.util.List;

public class Home_Product_Details_Activity extends AppCompatActivity {
    private RecyclerView home_prod_recycler;
    private Home_Products_Adapter adapter;
    private List<HomePro_Details> albumList;

    Toolbar tool_home_prod;
    ImageView close_img;
    TextView best_selling_tv;
    Button btn_sort, btn_filter;
    TabLayout tab_filter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__product__details_);

        tool_home_prod =  findViewById(R.id.toolbar_home_products);
        btn_sort =  findViewById(R.id.sort_button);
        btn_filter =  findViewById(R.id.filter_button);
       // tab_filter = findViewById(R.id.filter_tabLayout);

        home_prod_recycler = (RecyclerView)findViewById(R.id.home_items_recycler);
        albumList = new ArrayList<>();
        adapter = new Home_Products_Adapter(getApplication(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 2);
        home_prod_recycler.setLayoutManager(mLayoutManager);
        // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
        home_prod_recycler.setItemAnimator(new DefaultItemAnimator());
        home_prod_recycler.setAdapter(adapter);

        initToolBar();
        tool_home_prod.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tool_home_prod.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);
                    }
                }
        );

   /*   btn_sort.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              final AlertDialog.Builder filter_alert_builder = new AlertDialog.Builder(Home_Product_Details_Activity.this);
              LayoutInflater inflater = LayoutInflater.from(Home_Product_Details_Activity.this);

              final View alertView = inflater.inflate(R.layout.sort_by_layout, null);

              best_selling_tv = alertView.findViewById(R.id.best_selling_option);
              close_img = alertView.findViewById(R.id.close_cross_img);

              best_selling_tv.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent i = new Intent(getApplication(),MensClothingActivity.class);
                      startActivity(i);
                  }
              });




              close_img.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      filter_alert_builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                          @Override
                          public void onDismiss(DialogInterface dialog) {
                              Intent i = new Intent(Home_Product_Details_Activity.this,MainActivity.class);
                              startActivity(i);
                          }
                      });
                  }
              });

              filter_alert_builder.setView(alertView);
              filter_alert_builder.show();

          }
      });*/

   btn_sort.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           openBottomSheetForSort(v);
       }
   });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetForFilter(v);
            }
        });


            prepareProducts();
    }

    public void openBottomSheetForFilter (View v) {

        View view = getLayoutInflater ().inflate (R.layout.filter_layout, null);

       Button apply_filter_btn = view.findViewById( R.id.btn_apply_filter);
        tab_filter = view.findViewById(R.id.filter_tabLayout);
        close_img = view.findViewById(R.id.close_cross_img);
        /*TextView txtDetail = (TextView)view.findViewById( R.id.detail_tv);
        TextView txtOpen = (TextView)view.findViewById( R.id.open_tv);
        final TextView txtUninstall = (TextView)view.findViewById( R.id.uninstall_tv);*/

        final Dialog mBottomSheetDialog = new Dialog (Home_Product_Details_Activity.this,
                R.style.MaterialDialogSheet);

        ///////////////
        //Tablayout
//tab_filter.setTabGravity(TabLayout.GRAVITY_CENTER);
        tab_filter.setTabMode(TabLayout.MODE_SCROLLABLE);

        tab_filter.addTab(tab_filter.newTab().setText("Price"));
        tab_filter.addTab(tab_filter.newTab().setText("Brand"));
        tab_filter.addTab(tab_filter.newTab().setText("Discount"));
        tab_filter.addTab(tab_filter.newTab().setText("Size"));
        tab_filter.addTab(tab_filter.newTab().setText("Color"));
        tab_filter.addTab(tab_filter.newTab().setText("Material"));
        tab_filter.setTabGravity(TabLayout.GRAVITY_FILL);

        tab_filter.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new OffersFragment();
                        break;
                    case 1:
                        fragment = new FashionFragment();
                        break;
                    case 2:
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        fragment = new OffersFragment();
                        break;
                }
                /*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();*/

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
////////////////////////
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.setCancelable (true);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mBottomSheetDialog.show ();

        apply_filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Product_Details_Activity.this,"Clicked filter",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home_Product_Details_Activity.this,"Clicked filter",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }


    public void openBottomSheetForSort (View v) {

        View view = getLayoutInflater ().inflate (R.layout.sort_by_layout, null);

        TextView low_high = (TextView)view.findViewById( R.id.mens_clothing_main);
        close_img = view.findViewById(R.id.close_cross_img);

        /*TextView txtDetail = (TextView)view.findViewById( R.id.detail_tv);
        TextView txtOpen = (TextView)view.findViewById( R.id.open_tv);
        final TextView txtUninstall = (TextView)view.findViewById( R.id.uninstall_tv);*/

        final Dialog mBottomSheetDialog = new Dialog (Home_Product_Details_Activity.this,
                R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.setCancelable (true);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mBottomSheetDialog.show ();

        low_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent i = new Intent(getApplication(),MensClothingActivity.class);
                        startActivity(i);
            }
        });

        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home_Product_Details_Activity.this,"Clicked filter",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
       /* txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Clicked Detail",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
        txtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Clicked Open",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
        txtUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Clicked Uninstall",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });*/
    }

    private void prepareProducts() {
        int[] covers = new int[]{
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single,
                R.drawable.men_fashion_single};

        HomePro_Details a = new HomePro_Details("Bakasur", "80$", covers[0]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[1]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[2]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[3]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[4]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[5]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[6]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[7]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[8]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[9]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[10]);
        albumList.add(a);

        a = new HomePro_Details("Bakasur", "80$", covers[11]);
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
        tool_home_prod.setTitle(R.string.toolbar_for_home_products);
        tool_home_prod.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(tool_home_prod);

    }
}
