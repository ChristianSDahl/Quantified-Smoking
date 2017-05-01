package com.example.christian.donutvisualization;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class MainMenuDonut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_donut);

        addDataSet();
    }

    public void addDataSet(){
        PieChart menu;

        menu = (PieChart) findViewById(R.id.menu);

        List<PieEntry> menuPoints = new ArrayList<>();
        menuPoints.add(new PieEntry(25,"In-Depth"));
        menuPoints.add(new PieEntry(25,"Data"));
        menuPoints.add(new PieEntry(25,"Economy"));
        menuPoints.add(new PieEntry(25,"Settings"));

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
}
