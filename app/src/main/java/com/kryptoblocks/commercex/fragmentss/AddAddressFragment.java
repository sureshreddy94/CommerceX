package com.kryptoblocks.commercex.fragmentss;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.payment.PaymentFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAddressFragment extends Fragment {

    Button btn_save_contine_add_address;

    public AddAddressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);


        btn_save_contine_add_address = view.findViewById(R.id.add_address_save_continue_button);

        btn_save_contine_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment();
            }
        });
        return view;
    }

}
