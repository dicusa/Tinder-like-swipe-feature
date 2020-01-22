package com.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.attendancemanager.databinding.ActivityStartBinding;

import api.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity {

Context context = StartActivity.this;
   ActivityStartBinding view;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("")//url of firebase app
            .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
            .build();

    ApiInterface api = retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       view =  DataBindingUtil.setContentView(this,R.layout.activity_start);
       
    }

    

    }

    

