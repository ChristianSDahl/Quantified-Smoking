package mednolse16studentaaudk.graphvisualization;

import java.util.Date;
import java.util.Locale;

import processing.core.PApplet;

/**
 * Created by Nickl on 06/05/2017.
 */



public class Sketch extends PApplet {
    public void settings() {

        fullScreen();
    }

    public void setup() {
        background(255);

        String[] lines = MainActivity.loadedinput;
        float numberOfLines = lines.length;

        float start = Float.parseFloat(split(lines[0], ',')[1]);
        float end = Float.parseFloat(split(lines[lines.length-1], ',')[1]);

        fill(0);

        line(30, 0, 30, height-60);
        line(25, height-60, width-20, height-60);
        textSize(30);
        stroke(0);

        Date d = new Date(Long.parseLong(split(lines[0], ',')[1]));
        String firstdate = new java.text.SimpleDateFormat("h:mm a",Locale.getDefault()).format(d);
        String firstmonth = new java.text.SimpleDateFormat("EEE, MMM d, ''yy",Locale.getDefault()).format(d);
        text(firstmonth + ", " + firstdate, 40, height-30);

        textAlign(RIGHT);
        d = new Date(Long.parseLong(split(lines[lines.length-1], ',')[1]));
        String lastdate = new java.text.SimpleDateFormat("h:mm a",Locale.getDefault()).format(d);
        String lastmonth = new java.text.SimpleDateFormat("EEE, MMM d, ''yy",Locale.getDefault()).format(d);
        text(lastmonth + ", " + lastdate, width-20, height-30);

        float xprev = -1;
        float yprev = -1;
        float ynew;

        for (int i = 0; i < lines.length; i++) {
            float xnew = ((Float.parseFloat(split(lines[i], ',')[1])-start)/(end-start))*(width-70)+50;
            ynew = height - ((((i+1))/(numberOfLines-0))*(height-80)+60);

            fill(0);
            stroke(0);
            //If not the first entry do this
            if (xprev != -1) {
                line(xprev, yprev, xnew, yprev);
                line(xnew, ynew, xnew, yprev);
            } else {
                line (50, height-60, 50, ynew);
            }
            xprev = xnew;
            yprev = ynew;
            stroke(0);
            line (28, ynew, 32, ynew);
            fill(0);
            text(i+1, 20, ynew+8);
        }
        for (int i = 0; i < lines.length; i++) {
            float xnew = ((Float.parseFloat(split(lines[i], ',')[1])-start)/(end-start))*(width-70)+50;
            ynew = height - ((((i+1))/(numberOfLines-0))*(height-80)+60);
            stroke(0);
            if (Float.parseFloat(split(lines[i], ",")[0]) == 0) {
                fill(214,214,214);
//                        #D6D6D6
            } else {
                fill(0,109,109);
//                        #006D6D
            }
            ellipseMode(CENTER);
            noStroke();
            ellipse(xnew, ynew, 20, 20);
        }
    }
}
