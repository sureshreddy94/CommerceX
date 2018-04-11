package com.kryptoblocks.commercex;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.api_pojo.Forgot_Password_Input_Pojo;
import com.kryptoblocks.commercex.api_pojo.Forgot_Password_Output_Pojo;
import com.kryptoblocks.commercex.pojo.RegInput;
import com.kryptoblocks.commercex.pojo.RegOutput;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText enter_email;
    Button frgt_pwd;
    String frgt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        enter_email = findViewById(R.id.input_forgot_password);
        frgt_pwd = findViewById(R.id.frgt_btn);

        frgt_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enter_email.getText().toString().isEmpty())
                {
                    //frgt_email = enter_email.getText().toString();

                    RegInput input = new RegInput();
                    input.setEmail(enter_email.getText().toString().trim());

                    new ForgotPwdAsync().execute(input);

                } else {


                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter the Registered Email ID ", Toast.LENGTH_SHORT).show(); }


            }
        });

    }

    public class ForgotPwdAsync extends AsyncTask<RegInput, Integer, RegOutput> {
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
                    .setEndpoint("http://178.62.45.194:4800/api/v1")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        protected RegOutput doInBackground(RegInput... params) {
            RegOutput status = null;
            try {
                Service_Interface reg = restAdapter.create(Service_Interface.class);
                status = reg.forgotPwd(params[0]);
            }catch (Exception e){
                //Toast.makeText(Education_login.this, "expection", Toast.LENGTH_SHORT).show();
            }
            return status;
        }

        protected void onPostExecute(RegOutput response)
        {
            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) ForgotPasswordActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (response.getMessage().equals("We've sent an email with password reset instructions.")) {

                    Intent i = new Intent(ForgotPasswordActivity.this, CheckOtpActivity.class);
                    startActivity(i);
                    Toast.makeText(ForgotPasswordActivity.this, "Please Check OTP sent to your Email", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(ForgotPasswordActivity.this,"You entered wrong Email Id", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e){e.printStackTrace();


                Toast.makeText(ForgotPasswordActivity.this,"Worng credential", Toast.LENGTH_SHORT).show();}
        }
    }
}
