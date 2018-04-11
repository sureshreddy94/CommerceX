package com.kryptoblocks.commercex.fragmentss;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.kryptoblocks.commercex.Home_Product_Details_Activity;
import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.SliderActivity;


import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment implements BaseSliderView.OnSliderClickListener, TabLayout.OnTabSelectedListener {


    ViewPager pager_offers;
    SliderLayout imgslider;
    TabLayout tabb;
    LinearLayout linearLayout_lay_deals_day;

    public OffersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        // pager_offers = view.findViewById(R.id.offers_view_pager);
        imgslider = view.findViewById(R.id.slider);
        tabb = view.findViewById(R.id.tabLayout);
        linearLayout_lay_deals_day = view.findViewById(R.id.linear_layout_for_deals_of_the_day);

        //Tablayout
        tabb.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabb.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabb.addTab(tabb.newTab().setText("Offers"));
        tabb.addTab(tabb.newTab().setText("Mobiles"));
        tabb.addTab(tabb.newTab().setText("Fashion"));
        tabb.addTab(tabb.newTab().setText("Electronics"));
        tabb.addTab(tabb.newTab().setText("Home"));
        tabb.setTabGravity(TabLayout.GRAVITY_FILL);

        //Adding onTabSelectedListener to swipe views

       tabb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
               FragmentManager fm = getFragmentManager();
               FragmentTransaction ft = fm.beginTransaction();
               ft.replace(R.id.content_frame, fragment);
               ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
               ft.commit();

           }


           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

       linearLayout_lay_deals_day.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                //Home_Product_Details_Fragment();

              Intent i = new Intent(getContext(),Home_Product_Details_Activity.class);
               startActivity(i);

           }
       });

//ImageSlider
        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("a", R.drawable.ad1);
        file_maps.put("b", R.drawable.ad3);
        file_maps.put("c", R.drawable.ad2);
        file_maps.put("d", R.drawable.ad4);
        file_maps.put("e", R.drawable.ad5);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    //.setOnSliderClickListener(this);
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                            Intent intent = new Intent(getActivity(), SliderActivity.class);
                            // intent.putExtra("imageurls", urls.get(finalI));
                            startActivity(intent);

                        }
                    });

                imgslider.addSlider(textSliderView);
            }


            imgslider.setPresetTransformer(SliderLayout.Transformer.Fade);
            imgslider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
          //  imgslider.setCustomAnimation(new DescriptionAnimation());
            imgslider.setDuration(6000);


        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
