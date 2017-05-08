package com.example.christian.finalprototypep2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Christian on 08-05-2017.
 */

public class Info extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
    public void backButtonInfo(View view){
        Intent i = new Intent(Info.this, MainMenu.class);
        startActivity(i);
    }
}
