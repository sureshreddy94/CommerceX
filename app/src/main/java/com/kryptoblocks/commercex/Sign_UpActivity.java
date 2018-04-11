package com.kryptoblocks.commercex;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.pojo.RegOutput;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Sign_UpActivity extends AppCompatActivity {

    EditText reg_email,reg_password,reg_repwd,reg_firstname,reg_mobile,reg_lastname;
    Button signup;
    String pwd,repwd;
    String fname,lname,mobil,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);


        reg_firstname = findViewById(R.id.input_firstname);
        reg_lastname = findViewById(R.id.input_lastname);
        reg_mobile = findViewById(R.id.input_mobile_number);
        reg_email = findViewById(R.id.input_email_id);
        reg_password = findViewById(R.id.input_pwd);
        reg_repwd = findViewById(R.id.input_re_enter_password);

        signup = findViewById(R.id.sign_up);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!reg_firstname.getText().toString().isEmpty() && !reg_email.getText().toString().isEmpty() && !reg_mobile.getText().toString().isEmpty() )
                {
                    pwd = reg_password.getText().toString();
                    repwd = reg_repwd.getText().toString();

                    if(pwd.equals(repwd)){

                        fname = reg_firstname.getText().toString().trim();
                        lname = reg_lastname.getText().toString().trim();
                        mobil = reg_mobile.getText().toString().trim();
                        email = reg_email.getText().toString().trim();


                        new RegisterCustomerAsync().execute(email,repwd,fname,mobil,lname);

                    } else
                    { Toast.makeText(Sign_UpActivity.this, "Password is mismatched", Toast.LENGTH_SHORT).show(); }

                }else
                { Toast.makeText(Sign_UpActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show(); }
            }
        });

    }

    public class RegisterCustomerAsync extends AsyncTask<String, Integer, RegOutput> {
        RestAdapter restAdapter;
        public String res;

        @Override
        protected void onPreExecute() {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            //showDialog();
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://178.62.45.194:4800/api/v1/user")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        protected RegOutput doInBackground(String... params) {
            RegOutput status = null;
            try {
                Service_Interface reg = restAdapter.create(Service_Interface.class);
                status = reg.registerCustomer(params[0],params[1],params[2],params[3],params[4]);
            }catch (Exception e){
                //Toast.makeText(Education_login.this, "expection", Toast.LENGTH_SHORT).show();
            }
            return status;
        }

        protected void onPostExecute(RegOutput response)
        {
            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) Sign_UpActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (response.getMessage().equals("Account verification email sent successfully")) {

                    Intent i = new Intent(Sign_UpActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(Sign_UpActivity.this, "Register is succesfull.", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(Sign_UpActivity.this,"username already exist.", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e){e.printStackTrace();
                Toast.makeText(Sign_UpActivity.this,"Worng credential", Toast.LENGTH_SHORT).show();}
        }
    }
}

