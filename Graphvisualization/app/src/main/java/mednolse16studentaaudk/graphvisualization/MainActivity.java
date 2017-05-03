package mednolse16studentaaudk.graphvisualization;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1, series2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
        File file = new File(directory, "savedquickcigarettes");


        double y, x;
        x = 0; //Where the graph starts

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();
        series2 = new LineGraphSeries<>();

        int numDataPoints = 20; //Number of data points , number of lines in document
        for (int i = 0; i < numDataPoints; i++) {
            y = Math.pow(x, 2);
//            double y2 = Math.cos(x);
            series1.appendData(new DataPoint(x, y), true, 800);
//            series1.appendData();
//            series2.appendData(new DataPoint(x, y2), true, 800);
            x = x + 0.1; //Gives a smoother line, while if it would instead be 0.5 it would look more like dots
//            series1.getViewport().set
        }
        graph.addSeries(series1);
//        graph.addSeries(series2);
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(0);
//        graph.getViewport().setMaxX(100);
        graph.getViewport().setScrollable(true);
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
}
