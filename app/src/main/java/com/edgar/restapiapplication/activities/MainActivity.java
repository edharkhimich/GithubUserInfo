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

        Api.getInstance().getApiInterface().getUserInformation(userNameEt.getText().toString())
                .enqueue(new Callback<User>() {
                             final Intent intent = new Intent(getApplicationContext(),
                                     UserDetailsActivity.class);

                             @Override
                             public void onResponse(Call<User> call, Response<User> response) {

                                 final String responeName = response.body().getName();
                                 final String responseBio = response.body().getBio();
                                 final String responseAvatar = response.body().getAvatar_url();

                                 int code = response.code();
                                 Log.d(TAG, "code " + code);

                                 switch (code) {
                                     case 200: {
                                         Log.d(TAG, "RESPONSE_NAME " + responeName);
                                         Log.d(TAG, "RESPONSE_BIO " + responseBio);
                                         Log.d(TAG, "RESPONSE_AVATAR " + responseAvatar);

                                         intent.putExtra(RESPONSE_NAME, responeName);
                                         intent.putExtra(RESPONSE_BIO, responseBio);
                                         intent.putExtra(RESPONSE_AVATAR, responseAvatar);

                                         break;
                                     }

                                     default: {
                                         Log.e(TAG, "Error");
                                     }
                                 }
                                 Api.getInstance().getApiInterface().getUserInformationForRepo(userNameEt.getText().toString())
                                         .enqueue(new Callback<List<Repo>>() {

                                             @Override
                                             public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                                                 }


                                             @Override
                                             public void onFailure(Call<List<Repo>> call, Throwable t) {
                                                 Log.d(LOG, "Some Error in Second Reguest");

                                             }
                                         });

                                 startActivity(intent);
                             }

                             @Override
                             public void onFailure(Call<User> call, Throwable t) {
                                 Toast.makeText(getApplicationContext(), "The user is not found !",
                                         Toast.LENGTH_LONG).show();
                             }
                         }
                );
    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
