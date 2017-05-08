package mednolse16studentaaudk.specifiedvisualization;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    File directory;
    File file;
    public static String[] loadedInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory,"savedcigarettes");
        loadSpecifiedInputs();

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
}
