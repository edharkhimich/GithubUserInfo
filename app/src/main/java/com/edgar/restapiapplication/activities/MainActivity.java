package com.edgar.restapiapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.adapter.RecyclerViewAdapter;
import com.edgar.restapiapplication.api.Api;
import com.edgar.restapiapplication.model.Repo;
import com.edgar.restapiapplication.model.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String RESPONSE_NAME = "name";
    private static final String LOG = "myLogs";
    private static final String RESPONSE_BIO = "bio";
    private static final String RESPONSE_AVATAR = "avatar";
    private static final String INTENT_NAME = "name_key";
    private RecyclerViewAdapter adapter;

    @Bind(R.id.searchBtn)
    Button searchBtn;
    public static EditText userNameEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEt = (EditText) findViewById(R.id.editText);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.searchBtn)
    void onButtonClick() {
        Log.d("log", "OnClick");
        String userName = userNameEt.getText().toString();

        final Intent intent = new Intent(getApplicationContext(),
                UserDetailsActivity.class);
        intent.putExtra(INTENT_NAME, userName);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
