package com.example.christian.finalprototypep2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ImageReader;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PImage;

import static android.R.attr.background;
import static android.R.attr.bitmap;
import static android.R.attr.ellipsize;
import static com.example.christian.finalprototypep2.Data.bar;
import static com.example.christian.finalprototypep2.Data.car;
import static com.example.christian.finalprototypep2.Data.food;
import static com.example.christian.finalprototypep2.Data.other;

/**
 * Created by Christian on 08-05-2017.
 */


public class SketchSpecified extends PApplet {
    //    //place
//    ImageView livingroom, kitchen, desArea, outside, car, bar, other;
//    //situation
//    ImageView food, dbreak, alcohol, coffee, tv, onthego, sex, company, work, others;
    float placeresx = (float) ((width / 5) * 4);
    float placeresy = (float) ((height / 7) * 4);

    float sitresx = (float) ((width / 6.5) * 3);
    float sitresy = (float) ((height / 6.5) * 3);

    ArrayList<String[]> tempSituationArrayList = new ArrayList<>();

    public void settings() {
        fullScreen();
    }

    public void setup() {
        if (Data.loadedInput != null) {
            float xcenter, ycenter, xsit, ysit, xfeel, yfeel;
            BitmapDrawable livingroomDraw = (BitmapDrawable) Data.livingroom.getDrawable();
            Bitmap livingroomBit = livingroomDraw.getBitmap();
            PImage livingroom = new PImage(livingroomBit);
            BitmapDrawable kitchenDraw = (BitmapDrawable) Data.kitchen.getDrawable();
            Bitmap kitchenBit = kitchenDraw.getBitmap();
            PImage kitchen = new PImage(kitchenBit);
            BitmapDrawable desAreaDraw = (BitmapDrawable) Data.desArea.getDrawable();
            Bitmap desAreaBit = desAreaDraw.getBitmap();
            PImage desArea = new PImage(desAreaBit);
            BitmapDrawable outsideDraw = (BitmapDrawable) Data.outside.getDrawable();
            Bitmap outsideBit = outsideDraw.getBitmap();
            PImage outside = new PImage(outsideBit);
            BitmapDrawable carDraw = (BitmapDrawable) Data.car.getDrawable();
            Bitmap carBit = carDraw.getBitmap();
            PImage car = new PImage(carBit);
            BitmapDrawable barDraw = (BitmapDrawable) bar.getDrawable();
            Bitmap barBit = barDraw.getBitmap();
            PImage bar = new PImage(barBit);
            BitmapDrawable otherDraw = (BitmapDrawable) Data.other.getDrawable();
            Bitmap otherBit = otherDraw.getBitmap();
            PImage other = new PImage(otherBit);

            BitmapDrawable foodDraw = (BitmapDrawable) Data.food.getDrawable();
            Bitmap foodBit = foodDraw.getBitmap();
            PImage food = new PImage(foodBit);
            BitmapDrawable dbreakDraw = (BitmapDrawable) Data.dbreak.getDrawable();
            Bitmap dbreakBit = dbreakDraw.getBitmap();
            PImage dbreak = new PImage(dbreakBit);
            BitmapDrawable alcoholDraw = (BitmapDrawable) Data.alcohol.getDrawable();
            Bitmap alcoholBit = alcoholDraw.getBitmap();
            PImage alcohol = new PImage(alcoholBit);
            BitmapDrawable coffeeDraw = (BitmapDrawable) Data.coffee.getDrawable();
            Bitmap coffeeBit = coffeeDraw.getBitmap();
            PImage coffee = new PImage(coffeeBit);
            BitmapDrawable tvDraw = (BitmapDrawable) Data.tv.getDrawable();
            Bitmap tvBit = tvDraw.getBitmap();
            PImage tv = new PImage(tvBit);
            BitmapDrawable onthegoDraw = (BitmapDrawable) Data.onthego.getDrawable();
            Bitmap onthegoBit = onthegoDraw.getBitmap();
            PImage onthego = new PImage(onthegoBit);
            BitmapDrawable sexDraw = (BitmapDrawable) Data.sex.getDrawable();
            Bitmap sexBit = sexDraw.getBitmap();
            PImage sex = new PImage(sexBit);
            BitmapDrawable companyDraw = (BitmapDrawable) Data.company.getDrawable();
            Bitmap companyBit = companyDraw.getBitmap();
            PImage company = new PImage(companyBit);
            BitmapDrawable workDraw = (BitmapDrawable) Data.work.getDrawable();
            Bitmap workBit = workDraw.getBitmap();
            PImage work = new PImage(workBit);
            BitmapDrawable othersDraw = (BitmapDrawable) Data.others.getDrawable();
            Bitmap othersBit = othersDraw.getBitmap();
            PImage others = new PImage(othersBit);

            background(220);
            String[] temparray = Data.loadedInput;
            println("temparraysize: " + temparray.length);
            if (temparray != null) {
                Entry[] entryobject = new Entry[temparray.length];
                for (int i = 0; i < temparray.length; i++) {
                    entryobject[i] = new Entry();
                    entryobject[i].setArguments(temparray[i]);
                }

                Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);
                ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<>();
                for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
                    placeHolderArray.add(findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces));
                }

                for (int i = 0; i < placeHolderArray.size(); i++) {
                    imageMode(CENTER);
                    noStroke();
                    fill(255);
                    xcenter = width / 2 + 340 * sin(2 * PI * i / placeHolderArray.size());
                    ycenter = height / 2 + 340 * cos(2 * PI * i / placeHolderArray.size());
                    ellipse(xcenter, ycenter, 100, 100);
                    switch (AmountOfUsedPlaces[i]) {
                        case 0:
                            image(livingroom, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 1:
                            image(kitchen, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 2:
                            image(desArea, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 3:
                            image(outside, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 4:
                            image(car, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 5:
                            image(bar, xcenter, ycenter, placeresx, placeresy);

                            break;
                        case 6:
                            image(other, xcenter, ycenter, placeresx, placeresy);

                            break;
                    }
                    for (int j = 0; j < placeHolderArray.get(i).size(); j++) {
                        xsit = xcenter + 110 * sin(2 * PI * j / placeHolderArray.get(i).size());
                        ysit = ycenter + 110 * cos(2 * PI * j / placeHolderArray.get(i).size());
                        fill(255);
                        ellipse(xsit, ysit, 60, 60);
                        switch (parseInt(tempSituationArrayList.get(i)[j])) {
                            case 0:
                                image(food, xsit, ysit, sitresx, sitresy);
                                break;
                            case 1:
                                image(tv, xsit, ysit, sitresx, sitresy);
                                break;
                            case 2:
                                image(dbreak, xsit, ysit, sitresx, sitresy);
                                break;
                            case 3:
                                image(onthego, xsit, ysit, sitresx, sitresy);
                                break;
                            case 4:
                                image(alcohol, xsit, ysit, sitresx, sitresy);
                                break;
                            case 5:
                                image(company, xsit, ysit, sitresx, sitresy);
                                break;
                            case 6:
                                image(coffee, xsit, ysit, sitresx, sitresy);
                                break;
                            case 7:
                                image(work, xsit, ysit, sitresx, sitresy);
                                break;
                            case 8:
                                image(sex, xsit, ysit, sitresx, sitresy);
                                break;
                            case 9:
                                image(others, xsit, ysit, sitresx, sitresy);
                                break;
                        }

                        for (int k = 0; k < placeHolderArray.get(i).get(j).size(); k++) {
                            xfeel = xsit + 40 * sin(2 * PI * k / placeHolderArray.get(i).get(j).size());
                            yfeel = ysit + 40 * cos(2 * PI * k / placeHolderArray.get(i).get(j).size());

                            switch ((int) placeHolderArray.get(i).get(j).get(k)) {
                                case 0:
                                    fill(255, 18, 54);
                                    break;
                                case 1:
                                    fill(18, 83, 255);
                                    break;
                                case 2:
                                    fill(93, 121, 33);
                                    break;
                                case 3:
                                    fill(255, 235, 8);
                                    break;
                                case 4:
                                    fill(59, 29, 1);
                                    break;
                                case 5:
                                    fill(183, 158, 11);
                                    break;
                                case 6:
                                    fill(59);
                                    break;
                                case 7:
                                    fill(255, 23, 217);
                                    break;
                                case 8:
                                    fill(39, 37, 38);
                                    break;
                                case 9:
                                    fill(245, 152, 2);
                                    break;
                                case 10:
                                    fill(74, 245, 2);
                                    break;
                            }
                            ellipse(xfeel, yfeel, 10, 10);
                        }
                    }
                }
            }
        }
    }


    Integer[] usedPlaces(Entry[] cigObjects) {
        int[] foundPlaces = new int[cigObjects.length];
        for (int i = 0; i < cigObjects.length; i++) {
            foundPlaces[i] = cigObjects[i].place;
        }
        Set<Integer> tempPlaceHolder = findNonDuplicates(foundPlaces);
        Integer[] allPlaces = tempPlaceHolder.toArray(new Integer[tempPlaceHolder.size()]);

        return allPlaces;
    }

    public static Set<Integer> findNonDuplicates(int[] input) {
        Set<Integer> nonDuplicates = new HashSet<Integer>();
        if (input.length == 1) {
            nonDuplicates.add(input[0]);
        } else {
            for (int i = 0; i < input.length; i++) {
                for (int j = 1; j < input.length; j++) {
                    if (input[i] != input[j] && i != j) { // non-duplicate element found
                        nonDuplicates.add(input[i]);
                        break;
                    }
                }
            }
        }
        if (nonDuplicates.size() == 0) {
            nonDuplicates.add(input[0]);
        }
        return nonDuplicates;
    }


    public static Set<Integer> findNonDuplicates(ArrayList<Integer> input) {
        Set<Integer> nonDuplicates = new HashSet<Integer>();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(i) != input.get(j) && i != j) { // non-duplicate element found
                    nonDuplicates.add(input.get(i));
                    break;
                }
            }
        }
        if (nonDuplicates.size() == 0) {
            nonDuplicates.add(input.get(0));
        }

        return nonDuplicates;
    }

    public ArrayList<ArrayList> findSituationAndFeelings(Entry[] entryobject, int currentPlace, Integer[] AmountOfUsedPlaces) {
        Integer[] AmountOfUsedSituation = usedSituations(entryobject, currentPlace, AmountOfUsedPlaces);

        String[] tempSituationArray = new String[AmountOfUsedSituation.length];

        for (int i = 0; i < tempSituationArray.length; i++) {
            tempSituationArray[i] = str(AmountOfUsedSituation[i]);
        }
        tempSituationArrayList.add(tempSituationArray);

        ArrayList<ArrayList> tempSitAndFeelings = new ArrayList<ArrayList>();
        ArrayList<Integer> tempFeeling = new ArrayList<Integer>();

        for (int k = 0; k < AmountOfUsedSituation.length; k++) {
            for (int l = 0; l < entryobject.length; l++) {
                if (AmountOfUsedSituation[k] == entryobject[l].situation && AmountOfUsedPlaces[currentPlace] == entryobject[l].place) {
                    tempFeeling.add(entryobject[l].feeling);
                }
            }
            tempSitAndFeelings.add(tempFeeling);
            tempFeeling = new ArrayList<Integer>();
        }
        return tempSitAndFeelings;
    }


    Integer[] usedSituations(Entry[] cigObjects, int currentPlace, Integer[] AmountOfUsedPlaces) {
        ArrayList<Integer> foundSituations = new ArrayList<Integer>();
        for (int i = 0; i < cigObjects.length; i++) {
            if (AmountOfUsedPlaces[currentPlace] == cigObjects[i].place) {
                foundSituations.add(cigObjects[i].situation);
            }
        }
        Set<Integer> tempSituationsHolder = findNonDuplicates(foundSituations);
        Integer[] allSituations = tempSituationsHolder.toArray(new Integer[tempSituationsHolder.size()]);

        return allSituations;
    }
}
