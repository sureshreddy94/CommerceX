package com.kryptoblocks.commercex.fragmentss;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kryptoblocks.commercex.MainActivity;
import com.kryptoblocks.commercex.MensClothingActivity;
import com.kryptoblocks.commercex.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    CardView card_men_fashion, card_women_fashion, card_kids_fashion;
    TextView clothing_men;
    Context mContext;
    private PopupWindow mPopupWindow;


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_categories, container, false);

        card_men_fashion = view.findViewById(R.id.categories_men_fashion_card_view);
        card_women_fashion = view.findViewById(R.id.categories_women_fashion_card_view);
        card_kids_fashion = view.findViewById(R.id.categories_kids_fashion_card_view);

       // mContext = getContext();

        card_men_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final AlertDialog.Builder men_alert_builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(getActivity());

                View alertView = inflater.inflate(R.layout.mens_fashion_layout, null);

                clothing_men = alertView.findViewById(R.id.mens_clothing_main);

                clothing_men.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(),MensClothingActivity.class);
                        startActivity(i);
                    }
                });

                //ImageButton close_send_request = alertView.findViewById(R.id.close_image_button_payment);
              /*  mPopupWindow = new PopupWindow(
                        alertView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT

                );*/

               /* mPopupWindow.setContentView(view);
                mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                */
                //mPopupWindow.setFocusable(true);
                //mPopupWindow.showAtLocation(alertView, Gravity.CENTER,0,0);

                men_alert_builder.setView(alertView);

                men_alert_builder.show();
            }
        });

        MainActivity actCategories = (MainActivity) getActivity();
        actCategories.setTitle("Categories");

        return view;
    }

}
