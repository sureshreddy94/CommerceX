package com.kryptoblocks.commercex;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kryptoblocks.commercex.fragmentss.AddAddressFragment;

public class AddAdressActivity extends AppCompatActivity {

    public void AddAddressFragment() {

        AddAddressFragment addAddressFragment = new AddAddressFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, addAddressFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        AddAddressFragment();
    }
}
