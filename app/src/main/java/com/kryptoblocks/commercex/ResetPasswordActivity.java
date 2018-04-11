package com.kryptoblocks.commercex;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.api_pojo.SignIn_Input_Pojo;
import com.kryptoblocks.commercex.api_pojo.SignIn_Output_Pojo;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText email,pwd,re_pwd;
    Button reset;
    String spwd,srepwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = findViewById(R.id.reset_email);
        pwd = findViewById(R.id.reset_password);
        re_pwd = findViewById(R.id.reset_confirm_password);

        reset = findViewById(R.id.user_reset_pwd);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!email.getText().toString().isEmpty())&& !pwd.getText().toString().isEmpty() && !re_pwd.getText().toString().isEmpty()) {

                    spwd = pwd.getText().toString();
                    srepwd = re_pwd.getText().toString();

                    if(spwd.equals(srepwd)){


                        SignIn_Input_Pojo regis_input = new SignIn_Input_Pojo();
                        regis_input.setEmail(email.getText().toString().trim());
                        regis_input.setPassword(srepwd);

                        new RestpasswordAsync().execute(regis_input);

                    }else {

                        Toast.makeText(ResetPasswordActivity.this, "Passwords is missmatch", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(ResetPasswordActivity.this, "Please enter all the fileds", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private class  RestpasswordAsync extends AsyncTask<SignIn_Input_Pojo, Integer, SignIn_Output_Pojo> {

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

        @Override
        protected SignIn_Output_Pojo doInBackground(SignIn_Input_Pojo... signIn_input_pojos) {

            SignIn_Output_Pojo status = null;
            try {
                Service_Interface service_interface = restAdapter.create(Service_Interface.class);
                status = service_interface.reset_password(signIn_input_pojos[0]);
            } catch (Exception e) {
                //Toast.makeText(Education_login.this, "expect00ion", Toast.LENGTH_SHORT).show();
            }
            return status;
        }
        protected void onPostExecute(SignIn_Output_Pojo response) {
            try {
                if (response.getMessage().equals("Password reset successfully")) {

                    Intent myIntent = new Intent(ResetPasswordActivity.this, SignInActivity.class);
                  startActivity(myIntent);
                    Toast.makeText(ResetPasswordActivity.this, "Password reset succesfull.", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(ResetPasswordActivity.this, "Please check the email", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ResetPasswordActivity.this, "Session has timed out. Please login again to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
