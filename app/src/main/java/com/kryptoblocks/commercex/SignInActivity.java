package com.kryptoblocks.commercex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;


import java.util.concurrent.TimeUnit;

import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.api_pojo.SignIn_Input_Pojo;
import com.kryptoblocks.commercex.api_pojo.SignIn_Output_Pojo;
import com.kryptoblocks.commercex.fragmentss.OffersFragment;
import com.kryptoblocks.commercex.fragmentss.UserProfileFragment;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    TextView tv_sign_up;

    private static final String TAG = SignInActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private static final String EMAIL = "email";
    LoginButton loginButton;
    Button signin;
    ImageView image_fingerprint_log_in, image_forgot_pasword, image_gmail_log_in;
    private TextView info,new_here_tv;

    //shared preferences
    public static final String MyPREFERENCES = "MyPrefs" ;
    //public static final String Auth_sign_in = "authKey";
    SharedPreferences prefs;
    //String authVar;

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    TextInputLayout sign_in_inputLayout_email, sign_in_inputLayout_password;
    TextInputEditText email_sign_in, password_sign_in;
    LinearLayout content_lin_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_in);

        tv_sign_up = findViewById(R.id.sign_up_textView);
        image_fingerprint_log_in = findViewById(R.id.figerprint_for_touch_id_image);
        image_forgot_pasword = findViewById(R.id.forgot_password_imageview);
        image_gmail_log_in = findViewById(R.id.gmail_login_image);
        new_here_tv = findViewById(R.id.new_here_text_view);
        content_lin_lay = findViewById(R.id.content_lay);

       /* new_here_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileFragment();
            }
        });*/


        signin = findViewById(R.id.signin);

        sign_in_inputLayout_email = findViewById(R.id.input_layout_signIn_email);
        sign_in_inputLayout_password = findViewById(R.id.input_layout_signIn_password);

        email_sign_in = findViewById(R.id.sign_in_email);
        password_sign_in = findViewById(R.id.sign_in_password);
       // loginButton = (LoginButton)findViewById(R.id.login_button);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!email_sign_in.getText().toString().isEmpty())&& !password_sign_in.getText().toString().isEmpty()) {

                    SignIn_Input_Pojo regis_input = new SignIn_Input_Pojo();
                    regis_input.setEmail(email_sign_in.getText().toString().trim());
                    regis_input.setPassword(password_sign_in.getText().toString().trim());

                    new UserSignInAsync().execute(regis_input);

                }
            }
        });

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        loginButton = findViewById(R.id.login_button);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);



       /* signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);
            }
        });*/

//Gmail signIn

        image_gmail_log_in.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //shared preferences for signIn
       // sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "display name: " + acct.getDisplayName());
            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            Intent i = new Intent(SignInActivity.this, MainActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("username", personName);
            bundle.putString("userimage", personPhotoUrl);
            i.putExtras(bundle);

            startActivity(i);
            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.gmail_login_image:
                signIn();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //for Gmail
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        //Facebook login
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            //mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(SignInActivity.this, MainActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }

        image_forgot_pasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

  private class  UserSignInAsync extends AsyncTask<SignIn_Input_Pojo, Integer, SignIn_Output_Pojo> {

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

      @Override
      protected SignIn_Output_Pojo doInBackground(SignIn_Input_Pojo... signIn_input_pojos) {

          SignIn_Output_Pojo status = null;
          try {
              Service_Interface service_interface = restAdapter.create(Service_Interface.class);
              status = service_interface.registration_input(signIn_input_pojos[0]);
          } catch (Exception e) {
              //Toast.makeText(Education_login.this, "expect00ion", Toast.LENGTH_SHORT).show();
          }
          return status;
      }
      protected void onPostExecute(SignIn_Output_Pojo response) {
          try {
              if (response.getMessage().equals("Login Successful")) {

                String authVar  = response.getAuthorization().toString();
                   prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                  SharedPreferences.Editor editor = prefs.edit();
                  editor.putString("var_auth", authVar);
                  Log.d("sig",authVar);
                  editor.commit();


                  Intent i= new Intent(SignInActivity.this, MainActivity.class);
                  startActivity(i);

                  Toast.makeText(SignInActivity.this, "SignIn is succesfull.", Toast.LENGTH_SHORT).show();
              } else {

                  Toast.makeText(SignInActivity.this, "Wrong try", Toast.LENGTH_SHORT).show();
              }

          } catch (Exception e) {
              e.printStackTrace();
              Toast.makeText(SignInActivity.this, "Session has timed out. Please login again to continue", Toast.LENGTH_SHORT).show();
          }
      }
  }
}
