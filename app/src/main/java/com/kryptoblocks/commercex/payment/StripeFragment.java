package com.kryptoblocks.commercex.payment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kryptoblocks.commercex.R;
import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * created by suresh Reddy on 19-02-18
 */
public class StripeFragment extends Fragment {


    public static final String PUBLISHABLE_KEY = "pk_test_pRx31Z9fVQdBfNTshAZ2lYSS";
    public static final String APPLICATION_ID = "B2CDnBIwR8HQzf3FbqoA5iI135wuBkNTFuLdMVd5";
    public static final String CLIENT_KEY = "OQx5RBE6EhTGz1qabKw5YKRAyjHfwYkPiVtbhPVf";
    public static final String BACK4PAPP_API = "https://parseapi.back4app.com/";
    ImageView back_btn;
    Token tok;
    private Button pay;
    private Card card;
    private ProgressDialog progress;

    public StripeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_stripe, container, false);
        // pay = view.findViewById(R.id.proceed_to_pay);
        back_btn = view.findViewById(R.id.stripe_backbtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFragment();
            }
        });


        // Connect to Your Back4app Account
        Parse.initialize(new Parse.Configuration.Builder(getContext())
                .applicationId(APPLICATION_ID)
                .clientKey(CLIENT_KEY)
                .server(BACK4PAPP_API).build());
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);

        // Create a demo test credit Card
        // You can pass the payment form data to create a Real Credit card
        // But you need to implement youself.

        card = new Card(
                "4242424242424242", //card number
                12, //expMonth
                2022,//expYear
                "123"//cvc
        );


       /* CardInputWidget mCardInputWidget = view.findViewById(R.id.card_input_widget);

        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
            //mErrorDialogHandler.showError("Invalid Card Data");
        }
        cardToSave.setName("Customer Name");
        cardToSave.setAddressZip("12345");*/


        progress = new ProgressDialog(getContext());
        pay = view.findViewById(R.id.proceed_to_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });
        return view;
    }

    private void buy() {
        boolean validation = card.validateCard();
        if (validation) {
            startProgress("Validating Credit Card");
            new Stripe(getContext()).createToken(
                    card,
                    PUBLISHABLE_KEY,
                    new TokenCallback() {
                        @Override
                        public void onError(Exception error) {
                            Log.d("Stripe", error.toString());
                        }

                        @Override
                        public void onSuccess(Token token) {
                            finishProgress();
                            stripePay();
                            charge(token);
                        }
                    });
        } else if (!card.validateNumber()) {
            Log.d("Stripe", "The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            Log.d("Stripe", "The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            Log.d("Stripe", "The CVC code that you entered is invalid");
        } else {
            Log.d("Stripe", "The card details that you entered are invalid");
        }
    }

    private void charge(Token cardToken) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("itemName", "test");
        params.put("cardToken", cardToken.getId());
        params.put("name", "Akash");
        params.put("address", "Sacramento");
        params.put("email", "akash.labs@gmail.com");
        params.put("zip", "99999");
        params.put("city_state", "US");
        startProgress("Purchasing Item");
        ParseCloud.callFunctionInBackground("purchaseItem", params, new FunctionCallback<Object>() {
            public void done(Object response, ParseException e) {
                finishProgress();
                if (e == null) {
                    Log.d("Cloud Response", "There were no exceptions! " + response.toString());
                    Toast.makeText(getContext(),
                            "Item Purchased Successfully ",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.d("Cloud Response", "Exception: " + e);
                    Toast.makeText(getContext(),
                            e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startProgress(String title) {
        progress.setTitle(title);
        progress.setMessage("Please Wait");
        progress.show();
    }

    private void finishProgress() {
        progress.dismiss();
    }

    private void stripePay() {
        Stripe stripe = new Stripe(getContext(), PUBLISHABLE_KEY);
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        Toast.makeText(getContext(), "Token created: " + token.getId(), Toast.LENGTH_LONG).show();
                        tok = token;
                        new StripeCharge(token.getId()).execute();


                    }

                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

    public void postData(String description, String token, String amount) {
        // Create a new HttpClient and Post Header
        try {
            URL url = new URL("https://parseapi.back4app.com/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new NameValuePair("method", "charge"));
            params.add(new NameValuePair("description", description));
            params.add(new NameValuePair("source", token));
            params.add(new NameValuePair("amount", amount));

            OutputStream os = null;

            os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        Log.e("Query", result.toString());
        return result.toString();
    }

    public class StripeCharge extends AsyncTask<String, Void, String> {
        String token;

        public StripeCharge(String token) {
            this.token = token;
        }

        @Override
        protected String doInBackground(String... params) {
            new Thread() {
                @Override
                public void run() {
                    postData(getName(), token, "");
                }
            }.start();
            return "Done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Result", s);
        }
    }

    public class NameValuePair {
        String name, value;

        public NameValuePair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}