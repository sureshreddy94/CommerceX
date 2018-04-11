package com.kryptoblocks.commercex.payment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kryptoblocks.commercex.R;


/**
 * created by suresh Reddy on19-02-18
 */
public class AddCardFragment extends Fragment {

    private static final String[] mm_Paths = {"MM", "01", "02", "03", "04", "05", "06", "07", "08", "09",
            "10", "11", "12"};
    private static final String[] year_Paths = {"YYYY", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011",
            "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ImageView back_button;
    Button save_btn;
    Spinner mm, yyyy;
    private String monthString = null;
    private String yearString = null;

    public AddCardFragment() {
        // Required empty public constructor
    }

    public void CardFragment() {

        CardFragment cardFragment = new CardFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, cardFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_card, container, false);

        back_button = view.findViewById(R.id.add_card_bckbtn);
        save_btn = view.findViewById(R.id.save_card);
        mm = view.findViewById(R.id.spinner_mm);
        yyyy = view.findViewById(R.id.spinner_year);

        ArrayAdapter<String> mnthAdapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_spinner_item, mm_Paths);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_spinner_item, year_Paths);

        mnthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        mm.setAdapter(mnthAdapter);
        yyyy.setAdapter(yearAdapter);


        mm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthString = parent.getItemAtPosition(position).toString();

                ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yyyy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearString = parent.getItemAtPosition(position).toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardFragment();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardFragment();
            }
        });
        return view;
    }

}
