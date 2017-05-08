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
    float placeres = (float) (width / 5);
    float sitres = (float) (width / 6.5);

    ArrayList<String[]> tempSituationArrayList = new ArrayList<>();

    public void settings() {
        fullScreen();
    }

    public void setup() {
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
//        Data.livingroom
//    }
//}

//        File directory = getApplicationContext().getDir("mydir", Context.MODE_PRIVATE);
//        File file = new File(directory, "savedcigarettes");

//        place image setup
//        livingroom = new ImageView();
//        kitchen = loadImage("kitchen.png");
//        desArea = loadImage("designatedsmokingareas.png");
//        outside = loadImage("outside.png");
//        car = loadImage("inthecar.png");
//        bar = loadImage("atthebar.png");
//        other = loadImage("placeother.png");
//
//        //situation images setup
//        food = loadImage("afterfood.png");
//        dbreak = loadImage("dbreak.png");
//        alcohol = loadImage("alcohol.png");
//        coffee = loadImage("coffeebreak.png");
//        tv = loadImage("watchingtv.png");
//        onthego = loadImage("onthego.png");
//        sex = loadImage("aftersexualintercourse.png");
//        company = loadImage("withcompany.png");
//        work = loadImage("work.png");
//        others = loadImage("other.png");

//        Entry entry;
//        size(1000, 1000);
        String[] temparray = Data.loadedInput;
        println("temparraysize: " + temparray.length);
        if (temparray != null) {
            Entry[] entryobject = new Entry[temparray.length];
            for (int i = 0; i < temparray.length; i++) {
                entryobject[i] = new Entry();
                entryobject[i].setArguments(temparray[i]);
//                println(temparray[i]);
                //                entryobject[i] = new Entry(temparray[i]);
            }
//            println("entrysize: " + entryobject.length);
//            println("entry place:" + entryobject[0].place);
            //function that finds all used places
            Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);
//            println("Amount: " + AmountOfUsedPlaces.length);
            ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<>();
//            ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<ArrayList<ArrayList>>();
            for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
//                println("amount: "+AmountOfUsedPlaces.length);
                placeHolderArray.add(findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces));
            }

            for (int i = 0; i < placeHolderArray.size(); i++) {
                imageMode(CENTER);
                noStroke();
                fill(255);
                xcenter = width / 2 + 200 * sin(2 * PI * i / placeHolderArray.size());
                ycenter = height / 2 + 200 * cos(2 * PI * i / placeHolderArray.size());
                //((i-0.0)/(AmountOfUsedPlaces.length-1))*(width-300)+150;
                ellipse(xcenter, ycenter, 50, 50);
//                println("test: " + placeHolderArray.size());
                switch (AmountOfUsedPlaces[i]) {
                    case 0:
                        image(livingroom, xcenter, ycenter, placeres, placeres);

                        break;
                    case 1:
                        image(kitchen, xcenter, ycenter, placeres, placeres);

                        break;
                    case 2:
                        image(desArea, xcenter, ycenter, placeres, placeres);

                        break;
                    case 3:
                        image(outside, xcenter, ycenter, placeres, placeres);

                        break;
                    case 4:
                        image(car, xcenter, ycenter, placeres, placeres);

                        break;
                    case 5:
                        image(bar, xcenter, ycenter, placeres, placeres);

                        break;
                    case 6:
                        image(other, xcenter, ycenter, placeres, placeres);

                        break;
                }
                for (int j = 0; j < placeHolderArray.get(i).size(); j++) {
                    xsit = xcenter + 60 * sin(2 * PI * j / placeHolderArray.get(i).size());
                    ysit = ycenter + 60 * cos(2 * PI * j / placeHolderArray.get(i).size());
                    fill(255);
                    ellipse(xsit, ysit, 20, 20);
                    switch (parseInt(tempSituationArrayList.get(i)[j])) {
                        case 0:
                            image(food, xsit, ysit, sitres, sitres);
                            break;
                        case 1:
                            image(tv, xsit, ysit, sitres, sitres);
                            break;
                        case 2:
                            image(dbreak, xsit, ysit, sitres, sitres);
                            break;
                        case 3:
                            image(onthego, xsit, ysit, sitres, sitres);
                            break;
                        case 4:
                            image(alcohol, xsit, ysit, sitres, sitres);
                            break;
                        case 5:
                            image(company, xsit, ysit, sitres, sitres);
                            break;
                        case 6:
                            image(coffee, xsit, ysit, sitres, sitres);
                            break;
                        case 7:
                            image(work, xsit, ysit, sitres, sitres);
                            break;
                        case 8:
                            image(sex, xsit, ysit, sitres, sitres);
                            break;
                        case 9:
                            image(others, xsit, ysit, sitres, sitres);
                            break;
                    }

                    for (int k = 0; k < placeHolderArray.get(i).get(j).size(); k++) {
                        xfeel = xsit + 20 * sin(2 * PI * k / placeHolderArray.get(i).get(j).size());
                        yfeel = ysit + 20 * cos(2 * PI * k / placeHolderArray.get(i).get(j).size());

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
                                fill(180, 80, 29);
                                break;
                            case 5:
                                fill(183, 158, 11);
                                break;
                            case 6:
                                fill(134, 134, 134);
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
                        ellipse(xfeel, yfeel, 5, 5);
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
        println("1: " + foundPlaces.length);
        Set<Integer> tempPlaceHolder = findNonDuplicates(foundPlaces);
        println("2: " + tempPlaceHolder.size());
        Integer[] allPlaces = tempPlaceHolder.toArray(new Integer[tempPlaceHolder.size()]);

        return allPlaces;
    }

    public static Set<Integer> findNonDuplicates(int[] input) {
        Set<Integer> nonDuplicates = new HashSet<Integer>();
        println("input længde: " + input.length);
        if (input.length == 1) {
            nonDuplicates.add(input[0]);
        } else {
            for (int i = 0; i < input.length; i++) {
                for (int j = 1; j < input.length; j++) {
                    println("comes here: " + input[i] + " , " + input[j]);
                    if (input[i] != input[j] && i != j) { // non-duplicate element found
                        nonDuplicates.add(input[i]);
                        println("creates29;");
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
            println("tempsit: " + tempSituationArray[i]);
        }
        tempSituationArrayList.add(tempSituationArray);
        println("size of ArrayList: " + tempSituationArrayList.size());

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

        for (int i = 0; i < tempSitAndFeelings.size(); i++) {
            println("Place: " + AmountOfUsedPlaces[currentPlace] + ", Situation; " + tempSitAndFeelings.get(i) + " feeling size: " + tempSitAndFeelings.get(i).size());
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
