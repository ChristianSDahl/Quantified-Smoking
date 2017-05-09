package com.example.christian.finalprototypep2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Christian on 08-05-2017.
 */

public class Data  extends AppCompatActivity {

    //place
    public static ImageView livingroom, kitchen, desArea, outside, car, bar, other;
    //situation
    public static ImageView food, dbreak, alcohol, coffee, tv, onthego, sex, company, work, others;
    File directory;
    File file;
    Button back, legend;
    public static String[] loadedInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        back = (Button) findViewById(R.id.backdata);
        legend = (Button) findViewById(R.id.legend);

        back.getBackground().setColorFilter(0xe0006d6d, PorterDuff.Mode.SRC_ATOP);
        back.invalidate();

        legend.getBackground().setColorFilter(0xe0006d6d, PorterDuff.Mode.SRC_ATOP);
        legend.invalidate();

        directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory,"savedcigarettes");
        loadSpecifiedInputs();

        livingroom = new ImageView(this);
        livingroom.setImageResource(R.drawable.livingroom);
        kitchen = new ImageView(this);
        kitchen.setImageResource(R.drawable.kitchen);
        desArea = new ImageView(this);
        desArea.setImageResource(R.drawable.designatedsmokingareas);
        outside = new ImageView(this);
        outside.setImageResource(R.drawable.outside);
        car = new ImageView(this);
        car.setImageResource(R.drawable.inthecar);
        bar = new ImageView(this);
        bar.setImageResource(R.drawable.atthebar);
        other = new ImageView(this);
        other.setImageResource(R.drawable.placeother);

        food = new ImageView(this);
        food.setImageResource(R.drawable.afterfood);
        dbreak = new ImageView(this);
        dbreak.setImageResource(R.drawable.dbreak);
        alcohol = new ImageView(this);
        alcohol.setImageResource(R.drawable.alcohol);
        coffee = new ImageView(this);
        coffee.setImageResource(R.drawable.coffeebreak);
        tv = new ImageView(this);
        tv.setImageResource(R.drawable.watchingtv);
        onthego = new ImageView(this);
        onthego.setImageResource(R.drawable.onthego);
        sex = new ImageView(this);
        sex.setImageResource(R.drawable.aftersexualintercourse);
        company = new ImageView(this);
        company.setImageResource(R.drawable.withcompany);
        work = new ImageView(this);
        work.setImageResource(R.drawable.work);
        others = new ImageView(this);
        others.setImageResource(R.drawable.other);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new SketchSpecified();
        fragmentManager.beginTransaction()
                .replace(R.id.containerSpecified, fragment)
                .commit();

    }

    public void loadSpecifiedInputs() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    loadedInput = loadfile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
            Log.d("finishthread", "thread ends");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String[] loadfile(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Setting up variables to be used in the following block of code
        String temp;
        //Used to find length of array
        int count = 0;

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        try {
            while ((temp = br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //arraylist to store all the various cigarettes (String[]) in its own element
        String[] loadedtext = new String[count];

        String templine;
        int i = 0;

        try {
            while ((templine = br.readLine()) != null) {
                loadedtext[i] = templine;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedtext;
    }
    public void backButtonData(View view) {
        Intent i = new Intent(Data.this, MainMenu.class);
        startActivity(i);
    }
    public  void legendClick (View view) {
        Intent i = new Intent(Data.this, popupwindow.class);
        startActivity(i);
//        startActivity(new Intent(Data.this, popupwindow.class));

    }
}
