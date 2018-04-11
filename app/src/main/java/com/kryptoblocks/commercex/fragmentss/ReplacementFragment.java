package com.kryptoblocks.commercex.fragmentss;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptoblocks.commercex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplacementFragment extends Fragment {

    TextView return_order;


    public ReplacementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_replacement, container, false);



        return view;
    }

}
