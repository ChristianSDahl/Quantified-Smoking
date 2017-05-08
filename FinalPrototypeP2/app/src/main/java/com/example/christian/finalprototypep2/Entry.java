package com.example.christian.finalprototypep2;

import processing.core.PApplet;

/**
 * Created by Christian on 08-05-2017.
 */

public class Entry extends PApplet {

    public long ms;
    public int place, situation, feeling;
    public int[] feelarray;




    public Entry() {

    }
    public void setArguments(String cigarette) {
        this.place = placetoint(split(cigarette, ",")[1]);
//        println("place: " + place);
        this.situation = situationtoint(split(cigarette, ",")[2]);
//        println("situation: " + situation);
        this.feeling = feelingtoint(split(cigarette, ",")[3]);
        println("Whole string: " + cigarette + ", place: " + place + ", situation: " + situation + ", feeling: " + feeling);
    }

    public int placetoint(String place) {
        int placeint = 0;
        if (place.equals("Living room")) {
            println("I*M RUN");
            placeint = 0;
        } else if (place.equals("Kitchen")) {
            placeint = 1;
        } else if (place.equals("Designated smoking area")) {
            placeint = 2;
        } else if (place.equals("Outside")) {
            placeint = 3;
        } else if (place.equals("Car")) {
            placeint = 4;
        } else if (place.equals("Bar")) {
            placeint = 5;
        } else if (place.equals("Other")) {
            placeint = 6;
        }
        return placeint;
    }
    public int situationtoint(String situation) {
        int sitint = 0;
        if (situation.equals("After food")) {
            sitint = 0;
        } else if (situation.equals("TV/Radio")) {
            sitint = 1;
        } else if (situation.equals("Break")) {
            sitint = 2;
        } else if (situation.equals("On the go")) {
            sitint = 3;
        } else if (situation.equals("Alcohol")) {
            sitint = 4;
        } else if (situation.equals("Social")) {
            sitint = 5;
        } else if (situation.equals("Coffee")) {
            sitint = 6;
        } else if (situation.equals("Work")) {
            sitint = 7;
        } else if (situation.equals("After sexual relation")) {
            sitint = 8;
        } else if (situation.equals("Other")) {
            sitint = 9;
        }
        return sitint;
    }
    public int feelingtoint(String feeling) {
        int feelint = 0;
        if (feeling.equals("Stress")) {
            feelint = 0;
        } else if (feeling.equals("Sadness")) {
            feelint = 1;
        } else if (feeling.equals("Boredom")) {
            feelint = 2;
        } else if (feeling.equals("Happiness")) {
            feelint = 3;
        } else if (feeling.equals("Tired")) {
            feelint = 4;
        } else if (feeling.equals("Content")) {
            feelint = 5;
        } else if (feeling.equals("Neutral")) {
            feelint = 6;
        } else if (feeling.equals("Relaxed")) {
            feelint = 7;
        } else if (feeling.equals("Craving")) {
            feelint = 8;
        } else if (feeling.equals("Hunger")) {
            feelint = 9;
        } else if (feeling.equals("Other")) {
            feelint = 10;
        }
        return feelint;
    }
}