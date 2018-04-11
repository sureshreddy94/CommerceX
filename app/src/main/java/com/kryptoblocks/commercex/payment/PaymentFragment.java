package com.kryptoblocks.commercex.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.fragmentss.AddAddressFragment;


/**
 * created by suresh Reddy on 19-02-18
 */
public class PaymentFragment extends Fragment {
    Toolbar toolbar_payment;

    Button check;
    CardView wallet_card, reward_card, paypal_card, bitcoin_card, credit_debit_card, cod_card;


    public PaymentFragment() {
        // Required empty public constructor
    }


    public void ExodusWalletFragment() {

        ExodusWalletFragment exodusWalletFragment = new ExodusWalletFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, exodusWalletFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void CardFragment() {

        CardFragment cardFragment = new CardFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, cardFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void BitcoinFragment() {

        BitcoinFragment bitcoinFragment = new BitcoinFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, bitcoinFragment);
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

    public void PaypalFragment() {

        PaypalFragment paypalFragment = new PaypalFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, paypalFragment);
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

    public void StripeFragment() {

        StripeFragment stripeFragment = new StripeFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, stripeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void AddAddressFragment() {

        AddAddressFragment addAdressFragment = new AddAddressFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, addAdressFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        check = view.findViewById(R.id.checkout);
        wallet_card = view.findViewById(R.id.card_wallet);
        reward_card = view.findViewById(R.id.card_reward_points);
        paypal_card = view.findViewById(R.id.card_paypal);
        bitcoin_card = view.findViewById(R.id.card_bitcoin);
        credit_debit_card = view.findViewById(R.id.card_credit_debit);
        cod_card = view.findViewById(R.id.card_cod);
        toolbar_payment = view.findViewById(R.id.toolbar_payment);

        wallet_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent i = new Intent(getContext(),ExodusWalletFragment.class);
                startActivity(i);*/
                ExodusWalletFragment();
            }
        });

        reward_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RewardPointsFragment();
            }
        });

        paypal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaypalFragment();
            }
        });

        bitcoin_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitcoinFragment();
            }
        });

        credit_debit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardFragment();
            }
        });

        cod_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddCardFragment();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StripeFragment();
            }
        });

        initToolBar();
        // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_bitcoin);
        toolbar_payment.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar_payment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAddressFragment();
            }
        });


        return view;
    }

    public void initToolBar() {

        //  toolbar_bitcoin = findViewById(R.id.toolbar_bit);
        toolbar_payment.setTitle("Payment");
        toolbar_payment.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        //setSupportActionBar(toolbar_bitcoin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_payment);
    }
}
