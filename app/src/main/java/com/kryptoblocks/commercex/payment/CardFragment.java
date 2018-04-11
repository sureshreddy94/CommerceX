package com.kryptoblocks.commercex.payment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.kryptoblocks.commercex.R;


/**
 * created by suresh Reddy on 19-02-18
 */
public class CardFragment extends Fragment {

    ImageView back_button, ctrl_btn;
    Button card_check_out;
    Toolbar card_toolbarr;


    public CardFragment() {
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

    public void AddCardFragment() {

        AddCardFragment addCardFragment = new AddCardFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, addCardFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void PaymentSuccessFragment() {

        PaymentSuccessFragment paymentSuccessFragment = new PaymentSuccessFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, paymentSuccessFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void CreditCardFormFragment() {

        CreditCardFormFragment creditCardFormFragment = new CreditCardFormFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, creditCardFormFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        //  back_button = view.findViewById(R.id.credit_back_button);
        card_toolbarr = view.findViewById(R.id.toolbar_card);
        card_check_out = view.findViewById(R.id.card_checkout);
        ctrl_btn = view.findViewById(R.id.ctrl_btn);
        ctrl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddCardFragment();
                CreditCardFormFragment();
            }
        });


        card_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentSuccessFragment();
            }
        });
        initToolBar();
        card_toolbarr.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        card_toolbarr.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment();
            }
        });


        return view;
    }


    public void initToolBar() {

        //  toolbar_bitcoin = findViewById(R.id.toolbar_bit);
        card_toolbarr.setTitle(R.string.toolbar_for_card);
        card_toolbarr.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        //setSupportActionBar(toolbar_bitcoin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(card_toolbarr);
    }
}
