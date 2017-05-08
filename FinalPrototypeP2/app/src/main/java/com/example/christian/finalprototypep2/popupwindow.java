package com.example.christian.finalprototypep2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by Mads Grumstrup on 5/8/2017.
 */
public class popupwindow extends Activity {
    public static TextView popuptext;

    //    public static TextView text1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
//TitleBox
//        popuptext = (TextView) findViewById(R.id.titlebox);


    }
}
