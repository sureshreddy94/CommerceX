package com.kryptoblocks.commercex.fragmentss;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptoblocks.commercex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobilesFragment extends Fragment {


    public MobilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobiles, container, false);
    }

}
