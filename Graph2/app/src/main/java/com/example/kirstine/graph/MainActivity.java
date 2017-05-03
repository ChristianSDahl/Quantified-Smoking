package com.example.kirstine.graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1, series2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double y,x;
        x = 0; //Where the graph starts

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();
        series2 = new LineGraphSeries<>();

        int numDataPoints = 500; //Number of data points
        for(int i = 0; i<numDataPoints; i++) {
            x = x + 0.1; //Gives a smoother line, while if it would instead be 0.5 it would look more like dots
            y = Math.sin(x);
            double y2 = Math.cos(x);
            series1.appendData(new DataPoint(x, y), true, 100);
            series2.appendData(new DataPoint(x, y2), true, 60);
            series1.getViewport().set
        }
        graph.addSeries(series1);
        graph.addSeries(series2);
        }
    }

