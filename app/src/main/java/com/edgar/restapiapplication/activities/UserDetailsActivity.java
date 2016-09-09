package com.edgar.restapiapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.adapter.RecyclerViewAdapter;
import com.edgar.restapiapplication.api.Api;
import com.edgar.restapiapplication.model.Repo;
import com.edgar.restapiapplication.model.User;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {
    private static final String LOG = "myLogs";
    @Bind(R.id.name_surname)
    TextView txtName;
    @Bind(R.id.bio)
    TextView bio;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String userNameFromIntent = intent.getStringExtra("name_key");

        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (userNameFromIntent != null) {
            Api.getInstance().getApiInterface().getUserInformation(userNameFromIntent)
                    .enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            final String responseName = response.body().getName();
                            final String responseBio = response.body().getBio();
                            final String responseAvatar = response.body().getAvatar_url();

                            int code = response.code();
                            Log.d(LOG, "code " + code);

                            switch (code) {
                                case 200: {
                                    if (MainActivity.userNameEt != null) {
                                        if (responseName != null) txtName.setText(responseName);
                                        else txtName.setHint("Unknown");
                                        if (responseBio != null) bio.setText(responseBio);
                                        else bio.setHint("User don't have any information");
                                        if (responseAvatar != null) {
                                            Picasso.with(image.getContext())
                                                    .load(responseAvatar)
                                                    .fit()
                                                    .into(image);
                                        }
                                    }
                                    break;
                                }

                                default: {
                                    Log.e(LOG, "Error");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

            Api.getInstance().getApiInterface().getUserInformationForRepo(userNameFromIntent)
                    .enqueue(new Callback<List<Repo>>() {

                        @Override
                        public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                            List<Repo> repoList = response.body();
                            Log.d(LOG, "reposList = " + repoList.get(0).getName());
                            adapter.setItems(response.body());
                        }


                        @Override
                        public void onFailure(Call<List<Repo>> call, Throwable t) {
                            Log.d(LOG, "Some Error in Second Reguest");
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
