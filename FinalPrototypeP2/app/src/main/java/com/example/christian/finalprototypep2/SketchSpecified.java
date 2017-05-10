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

//extends the class SketchSpecified with the class PApplet with has several methods
//known from the IDE processing.org  //such as setup(),fullScreen() and much more
public class SketchSpecified extends PApplet {
    float placeresx = (float) ((width / 5) * 4);
    float placeresy = (float) ((height / 7) * 4);

    float sitresx = (float) ((width / 6.5) * 3);
    float sitresy = (float) ((height / 6.5) * 3);

    ArrayList<String[]> tempSituationArrayList = new ArrayList<>();

    public void settings() {
        fullScreen();
    }

    public void setup() {
        //if statement to ensure that the program doesn't try to handle with a null object
        if (Data.loadedInput != null) {
            //Variables for various x and y's depending on place,situation and feeling
            float xcenter, ycenter, xsit, ysit, xfeel, yfeel;

            //Creates a BitmapDrawable object with the BitmapDrawable representation of the imageview
            //defined in the Data class
            BitmapDrawable livingroomDraw = (BitmapDrawable) Data.livingroom.getDrawable();
            //Creates a Bitmap from the BitmapDrawable object
            Bitmap livingroomBit = livingroomDraw.getBitmap();
            //Uses the object from the library processing-core.jar with the Bitmap
            //A conversion from ImageView (androidstudio) to PImage (processing) has occured
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
            //For easier codewriting the contents of the String[] loadedInput is stored
            //in a new String[] called temparray
            String[] temparray = Data.loadedInput;
            if (temparray != null) {
                //creates an array of Entry objects
                Entry[] entryobject = new Entry[temparray.length];
                for (int i = 0; i < temparray.length; i++) {
                    //For each element of the object a new Entry object is instantiated
                    entryobject[i] = new Entry();
                    //Uses the Entry object's method setArguments to store the variables
                    //defining each cigarette into the object
                    entryobject[i].setArguments(temparray[i]);
                }

                //Calls the method usedPlaces and saves the returned Integer[] object in
                //the variable AmountOfUsedPlaces
                //By looking through all Entry objects the method usedPlaces
                //finds all non-duplicates of places and returns those in a Integer[]
                Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);

                //Creation of placeHolderArray which is an ArrayList which contains ArrayList's
                //which also holds ArrayList
                //This is the way we've categorized our data. The upper ArrayList determins each place
                //The section below determins situation for each place
                //And finally the last ArrayList determins the feelings for that specific situation
                //and place
                ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<>();
                for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
                    //Calls method that depending on amount of places and which place is being handled
                    //an ArrayList with a specified size and ArrayList within elements containing
                    //feelings
                    placeHolderArray.add(findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces));
                }

                //The visualization
                //Goes through each place
                for (int i = 0; i < placeHolderArray.size(); i++) {
                    imageMode(CENTER);
                    noStroke();
                    fill(255);
                    //Depending on which element is handled, x/y variables are defined
                    xcenter = width / 2 + 340 * sin(2 * PI * i / placeHolderArray.size());
                    ycenter = height / 2 + 340 * cos(2 * PI * i / placeHolderArray.size());
                    //Draws an ellipse at the defined x/y with a radius of 100
                    ellipse(xcenter, ycenter, 100, 100);
                    //Switch to figure out which picture is supposed to show
                    //Each image defines a place in which the cigarette is smoked in
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
                    //For each place, the following code then goes through that specific place's
                    //situations.
                    for (int j = 0; j < placeHolderArray.get(i).size(); j++) {
                        //defines x/y for the situation
                        xsit = xcenter + 110 * sin(2 * PI * j / placeHolderArray.get(i).size());
                        ysit = ycenter + 110 * cos(2 * PI * j / placeHolderArray.get(i).size());
                        fill(255);
                        ellipse(xsit, ysit, 60, 60);
                        //finds which situation picture to show
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

                        //Lastly goes for each place and their specific situations, the respective
                        //feelings
                        for (int k = 0; k < placeHolderArray.get(i).get(j).size(); k++) {
                            //defines x/y for the feeling ellipse
                            xfeel = xsit + 40 * sin(2 * PI * k / placeHolderArray.get(i).get(j).size());
                            yfeel = ysit + 40 * cos(2 * PI * k / placeHolderArray.get(i).get(j).size());
                            //Figures out depending on what feeling is being handled
                            //the color of the ellipse which is going to indicate the feeling
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
                            //draws feeling
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
