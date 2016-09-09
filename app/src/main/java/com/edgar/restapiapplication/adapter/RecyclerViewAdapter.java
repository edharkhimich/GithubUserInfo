package com.edgar.restapiapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String LOG = "myLogs";
    List<Repo> repositories;

    public RecyclerViewAdapter(){
        repositories = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repos_info, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = repositories.get(position);
        Log.d("myLogs", "reposNAME ====> " + repositories.get(0).getName());
        holder.repos_name.setText("Project name : " + repo.getName());
        holder.repos_language.setText("Language : " + repo.getLanguage());

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void setItems(List<Repo> repositories){
        this.repositories = repositories;
        notifyDataSetChanged();
        Log.d(LOG, "Now repositories = " + repositories);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.repos_name)
        TextView repos_name;

        @Bind(R.id.repos_language)
        TextView repos_language;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

}
