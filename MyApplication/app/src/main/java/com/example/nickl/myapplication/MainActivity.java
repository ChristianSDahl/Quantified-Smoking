package com.example.nickl.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import static android.R.attr.data;
import static android.R.attr.order;
import static android.R.attr.value;
import static android.os.Build.VERSION_CODES.M;
import static android.view.View.Z;

public class MainActivity extends AppCompatActivity {

    public static String[] storedCigarette = new String[3];

    //        Making this class aware of the context of content_main.xml
//    public EditText editText;
    public TextView textView;
    public Button save, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        connecting objects from content_main.xml to variables defined earlier
//        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.storedpreview);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
    }


    public void buttonInfo(View view) {
//        Log.d("test", String.valueOf(view.getId()));
        if (view.getId() <= 2131558535 && view.getId() >= 2131558529) {
            Button x = (Button) view;
            storedCigarette[0] = String.valueOf(x.getText());
            for (int i = 2131558529; i <= 2131558535; i++) {
                if (view.getId() != i) {
//                    Log.d("idtest",findViewById(i).getTransitionName());
                    findViewById(i).getBackground().clearColorFilter();
                    findViewById(i).invalidate();
                    view.setSelected(false);
                }
            }
        } else if (view.getId() <= 2131558545 && view.getId() >= 2131558536) {
            Button y = (Button) view;
            storedCigarette[1] = String.valueOf(y.getText());
            for (int i = 2131558536; i <= 2131558545; i++) {
                if (view.getId() != i) {
                    findViewById(i).getBackground().clearColorFilter();
                    findViewById(i).invalidate();
                    view.setSelected(false);
                }
            }
        } else if (view.getId() <= 2131558556 && view.getId() >= 2131558546) {
            Button z = (Button) view;
            storedCigarette[2] = String.valueOf(z.getText());
            for (int i = 2131558546; i <= 2131558556; i++) {
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
            view.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
            view.invalidate();
        } else {
            view.getBackground().clearColorFilter();
            view.invalidate();
        }
    }

    //Defining the two listeners for respectively; button for load, button for save
    public void buttonSave(View view) {
        File directory = getApplicationContext().getDir("mydir", Context.MODE_APPEND);
//        Log.d("directorypath", getApplicationContext().getDir("mydir", Context.MODE_APPEND).getPath());
        File file = new File(directory, "savedcigarettes");
        //Takes the contents of the editText box and splits into various array elements according to a line separator defined through the System class

//        String[] savedTEXT = String.valueOf(storedCigarette).split(System.getProperty("line.separator"));
        String[] savedTEXT = storedCigarette;
//        Log.d("textsaved", TextUtils.join(",", savedTEXT));
//        String[] savedTEXT = String.valueOf(editText.getText()).split(System.getProperty("line.separator"));


        boolean[] tempNullCheck = {false, false, false};
        String[] tempMissingCheck = {null, null, null};
        for (int i = 0; i < storedCigarette.length; i++) {
            if (storedCigarette[i] == null) {
                switch (i) {
                    case 0:
                        tempMissingCheck[i] = "place";
                        break;
                    case 1:
                        tempMissingCheck[i] = "situation";
                        break;
                    case 2:
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
        for (int i = 2131558529; i <= 2131558556; i++) {
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
        ArrayList<String[]> loadedTEXT = loadfile(file);
//        ArrayList loadedCigarettes = loadfile(file);
//        ArrayList<String[]> loadedCigarettes = loadfile(file);

        String finalString = "";

        //Runs the the returned String array loadedTEXT line for line

//        for (int i = 0; i < loadedTEXT.size(); i++) {
//            finalString += loadedTEXT[i];
//            finalString += "\n";
//        }

        Toast.makeText(getApplicationContext(), "Loaded", Toast.LENGTH_SHORT).show();

//        for (int i = 0; i < loadedCigarettes.size(); i++) {
//            for (int j = 0; j < loadedCigarettes.get(i).length - 1; j++) {
//                if (j == 0) {
//                    finalString += loadedCigarettes.get(i)[j];
//                } else if (j == loadedCigarettes.get(i).length) {
////                    finalString += loadedCigarettes.get(i)[j] + "\n";
//                } else {
//                    finalString += ", " + loadedCigarettes.get(i)[j];
//                }
//            }
//        }

//        for (int i = 0; i < loadedCigarettes.size(); i++) {
//            for (int j = 0; j < loadedCigarettes.get(i).length; j++) {
//                finalString += loadedCigarettes.get(i)[j];
//            }
//        }
//        Log.d("all_cigarettes", finalString);

        //Sets the textbox's content to the loaded text from the file
        textView.setText(finalString);
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
//                fos.write("/".getBytes());
//                fos.write("\n".getBytes());

                //Going through the stored data array and writing it to the file
                for (int i = 0; i < data.length; i++) {
                    if (data[i] != null) {
                        if (i == 0) {
                            fos.write(data[i].getBytes());
                        } else {
                            String tempsave = ", " + data[i];
                            fos.write(tempsave.getBytes());
                        }
//                        fos.write(data[i].getBytes());
//                        fos.write("\n".getBytes());
//                        if (i == data.length - 1) {
//                            fos.write("\n".getBytes());
//                    }
                    }
                    //If fileoutputstream reaches the end of arrays length
                    //meaning it has written all the content
                    //the fos will create a line separator
                    if (i < data.length - 1) {
//                        fos.write("\n".getBytes());
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
    public static ArrayList<String[]> loadfile(File file) {
        ArrayList<String[]> arrList = new ArrayList<>();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String testing = "";
        String temp2 = "";
        try {
            while ((testing = br.readLine()) != null) {
                //temp2 += temp + ";";
                arrList.add(testing.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Håndter teksfil bagefter se hans kode

        //Setting up variables to be used in the following block of code
        String temp = "";
        //Used to find length of array
        int count = 0;

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
//        for (int j = 0; j < loadedtext.length; j++) {
//            loadedtext[j] = "";
//        }
        int i = 0;
        int j = 0;

//        cigaretteArrayList.clear();
        try {
            while ((templine = br.readLine()) != null) {
                loadedtext[i] = templine;
                i++;
//                Log.d("texttest", String.valueOf(loadedtext[i]));
//                i++;
//                if (i == 3) {
//                    i = 0;
//                    for (int u = 0; u < loadedtext.length; u++) {
//                        Log.d("indretest", loadedtext[u]);
//                    }
//                    cigaretteArrayList.add(loadedtext);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (int k = 0; k < cigaretteArrayList.size(); k++) {
//            for (int l = 0; l < cigaretteArrayList.get(k).length; l++) {
//                Log.d("testing", "element " + k + " inner element " + l + " content: " + cigaretteArrayList.get(k)[l]);
//            }
//        }
//        Log.d("SizecigaretteArrayList", String.valueOf(cigaretteArrayList.size()));
        return arrList;
    }


//    public void openCameraReturnPhoto(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, REQEUST_IMAGE_CAPTURE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQEUST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    public static void writeStringToFile(final String fileContents, String fileName) {
//        Context context = App.instance.getApplicationContext();
//        try {
//            FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
//            out.write(fileContents);
//            out.close();
//        } catch (IOException e) {
//            Logger.logError(TAG, e);
//        }
//    }

//    public static String readFileToString(String fileName) {
//        Context context = App.instance.getApplicationContext();
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        BufferedReader in = null;
//
//        try {
//            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
//            while ((line = in.readLine()) != null) stringBuilder.append(line);
//
//        } catch (FileNotFoundException e) {
//            Logger.logError(TAG, e);
//        } catch (IOException e) {
//            Logger.logError(TAG, e);
//        }
//
//        return stringBuilder.toString();
//    }
}
