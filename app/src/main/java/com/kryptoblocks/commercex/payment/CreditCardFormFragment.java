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
import android.widget.TextView;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.kryptoblocks.commercex.R;


/**
 * created by suresh Reddy on 19-02-18
 */
public class CreditCardFormFragment extends Fragment {
    Toolbar toolbar;

    public CreditCardFormFragment() {
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

    public void PaymentSuccessFragment() {

        PaymentSuccessFragment paymentSuccessFragment = new PaymentSuccessFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, paymentSuccessFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_card_form, container, false);

        CardForm cardForm = view.findViewById(R.id.credit_card_form);
        toolbar = view.findViewById(R.id.toolbar_credit);
        TextView textDes = view.findViewById(R.id.payment_amount);
        Button btnPay = view.findViewById(R.id.btn_pay);


        textDes.setText("");
        btnPay.setText(String.format("payer %s", textDes.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                PaymentSuccessFragment();

            }
        });

        initToolBar();
        // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_bitcoin);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        toolbar.setTitle("Credit/Debit Card");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        //setSupportActionBar(toolbar_bitcoin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
