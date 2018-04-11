package com.kryptoblocks.commercex.api_interface;

import android.content.Context;
import android.util.Log;

import com.kryptoblocks.commercex.api_pojo.Forgot_Password_Input_Pojo;
import com.kryptoblocks.commercex.api_pojo.Forgot_Password_Output_Pojo;
import com.kryptoblocks.commercex.api_pojo.Message;
import com.kryptoblocks.commercex.api_pojo.SignIn_Input_Pojo;
import com.kryptoblocks.commercex.api_pojo.SignIn_Output_Pojo;
import com.kryptoblocks.commercex.api_pojo.UserProfileInput;
import com.kryptoblocks.commercex.api_pojo.User_Profile_Output;
import com.kryptoblocks.commercex.pojo.OtpOutput;
import com.kryptoblocks.commercex.pojo.RegInput;
import com.kryptoblocks.commercex.pojo.RegOutput;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit2.Call;
import retrofit.http.Headers;


public interface Service_Interface {

//Sujata_api

    @POST("/login")
    SignIn_Output_Pojo registration_input(@Body SignIn_Input_Pojo registration_input);

    @POST("/forgot-password")
    Forgot_Password_Output_Pojo forgot_input(@Body Forgot_Password_Input_Pojo forgot_output);

    @GET("/profile")
    User_Profile_Output getUserProfile();


    //pradeep_api

    @FormUrlEncoded
    @POST("/registration")
    RegOutput registerCustomer(@Field("email") String email,
                               @Field("password") String pwd,
                               @Field("first_name") String fnam,
                               @Field("mobile") String mob,
                               @Field("last_name") String lnam);

    @POST("/forgot-password")
    RegOutput forgotPwd(@Body RegInput param);



    @FormUrlEncoded
    @POST("/check-otp")
    OtpOutput checkOtpp(@Field("email") String emaill,
                        @Field("otp") String otp);

    @GET("/logout")
    Message getUserlogout();

    @PUT("/profile")
    User_Profile_Output getUserProfileEdit(@Body UserProfileInput user_profile_input);

    @POST("/reset-password")
    SignIn_Output_Pojo reset_password(@Body SignIn_Input_Pojo registration_input);


}

