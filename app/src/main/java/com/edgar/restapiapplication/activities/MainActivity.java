package com.edgar.restapiapplication.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.edgar.restapiapplication.R;

import com.edgar.restapiapplication.fragments.UserName;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.myToolbar)
    Toolbar myToolbar;
    @Bind(R)
    FragmentTransaction transaction;
    private static Fragment fragmentt;
    private static int containerViewIdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(R.drawable.ic_action_back);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerMain, new UserName());
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void changeFragment(int containerViewId, Fragment fragment){
        fragmentt = fragment;
        containerViewIdd = containerViewId;


    }


}
