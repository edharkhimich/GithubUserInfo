package com.edgar.restapiapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.adapter.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        String responseUserName = intent.getStringExtra("name");
        String responseBio = intent.getStringExtra("bio");
        String responseAvatar_url = intent.getStringExtra("avatar");

        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        Log.d(LOG, "Response User Name : " + responseUserName);


        if (MainActivity.userNameEt != null) {
            if (responseUserName != null) txtName.setText(responseUserName);
            else txtName.setHint("Unknown");
            if (responseBio != null) bio.setText(responseBio);
            else bio.setHint("User don't have any information");
            if (responseAvatar_url != null) {
                Picasso.with(image.getContext())
                        .load(responseAvatar_url)
                        .fit()
                        .into(image);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
