package com.example.christian.finalprototypep2;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Created by Christian on 05-05-2017.
 */

public class SpecifiedInput extends AppCompatActivity

{

    public static String[] storedCigarette = new String[4];

    //        Making this class aware of the context of content_main.xml
//    public EditText editText;
    public TextView textView;
    public Button save, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specified_input);

        //        connecting objects from content_main.xml to variables defined earlier
//        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.storedpreview);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
    }


    public void buttonInfo(View view) {
//        Log.d("test", String.valueOf(view.getId()));
        if (view.getId() <= 2131558537 && view.getId() >= 2131558531) {
            Button x = (Button) view;
            storedCigarette[1] = String.valueOf(x.getText());
            for (int i = 2131558531; i <= 2131558537; i++) {
                if (view.getId() != i) {
//                    Log.d("idtest",findViewById(i).getTransitionName());
                    findViewById(i).getBackground().clearColorFilter();
                    findViewById(i).invalidate();
                    view.setSelected(false);
                }
            }
        } else if (view.getId() <= 2131558547 && view.getId() >= 2131558538) {
            Button y = (Button) view;
            storedCigarette[2] = String.valueOf(y.getText());
            for (int i = 2131558538; i <= 2131558547; i++) {
                if (view.getId() != i) {
                    findViewById(i).getBackground().clearColorFilter();
                    findViewById(i).invalidate();
                    view.setSelected(false);
                }
            }
        } else if (view.getId() <= 2131558558 && view.getId() >= 2131558548) {
            Button z = (Button) view;
            storedCigarette[3] = String.valueOf(z.getText());
            for (int i = 2131558548; i <= 2131558558; i++) {
                if (view.getId() != i) {
                    findViewById(i).getBackground().clearColorFilter();
                    findViewById(i).invalidate();
                    view.setSelected(false);
                }
            }
        }

        //change color of button
        Button tempButtonForColourChange = (Button) view;
        tempButtonForColourChange.setSelected(!tempButtonForColourChange.isSelected());
        if (tempButtonForColourChange.isSelected()) {
            view.getBackground().setColorFilter(0xe0006d6d, PorterDuff.Mode.SRC_ATOP);
            view.invalidate();
        } else {
            view.getBackground().clearColorFilter();
            view.invalidate();
        }
    }

    //Defining the two listeners for respectively; button for load, button for save
    public void buttonSave(View view) {
        Calendar calendarlog = Calendar.getInstance();
        String timelog = String.valueOf(calendarlog.getTimeInMillis());
        storedCigarette[0] = timelog;

        File directory = getApplicationContext().getDir("mydir", Context.MODE_APPEND);
        File file = new File(directory, "savedcigarettes");
        //Takes the contents of the editText box and splits into various array elements according to a line separator defined through the System class

        String[] savedTEXT = storedCigarette;


        boolean[] tempNullCheck = {false, false, false};
        String[] tempMissingCheck = {null, null, null, null};
        for (int i = 0; i < storedCigarette.length; i++) {
            if (storedCigarette[i] == null) {
                switch (i) {
                    case 0:
                        tempMissingCheck[i] = "time";
                        break;
                    case 1:
                        tempMissingCheck[i] = "place";
                        break;
                    case 2:
                        tempMissingCheck[i] = "situation";
                        break;
                    case 3:
                        tempMissingCheck[i] = "feeling";
                        break;
                }
            }
        }
        String outputMissingCheck = "";
        for (int i = 0; i < tempMissingCheck.length; i++) {
            if (tempMissingCheck[i] != null) {
                if (outputMissingCheck == "") {
                    outputMissingCheck = tempMissingCheck[i];
                } else if (i != 0) {
                    outputMissingCheck += ", " + tempMissingCheck[i];
                }
            }
        }

        if (outputMissingCheck != "") {
            Toast.makeText(getApplicationContext(), "Missing: " + outputMissingCheck, Toast.LENGTH_SHORT).show();
        } else {
            savefile(file, savedTEXT);
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

        //Clearing array with stored information
        for (int i = 0; i < storedCigarette.length; i++) {
            storedCigarette[i] = null;
        }

        //Clearing all button colors to default
        for (int i = 2131558531; i <= 2131558558; i++) {
            findViewById(i).getBackground().clearColorFilter();
            findViewById(i).invalidate();
            view.setSelected(false);
        }
    }

    public void buttonLoad(View view) {
        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        File file = new File(directory, "savedcigarettes");
//        Log.d("directory", directory.toString());

        //Calls the loadfile-method defined below
        String[] loadedCigarettes = loadfile(file);

        String finalString = "";

        //Runs the the returned String array loadedTEXT line for line

        for (int i = 0; i < loadedCigarettes.length; i++) {
            Log.d("Ciggaretes", "line " + i + " " + loadedCigarettes[i]);
        }


        Toast.makeText(getApplicationContext(), "Loaded", Toast.LENGTH_SHORT).show();

    }

    //save method, takes a file as input and a string array of content
    public static void savefile(File file, String[] data) {
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
            try {
                //Writing an indicator into the file in order for the load function
                //to correctly load cigarettes independently

                //Going through the stored data array and writing it to the file
                for (int i = 0; i < data.length; i++) {
                    if (data[i] != null) {
                        if (i == 0) {
                            fos.write(data[i].getBytes());
                        } else {
                            String tempsave = ", " + data[i];
                            fos.write(tempsave.getBytes());
                        }
                    }
                    //If fileoutputstream reaches the end of arrays length
                    //meaning it has written all the content
                    //the fos will create a line separator
                    if (i == data.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //finally is a block of code after a try/catch block that will run regardless of the results
        //of this try/catch block. This is done to, as seen here, to try and close the outputstream: fos
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
//            Log.d("lineCount", String.valueOf(count));
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