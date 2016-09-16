package com.edgar.restapiapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.activities.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserName extends Fragment {

    public static final String KEY = "key";
    private static final String KEY_OUTSTATE = "out state";
    private static final String LOG = "myLogs";
    @Bind(R.id.editText)
    EditText user_name;
    String userName;
    UserInfoFragment userInfoFragment;

    public UserName(){
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG, "onCreateView! savedinstance = " + savedInstanceState);
        View v = inflater.inflate(R.layout.username_fragment, container, false);
        ButterKnife.bind(this, v);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("User Name");

        user_name.requestFocus();
        return v;
    }

    @OnClick (R.id.searchBtn)
    void onClickSearch(){
        userName = user_name.getText().toString();
        if (userName.isEmpty())
            Toast.makeText(getActivity(), R.string.please_enter_name, Toast.LENGTH_LONG).show();
        else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            userInfoFragment = new UserInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY, userName);
            userInfoFragment.setArguments(bundle);
            transaction.replace(R.id.containerMain, userInfoFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG, "onDestroyView");
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

}
