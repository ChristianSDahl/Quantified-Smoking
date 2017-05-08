package com.example.christian.finalprototypep2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    PieChart menu;
    public File file;
    public static String[] loadedInput;
    public EditText explaintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_menu);

        addDataSet();

        explaintext = (EditText) findViewById(R.id.explainText);

        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");

        menu.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int picked = h.toString().indexOf("Highlight, x: ");
                int button = Integer.parseInt(h.toString().substring(picked + 14, picked + 15));

                if (button == 0) {
                    Intent i = new Intent(MainMenu.this, SpecifiedInput.class);
                    startActivity(i);
                } else if (button == 1) {
                    // Insert intent for opening data screen here.
                    Log.d("Test", "data");
                } else if (button == 2) {
                    // Insert intent for opening economy screen here.
                    Log.d("Test", "economy");
                } else if (button == 3) {
                    // Insert intent for opening settings screen here.
                    Log.d("Test", "settings");
                }
            }

            @Override
            public void onNothingSelected() {
            }
        });
        loadquickinputs();

        if (loadedInput.length > 2) {
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = new Sketch();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } else {
            //Graph will only show if you've inputted atleast 3 cigarettes - text
            explaintext.setVisibility(View.VISIBLE);
        }
    }

    public void addDataSet() {

        menu = (PieChart) findViewById(R.id.menu);

        List<PieEntry> menuPoints = new ArrayList<>();
        menuPoints.add(new PieEntry(25, "In-Depth"));
        menuPoints.add(new PieEntry(25, "Data"));
        menuPoints.add(new PieEntry(25, "Economy"));
        menuPoints.add(new PieEntry(25, "Settings"));

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

    public void QuickInput(View view) {
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
    public void loadquickinputs() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    loadedInput = loadfile(file);
                    //Your code goes here
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

}
