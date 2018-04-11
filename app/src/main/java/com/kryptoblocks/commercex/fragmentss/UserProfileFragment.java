package com.kryptoblocks.commercex.fragmentss;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.commercex.MainActivity;
import com.kryptoblocks.commercex.R;
import com.kryptoblocks.commercex.SignInActivity;
import com.kryptoblocks.commercex.api_interface.Service_Interface;
import com.kryptoblocks.commercex.api_pojo.Data_User_Profile;
import com.kryptoblocks.commercex.api_pojo.Location;
import com.kryptoblocks.commercex.api_pojo.SignIn_Output_Pojo;
import com.kryptoblocks.commercex.api_pojo.UserProfileInput;
import com.kryptoblocks.commercex.api_pojo.User_Profile_Output;
import com.kryptoblocks.commercex.utilityy.Utility;
import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import static android.content.Context.MODE_PRIVATE;
import static com.kryptoblocks.commercex.SignInActivity.MyPREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    EditText profile_first_name, profile_last_name,profile_dob, profile_email, profile_gender, profile_mobile, profile_location, profile_address;
    Button profile_edit,reset_pwd;

    SharedPreferences shared;
    String auth;

    CircleImageView profile_pic;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        profile_pic = view.findViewById(R.id.change_profile_image);

        profile_first_name = view.findViewById(R.id.user_profile_edit_text_first_name);
        profile_last_name = view.findViewById(R.id.user_profile_edit_text_last_name);
        profile_email = view.findViewById(R.id.user_profile_edit_text_email);
        profile_gender = view.findViewById(R.id.user_profile_edit_text_gender);
        profile_mobile = view.findViewById(R.id.user_profile_edit_text_mobile);
        profile_location = view.findViewById(R.id.user_profile_edit_text_location);
        profile_address = view.findViewById(R.id.user_profile_edit_text_address);
        profile_dob = view.findViewById(R.id.user_profile_edit_text_dob);

        profile_edit = view.findViewById(R.id.user_profile__edit_button);
        reset_pwd = view.findViewById(R.id.user_reset_pwd);


        reset_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(myIntent);

            }
        });

        profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserProfileInput user_profile_input = new UserProfileInput();

                user_profile_input.setEmail(profile_email.getText().toString().trim());
                user_profile_input.setFirstName(profile_first_name.getText().toString().trim());
                user_profile_input.setLastName(profile_last_name.getText().toString().trim());
                user_profile_input.setDob(profile_dob.getText().toString().trim());
                user_profile_input.setGender(profile_gender.getText().toString().trim());
                user_profile_input.setMobile(profile_mobile.getText().toString().trim());
                user_profile_input.setAddress(profile_address.getText().toString().trim());

                new AsyncEditUserDetails().execute(user_profile_input);


            }
        });

profile_pic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        selectImage();
    }
});


        new AsyncUserDetails().execute();

        return view;
    }

    private class AsyncUserDetails extends AsyncTask<Void, Integer,User_Profile_Output> {

        RestAdapter restAdapter;
        public String res;



        @Override
        protected void onPreExecute() {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            //showDialog();

            shared = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);   // get the sharedpreference set named "A"
            auth = shared.getString("var_auth","missing");
            Log.d("Hi",auth);

            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", auth);
                }
            };

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://178.62.45.194:4800/api/v1/user")
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }
        @Override
        protected User_Profile_Output doInBackground(Void... params) {
            User_Profile_Output status = null;
            try {
                Service_Interface service_interface = restAdapter.create(Service_Interface.class);
                status = service_interface.getUserProfile();
            } catch (Exception e) {
                //Toast.makeText(Education_login.this, "expect00ion", Toast.LENGTH_SHORT).show();
            }
            return status;

        }

        protected void onPostExecute(User_Profile_Output response) {
            try {



                if (!response.getData().getId().isEmpty())   {

                    Toast.makeText(getContext(), "successful.", Toast.LENGTH_SHORT).show();

                    String fName = response.getData().getFirstName();
                    String lName = response.getData().getLastName();
                    String birth = response.getData().getDob();
                    String gender = response.getData().getGender();
                    String email = response.getData().getEmail();
                    String mobile = response.getData().getMobile();
                   // Location location = response.getData().getLocation();
                    String address = response.getData().getAddress();

                    profile_first_name.setText(fName);
                    profile_last_name.setText(lName);
                    profile_dob.setText(birth);
                    profile_gender.setText(gender);
                    profile_email.setText(email);
                    profile_mobile.setText(mobile);
                    profile_address.setText(address);



                } else {

                    Toast.makeText(getContext(), "Wrong try", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Session has timed out. Please login again to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AsyncEditUserDetails extends AsyncTask<UserProfileInput, Integer,User_Profile_Output> {

        RestAdapter restAdapter;
        public String res;



        @Override
        protected void onPreExecute() {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            //showDialog();

            shared = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);   // get the sharedpreference set named "A"
            auth = shared.getString("var_auth","missing");
            Log.d("Hi",auth);

            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", auth);
                }
            };

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://178.62.45.194:4800/api/v1/user")
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }
        @Override
        protected User_Profile_Output doInBackground(UserProfileInput... params) {
            User_Profile_Output status = null;
            try {
                Service_Interface service_interface = restAdapter.create(Service_Interface.class);
                status = service_interface.getUserProfileEdit(params[0]);
            } catch (Exception e) {
                //Toast.makeText(Education_login.this, "expect00ion", Toast.LENGTH_SHORT).show();
            }
            return status;

        }

        protected void onPostExecute(User_Profile_Output response) {
            try {



                if (!response.getData().getId().isEmpty())   {

                    Toast.makeText(getContext(), "successfulllll.", Toast.LENGTH_LONG).show();

                    String fName = response.getData().getFirstName();
                    String lName = response.getData().getLastName();
                    String birth = response.getData().getDob();
                    String gender = response.getData().getGender();
                    String email = response.getData().getEmail();
                    String mobile = response.getData().getMobile();
                    // Location location = response.getData().getLocation();
                    String address = response.getData().getAddress();

                    profile_first_name.setText(fName);
                    profile_last_name.setText(lName);
                    profile_dob.setText(birth);
                    profile_gender.setText(gender);
                    profile_email.setText(email);
                    profile_mobile.setText(mobile);
                    profile_address.setText(address);

                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(myIntent);



                } else {

                    Toast.makeText(getContext(), "Wrong try", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Session has timed out. Please login again to continue", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void selectImage() {

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(getContext());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        profile_pic.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        profile_pic.setImageBitmap(bm);
    }


}
