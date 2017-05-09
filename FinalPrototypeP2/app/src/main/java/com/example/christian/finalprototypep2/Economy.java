package com.example.christian.finalprototypep2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.os.Handler;

import java.io.File;

public class Economy extends AppCompatActivity {
    File directory;
    File file;
    //    public File file
    private String[] loadedInput;
    private int progressStatus;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);

        directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");
        loadedInput = MainMenu.loadfile(file);
        if (loadedInput != null) {
            progressStatus = (loadedInput.length * 2);
            handler = new Handler();

            // Get the widgets reference from XML layout
            final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
            final Button btn = (Button) findViewById(R.id.btn);
            final TextView tv = (TextView) findViewById(R.id.tv);
            final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);


            new Thread(new Runnable() {
                @Override
                public void run() {

                    if (progressStatus < 500) {
                        // Update the progress status
                        progressStatus++;


                        // Update the progress bar
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress(progressStatus);
                                // Show the progress on TextView
                                tv.setText(progressStatus + " dkk");
                                // If task execution completed
                                if (progressStatus == 500) {
                                    // Set a message of completion
                                    tv.setText(progressStatus + " dkk spend on cigarettes");
                                    ImageView imgView = (ImageView) findViewById(R.id.dollarIcon);
                                    imgView.setVisibility(View.VISIBLE);
                                    progressStatus = 0;

//                                        R.id.dollarIcon:setVisible(true);
//                                        dollarIcon.visibility = true;
                                }
                            }
                        });
                    }
                }
            }).start(); // Start the operation
        }
    }

    public void backButton(View view) {
        Intent i = new Intent(Economy.this, MainMenu.class);
        startActivity(i);
    }
}