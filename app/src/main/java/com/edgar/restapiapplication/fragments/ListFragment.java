package com.edgar.restapiapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.activities.MainActivity;
import com.edgar.restapiapplication.adapter.RecyclerViewAdapter;
import com.edgar.restapiapplication.api.Api;
import com.edgar.restapiapplication.model.Repo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    public ListFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        ButterKnife.bind(this, v);

        adapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.user_projects);

        Api.getInstance().getApiInterface().getUserInformationForRepo(UserInfoFragment.userName)
                .enqueue(new Callback<List<Repo>>() {

                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        int code = response.code();
                        if(code == 200) {
                            List<Repo> repoList = response.body();
                            adapter.setItems(repoList);
                        }
                        else{
                            Toast.makeText(getActivity(), R.string.not_correct, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        Toast.makeText(getActivity(), R.string.some_error_in_response, Toast.LENGTH_LONG).show();
                    }
                });

        return v;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
