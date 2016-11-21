package com.edgar.restapiapplication.activities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.edgar.restapiapplication.other.LanProgressNoFragmentDialog;
import com.edgar.restapiapplication.R;

import com.edgar.restapiapplication.fragments.UserName;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    LanProgressNoFragmentDialog dialog;

    @Bind(R.id.myToolbar)
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new LanProgressNoFragmentDialog(this);
        dialog.setCancelable(false);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(R.drawable.ic_action_back);

        if (getSupportFragmentManager().findFragmentById(R.id.containerMain) == null) {
            changeFragment(new UserName(), false);
        }
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

    public static boolean isConn(Context c) {
        ConnectivityManager connectivity = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected())
                return true;
        }
        return false;
    }

    public void changeFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerMain, fragment, fragment.getClass().getSimpleName());

        if(addToBackStack){
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    public void showProgress(){
        dialog.show();
    }

    public void dismissDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
}

