package com.kryptoblocks.commercex;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.pojo.OtpOutput;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class CheckOtpActivity extends AppCompatActivity {


    EditText enter_otp,enter_email;
    Button verify_otp;
    String v_otp,vemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_otp);

        enter_otp = findViewById(R.id.input_otp);
        enter_email = findViewById(R.id.input_uemaill);

        verify_otp = findViewById(R.id.verify_otp);

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enter_otp.getText().toString().isEmpty())
                {
                    v_otp = enter_otp.getText().toString();
                    vemail= enter_email.getText().toString();

                    new CustomerOtpAsync().execute(vemail,v_otp);

                } else { Toast.makeText(CheckOtpActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show(); }
            }
        });

    }

   public class CustomerOtpAsync extends AsyncTask<String, Integer, OtpOutput> {
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

        protected OtpOutput doInBackground(String... params) {
            OtpOutput status = null;
            try {
                Service_Interface reg = restAdapter.create(Service_Interface.class);
                status = reg.checkOtpp(params[0],params[1]);
            }catch (Exception e){
                //Toast.makeText(Education_login.this, "expection", Toast.LENGTH_SHORT).show();
            }
            return status;
        }

        protected void onPostExecute(OtpOutput response)
        {
            //try {

                if (response.getMessage().equals("OTP matched successfully")) {

                    Intent i = new Intent(CheckOtpActivity.this, ResetPasswordActivity.class);
                    startActivity(i);
                    Toast.makeText(CheckOtpActivity.this, "Your password is reset Successfully", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(CheckOtpActivity.this,"OTP Does not match", Toast.LENGTH_SHORT).show();
                }

           /* } catch (Exception e){e.printStackTrace();
                Toast.makeText(CheckOtpActivity.this,"Worng credential", Toast.LENGTH_SHORT).show();}*/
        }
    }
}
