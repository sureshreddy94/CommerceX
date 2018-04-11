package com.kryptoblocks.commercex.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptoblocks.commercex.R;


/**
 * created by suresh Reddy on 19-02-18
 */
public class PaymentSuccessFragment extends Fragment {

    Toolbar toolbar_bitcoin;

    public PaymentSuccessFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_success, container, false);
        toolbar_bitcoin = view.findViewById(R.id.toolbar_bitt);

        initToolBar();
        // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_bitcoin);
        toolbar_bitcoin.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar_bitcoin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment();
            }
        });

        return view;


    }

    public void initToolBar() {

        //  toolbar_bitcoin = findViewById(R.id.toolbar_bit);
        toolbar_bitcoin.setTitle(R.string.toolbar_for_bitcoin);
        toolbar_bitcoin.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        //setSupportActionBar(toolbar_bitcoin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_bitcoin);
    }
}
