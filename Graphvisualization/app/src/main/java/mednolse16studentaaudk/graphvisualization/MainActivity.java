package mednolse16studentaaudk.graphvisualization;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public Button quickinput;
    public Button print;
    public static String[] loadedinput;
    public File directory;
    public File file;
    public EditText explaintext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explaintext = (EditText) findViewById(R.id.explainText);
        print = (Button) findViewById(R.id.loadbutton);
        quickinput = (Button) findViewById(R.id.quickinput);
        directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");

        loadquickinputs();

        if (loadedinput.length > 2) {
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

    public void loadquickinputs() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    loadedinput = loadfile(file);
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

    //load method, takes a file as input, returns string array
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

    public void quickinputclick(View view) {
        Calendar calendarlog = Calendar.getInstance();
        String timelog = String.valueOf(calendarlog.getTimeInMillis());
        saveQuickinput(file, "0," + timelog);
    }

    //save method, takes a file as input and a string array of content
    public static void saveQuickinput(File file, String data) {
        //Defines an object of the class FileOutputSteam, initiates it as null
        FileOutputStream fos = null;

        boolean append = true;

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

    //for debugging use
    public void loadquickinputclick(View view) {
        String[] quickcigarettes = loadfile(file);
        for (int i = 0; i < quickcigarettes.length; i++) {
            Log.d("print", "line " + i + ": " + quickcigarettes[i]);
        }
    }
}