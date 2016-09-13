package com.edgar.restapiapplication.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.util.AttributeSet;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.edgar.restapiapplication.R;

import com.edgar.restapiapplication.fragments.UserName;
import io.fabric.sdk.android.Fabric;



public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.main_activity);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerMain, new UserName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
