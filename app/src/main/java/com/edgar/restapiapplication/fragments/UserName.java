package com.edgar.restapiapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edgar.restapiapplication.LanProgressNoFragmentDialog;
import com.edgar.restapiapplication.R;
import com.edgar.restapiapplication.activities.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserName extends Fragment {

    public static final String KEY = "key";
    private static final String KEY_OUTSTATE = "out state";
    private static final String LOG = "myLogs";
    public String userName;

    @Bind(R.id.searchBtn)
    Button searchBtn;
    @Bind(R.id.editText)
    EditText user_name;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanProgressNoFragmentDialog dialog = new LanProgressNoFragmentDialog(getActivity());
        dialog.setCancelable(false);

    }

    public UserName() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG, "onCreateView! savedinstance = " + savedInstanceState);
        View v = inflater.inflate(R.layout.username_fragment, container, false);
        ButterKnife.bind(this, v);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("User Name");

        user_name.requestFocus();

        if (savedInstanceState != null) {
            user_name.setText(savedInstanceState.getString(KEY));
        }

        return v;
    }

    @OnClick(R.id.searchBtn)
    void onClickSearch() {

        userName = user_name.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.please_enter_name), Toast.LENGTH_LONG).show();
        } else {
            if (MainActivity.isConn(getActivity())) {
                Bundle bundle = new Bundle();
                bundle.putString(UserName.KEY, userName);
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                userInfoFragment.setArguments(bundle);
                ((MainActivity)getActivity()).changeFragment(userInfoFragment, true);
            } else {

                Toast.makeText(getActivity(), "This application work correctly if the power of the Internet is switch on.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                getActivity().startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG, "onDestroyView");
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(user_name!=null) {
            outState.putString(KEY, user_name.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }
}
