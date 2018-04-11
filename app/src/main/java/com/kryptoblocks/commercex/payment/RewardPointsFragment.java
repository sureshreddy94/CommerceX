package com.kryptoblocks.commercex.payment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kryptoblocks.commercex.R;

import java.util.ArrayList;


/**
 * created by suresh Reddy on 19-02-18
 */
public class RewardPointsFragment extends Fragment {

    ImageView reward_coin, reward_back_btn;
    Toolbar toolbar_reward;

    public RewardPointsFragment() {
        // Required empty public constructor
    }

    public void PaymentFragment() {

        PaymentFragment paymentFragment = new PaymentFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, paymentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void RewardPointsFragment() {

        RewardPointsFragment rewardPointsFragment = new RewardPointsFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, rewardPointsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward_points, container, false);
        reward_coin = view.findViewById(R.id.reward_coin);

        PieChart pieChart = view.findViewById(R.id.piechart);


        reward_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder reward_alert_builder = new AlertDialog.Builder(getContext());
                //LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View alertView = inflater.inflate(R.layout.reward_points_dialog, null);
                reward_back_btn = alertView.findViewById(R.id.reward_back_btn);

                final AlertDialog dialog = reward_alert_builder.create();

                reward_alert_builder.setView(alertView);
                reward_alert_builder.show();


                reward_back_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });


        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(1f, 0));
        yvalues.add(new Entry(25f, 1));
        yvalues.add(new Entry(25f, 2));
        yvalues.add(new Entry(25f, 3));


        PieDataSet dataSet = new PieDataSet(yvalues, "");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");


        PieData data = new PieData(xVals, dataSet);
        //data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDescription("");
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        //enable hole of pie chart

        pieChart.setDrawHoleEnabled(true);
        pieChart.setCenterText("1000 Points");
        pieChart.setCenterTextSize(13f);

        pieChart.setTransparentCircleRadius(5f);
        pieChart.setHoleRadius(75f);

        //set text size and color
        data.setValueTextSize(5f);
        data.setValueTextColor(Color.DKGRAY);
        // pieChart.setOnChartValueSelectedListener();
        toolbar_reward = view.findViewById(R.id.toolbar_reward);

        initToolBar();
        // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_bitcoin);
        toolbar_reward.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar_reward.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment();
            }
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initToolBar() {

        //  toolbar_bitcoin = findViewById(R.id.toolbar_bit);
        toolbar_reward.setTitle("Reward Points");
        toolbar_reward.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        //setSupportActionBar(toolbar_bitcoin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_reward);

    }
}
