package com.edgar.restapiapplication.other;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import com.edgar.restapiapplication.R;

public class LanProgressNoFragmentDialog extends Dialog {

    public LanProgressNoFragmentDialog(Context context) {
        super(context);
    }

    {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setCancelable(false);
        setContentView(R.layout.dialog_lan);
    }


}

