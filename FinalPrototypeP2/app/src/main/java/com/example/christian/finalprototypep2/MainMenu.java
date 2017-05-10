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
        setContentView(R.layout.activity_main_menu);

        addDataSet();

        explaintext = (EditText) findViewById(R.id.explainText);

        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");

        //Sets the pie chart we're using for our dial menu's value selector
        menu.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            //public void which takes the entry e (button selected) and Highlight h (which quarter
            //is highlighted
            public void onValueSelected(Entry e, Highlight h) {
                //picked becomes the int value of the identifier of the highlight (0,1,2,3)
                int picked = h.toString().indexOf("Highlight, x: ");
                //picked is cut further by only taking index 14,15 //the number
                int button = Integer.parseInt(h.toString().substring(picked + 14, picked + 15));

                //Could be replaced with switch
                //For each button, a different intent is instantiated and launched
                if (button == 0) {
                    Intent i = new Intent(MainMenu.this, SpecifiedInput.class);
                    startActivity(i);
                } else if (button == 1) {
                    Intent i = new Intent(MainMenu.this, Data.class);
                    startActivity(i);
                } else if (button == 2) {
                    Intent i = new Intent(MainMenu.this, Economy.class);
                    startActivity(i);
                } else if (button == 3) {
                    Intent i = new Intent(MainMenu.this, Info.class);
                    startActivity(i);
                }
            }

            //onNothingSelected is an overridden method from the class PieChart
            @Override
            public void onNothingSelected() {
            }
        });

        //Method call which creates, starts and waits for a thread
        //the public variable loadedInput becomes the results of this function
        loadquickinputs();

        if (loadedInput != null) {
            //For unknown reasons with the datavisualization it does not like to display
            //less than 3 inputs at a time. Therefor this if statement is implemented
            if (loadedInput.length > 2) {
                //Creates a FragmentManager object
                FragmentManager fragmentManager = getFragmentManager();
                //Instantiating a fragment object with the class Sketch
                Fragment fragment = new Sketch();
                //By using the fragmentmanager the container object within the layout:
                //content_main_menu, called container is changed with the fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        //Schedules a commit of this transaction
                        .commit();
            } else {
                //Graph will only show if you've inputted atleast 3 cigarettes - text
                explaintext.setVisibility(View.VISIBLE);
            }
        }
    }

    public void addDataSet() {

        menu = (PieChart) findViewById(R.id.menu);

        List<PieEntry> menuPoints = new ArrayList<>();
        menuPoints.add(new PieEntry(25, "In-Depth"));
        menuPoints.add(new PieEntry(25, "Data"));
        menuPoints.add(new PieEntry(25, "Economy"));
        menuPoints.add(new PieEntry(25, "Info"));

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
        //Instantiates a new thread object
        Thread thread = new Thread(new Runnable() {
            //overrides the method run implemented with the Runnable class
            @Override
            public void run() {
                try {
                    //Runs the method loadfile and the variable loadedInput
                    //becomes the returned variables of the methodcall
                    loadedInput = loadfile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Runs the run() method
        thread.start();
        try {
            //Cause the main thread to "pause" while the thread is finished running the run method
            thread.join();
            //A comment to print in the Android Monitor that the thread has run its course
            Log.d("finishthread", "thread ends");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String[] loadfile(File file) {
        //Defines an object of the class FineInputStream, initiates it as null
        FileInputStream fis = null;
        try {
            //Withing a try catch block, instantiates the fis with the FileInputStream constructor
            //and the file variabled defined prior to this
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Setting up variables to be used in the following block of code
        String temp;
        //Used to find length of array
        int count = 0;

        //Instantiates an object of the class InputStreamReader with the fis object
        InputStreamReader isr = new InputStreamReader(fis);
        //Instantiates an object of the class BufferedReader with the isr object
        BufferedReader br = new BufferedReader(isr);

        //Try/catch block to find the length of the text file
        try {
            while ((temp = br.readLine()) != null) {
                //for each line the count variables is added with 1
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Resets the fileinputstreamreader's position in the file back to the start
        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Arraylist to store all the various cigarettes. Each element is one cigarette
        //containing the following "variables": time,place,situation,feeling
        String[] loadedtext = new String[count];


        int i = 0;
        try {
            while ((temp = br.readLine()) != null) {
                //adds the line read into the current element of the String[] defined prior
                loadedtext[i] = temp;
                //additions the i variable with 1
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //closes all the streams used in this code
            try {
                fis.close();
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //returns the String[] loadedtext
        return loadedtext;
    }

}
