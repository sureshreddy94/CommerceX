package com.kryptoblocks.commercex.fragmentss;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptoblocks.commercex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailsFragment extends Fragment {
    TextView return_order;

    public void ReplacementFragment()
    {
        ReplacementFragment replacementFragment = new ReplacementFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, replacementFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public OrderDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        return_order = view.findViewById(R.id.return_order);

        return_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplacementFragment();
            }
        });

        return view;
    }

}
