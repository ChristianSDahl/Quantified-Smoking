package mednolse16studentaaudk.graphvisualization;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.R.attr.timeZone;
import static java.text.DateFormat.DATE_FIELD;
import static java.text.DateFormat.HOUR_OF_DAY0_FIELD;
import static java.text.DateFormat.getDateTimeInstance;

public class MainActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1, series2;
    public Button quickinput;
    public File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quickinput = (Button) findViewById(R.id.quickinput);


        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        file = new File(directory, "savedquickcigarettes");

        String[] quick = loadfile(file);



        double y, x;
        x = 0; //Where the graph starts

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();
        series2 = new LineGraphSeries<>();

        Date[] dateLong = new Date[quick.length];

        int numDataPoints = 100; //Number of data points , number of lines in document
        for (int i = 0; i < quick.length; i++) {
            dateLong[i] = new Date(Long.parseLong(quick[i]));
            y = i+1;
            series1.appendData(new DataPoint(dateLong[i],y), true, quick.length);
        }


//        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getBaseContext()));
        DateFormat dateFormat = DateFormat.getDateTimeInstance(3,3);

//        DateFormat dateFormat = DateFormat.getDateInstance(HOUR_OF_DAY0_FIELD);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext(),dateFormat));
        graph.getGridLabelRenderer().setNumHorizontalLabels(2); // only 4 because of the
//        Date start = new Date()
        Date start = dateLong[0];
//        start.setTime(0);
        Date end = dateLong[dateLong.length-1];
//        end.setTime(24);
        Log.d("longtest",start.toString());
        graph.getViewport().setMinX(start.getTime());
        graph.getViewport().setMaxX(end.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setHumanRounding(true);
        graph.getViewport().setScalable(true);
        graph.addSeries(series1);


//        graph.addSeries(series2);
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(0);
//        graph.getViewport().setMaxX(100);
//        graph.getViewport().setScrollable(true);
//        graph.getViewport().setYAxisBoundsManual(true);
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setScalable(true);
    }

    public void quickinputclick(View view) {
        Calendar calendarlog = Calendar.getInstance();
        String timelog = String.valueOf(calendarlog.getTimeInMillis());
        saveQuickinput(file, timelog);

//        Date dateLong = new Date(Long.parseLong(timelog));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//        sdf.setTimeZone(TimeZone.getTimeZone("Copenhagen"));
//        Long dateLong=Long.parseLong(sdf.format(timelog));
//        Log.d("testing",dateLong.toString());

    }

    public void loadquickinputclick(View view) {
        String[] quickcigarettes = loadfile(file);
        for (int i = 0; i < quickcigarettes.length; i++) {
            Log.d("print", "line " + i + ": " + quickcigarettes[i]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
//                Date dateLong = new Date(Long.parseLong(templine));
//                loadedtext[i] = dateLong.toString();
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
