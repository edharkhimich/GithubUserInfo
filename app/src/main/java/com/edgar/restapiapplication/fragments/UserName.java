package com.edgar.restapiapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.edgar.restapiapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserName extends Fragment {

    public static final String KEY = "key";
    @Bind(R.id.editText)
    EditText user_name;
    String userName;
    UserInfoFragment userInfoFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.username_fragment, null);
        ButterKnife.bind(this, v);


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
}
