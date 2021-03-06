package com.example.christian.finalprototypep2;

import android.content.Context;
import android.content.Intent;
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

import static android.R.attr.y;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Christian on 05-05-2017.
 */

public class SpecifiedInput extends AppCompatActivity {
    //Initializing placeholder for input from user: time, place, situation, feeling
    public static String[] storedCigarette = new String[4];
    public File filequick;
    //Making this class aware of the context of content_specified_input.xml
    public TextView textView;
    public Button save, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specified_input);

        //Connecting objects from content_specified_input.xml to variables defined earlier
        save = (Button) findViewById(R.id.save);
        //getting filedirectory and file for quickinput
        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        filequick = new File(directory, "savedquickcigarettes");
    }

    public void buttonInfo(View view) {

        //Buttons are set in a vertical fashion. This first block of code check if the buttons
        //within the first vertical set are clicked.
        if (view.getId() <= 2131558553 && view.getId() >= 2131558547) {
            //If the button clicked is within the first vertical row
            //a temporary button object is instantiated with the view of the button clicked on
            Button x = (Button) view;
            //Stored the buttons textlabel into the storedCigarette String array
            storedCigarette[1] = String.valueOf(x.getText());
            //Runs through all the buttons from the first row again
            for (int i = 2131558547; i <= 2131558553; i++) {
                //If it is not the button clicked on
                if (view.getId() != i) {
                    //intantiates a temporary object with the selected ID (i)
                    View viewtemp = findViewById(i);
                    //Clear the temp buttons color to default
                    viewtemp.getBackground().clearColorFilter();
                    //view.invalidate forces a view to be drawn
                    viewtemp.invalidate();
                    //Sets the selected state of all of the buttons not clicked to false
                    viewtemp.setSelected(false);
                    //The state called "selected" of the button which was clicked on is set to true
                    view.setSelected(true);
                }
            }
        //Second set of buttons clicked.
        } else if (view.getId() <= 2131558563 && view.getId() >= 2131558554) {
            Button y = (Button) view;
            storedCigarette[2] = String.valueOf(y.getText());
            for (int i = 2131558554; i <= 2131558563; i++) {
                View viewtemp = findViewById(i);
                if (view.getId() != i) {
                    viewtemp.getBackground().clearColorFilter();
                    viewtemp.invalidate();
                    viewtemp.setSelected(false);
                    view.setSelected(true);
                }
            }
        //Third set of buttons clicked.
        } else if (view.getId() <= 2131558574 && view.getId() >= 2131558564) {
            Button z = (Button) view;
            storedCigarette[3] = String.valueOf(z.getText());
            for (int i = 2131558564; i <= 2131558574; i++) {
                View viewtemp = findViewById(i);
                if (view.getId() != i) {
                    viewtemp.getBackground().clearColorFilter();
                    viewtemp.invalidate();
                    viewtemp.setSelected(false);
                    view.setSelected(true);
                }
            }
        }

        //Change color of button depending on whether or not it is pressed
        if (view.isSelected()) {
            view.getBackground().setColorFilter(0xe0006d6d, PorterDuff.Mode.SRC_ATOP);
            view.invalidate();
        } else {
            view.getBackground().clearColorFilter();
            view.invalidate();
        }
    }

    //Defining the listener for button to save
    public void buttonSave(View view) {
        //Stores a log of the time and saves it in the first element of storedCigarette
        Calendar calendarlog = Calendar.getInstance();
        String timelog = String.valueOf(calendarlog.getTimeInMillis());
        storedCigarette[0] = timelog;
        saveQuickinput(filequick, timelog);

        File directory = getApplicationContext().getDir("mydir", Context.MODE_APPEND);
        File file = new File(directory, "savedcigarettes");

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
            Intent i = new Intent(SpecifiedInput.this, MainMenu.class);
            startActivity(i);
        }

        //Clearing array with stored information
        for (int i = 0; i < storedCigarette.length; i++) {
            storedCigarette[i] = null;
        }


    }

    //save method, takes a file as input and a String array of contents
    //(time,place,situation and feeling)
    public static void savefile(File file, String[] data) {
        //Defines an object of the class FileOutputSteam declares it as null
        FileOutputStream fos = null;
        //This fos's constructer takes a file as input and a boolean variable
        //This boolean depicts if the fos appends or overwrites into the file
        boolean append = true;
        //Establishing a try/catch block to catch possible exceptions of various sorts when
        //creating the fos
        try {
            //Instantiates the fos with the file declared earlier in the code
            //and the boolean variable in its constructor
            fos = new FileOutputStream(file, append);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Write to file
        try {
            try {
                //Going through the stored data array and writing it to the file with the fos
                for (int i = 0; i < data.length; i++) {
                    if (data[i] != null) {
                        //If the element selected is the first element
                        if (i == 0) {
                            //Writes the element's contents into the file
                            fos.write(data[i].getBytes());
                        } else {
                            //Sets up a temp String variable containing a comma and the contents
                            //of the element and writes it into the file
                            String tempsave = "," + data[i];
                            fos.write(tempsave.getBytes());
                        }
                    }
                    //If the fos reaches the end of arrays length
                    //meaning it has written all the content
                    //the fos will create a line separator enabling more data to be inputted
                    //in the future
                    if (i == data.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
                //A catch block to catch the exception "IOException" if thrown from the block
                //found above
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //finally is a block of code after a try/catch block that will run regardless of the results
        //of this try/catch block.
        // This is done to, as seen here, to try and close the outputstream: fos
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveQuickinput(File file, String data) {
        //Defines an object of the class FileOutputSteam, initiates it as null
        FileOutputStream fos = null;

        boolean append = true;

        data = "1," + data;

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

    public void backButton(View view) {
        Intent i = new Intent(SpecifiedInput.this, MainMenu.class);
        startActivity(i);
    }

}
