package com.example.christian.finalprototypep2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    PieChart menu;
    public File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        addDataSet();

        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");

        menu.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int picked = h.toString().indexOf("Highlight, x: ");
                int button = Integer.parseInt(h.toString().substring(picked + 14, picked + 15));

                if(button == 0){
                    Intent i = new Intent(MainMenu.this, SpecifiedInput.class);
                    startActivity(i);
                }
                else if(button == 1){
                    // Insert intent for opening data screen here.
                    Log.d("Test", "data");
                }
                else if (button == 2){
                    // Insert intent for opening economy screen here.
                    Log.d("Test", "economy");
                }
                else if (button == 3){
                    // Insert intent for opening settings screen here.
                    Log.d("Test", "settings");
                }
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    public void addDataSet(){

        menu = (PieChart) findViewById(R.id.menu);

        List<PieEntry> menuPoints = new ArrayList<>();
        menuPoints.add(new PieEntry(25,"In-Depth"));
        menuPoints.add(new PieEntry(25,"Data"));
        menuPoints.add(new PieEntry(25,"Economy"));
        menuPoints.add(new PieEntry(25,"Settings"));

        PieDataSet set = new PieDataSet(menuPoints, "");
        PieData data = new PieData(set);
        menu.setData(data);
        menu.invalidate();
        menu.setDrawSliceText(true);

        // Design
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#006D6D"));

        set.setColors(colors);
        set.setSliceSpace(2);
        set.setValueTextColor(Color.parseColor("#006D6D"));
        menu.setHoleRadius(45f);
        menu.setTransparentCircleAlpha(0);
        menu.setDescription(" ");


    }
    public void QuickInput(View view){
        Calendar calendarlog = Calendar.getInstance();
        String timelog = String.valueOf(calendarlog.getTimeInMillis());
        saveQuickinput(file, timelog);
    }

    public static void saveQuickinput(File file, String data) {
        //Defines an object of the class FileOutputSteam, initiates it as null
        FileOutputStream fos = null;

        boolean append = true;

        data = "0," + data;

        //Establishing a try/catch block to catch possible exceptions of various sorts when creating the fos
        try {
            fos = new FileOutputStream(file, append);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //Writing an indicator into the file in order for the load function
            //to correctly load cigarettes independently
            if (data != null) {
                fos.write(data.getBytes());
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
