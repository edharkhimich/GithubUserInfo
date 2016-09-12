package com.edgar.restapiapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.edgar.restapiapplication.R;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;



public class MainActivity extends AppCompatActivity {

    private static final String INTENT_NAME = "name_key";


    @Bind(R.id.searchBtn)
    Button searchBtn;
    public static EditText userNameEt;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        userNameEt = (EditText) findViewById(R.id.editText);

        ButterKnife.bind(this);



    }

    @OnClick(R.id.searchBtn)
    void onButtonClick() {
        Log.d("log", "OnClick");
        String userName = userNameEt.getText().toString();

        if (userName.isEmpty())
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_LONG).show();
        else {
            final Intent intent = new Intent(getApplicationContext(),
                    UserDetailsActivity.class);
            intent.putExtra(INTENT_NAME, userName);
            startActivity(intent);
        }
    }



    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
