package nolse16aaumed16.specifiedinputvisualization;

import processing.core.PApplet;

import java.util.*;
import java.util.stream.*;
import java.util.Arrays;


public class Sketch extends PApplet {
    public void settings() {
        size(600, 600);

    }

    public void setup() {
        String[] temparray = loadStrings("entrydata.txt");
        Entry[] entryobject = new Entry[temparray.length];
        for (
                int i = 0;
                i < temparray.length; i++)

        {
            entryobject[i] = new Entry(temparray[i]);
        }

        //function that finds all used places
        usedPlaces(entryobject);

        //println(entry[]);
        for (
                int i = 0;
                i < 9; i++)

        {
            //draw place here
            for (int j = 0; j < entryobject.length; j++) {
                if (entryobject[j].place == i) {
                    //draw entryobject[i].situation
                    for (int k = 0; k < entryobject.length; k++) {
                        if (entryobject[k].place == i && entryobject[k].situation == j) {
                            //draw entryobject[k].feeling;
                        }
                    }
                }
            }
        }
    }


    String[] usedPlaces(Entry[] cigObjects) {
        //ArrayList<String> list;
        //new ArrayList<String>(Arrays.asList("a", "b", "c"));
        int[] foundPlaces = new int[cigObjects.length];
        for (int i = 0; i < cigObjects.length; i++) {
            foundPlaces[i] = cigObjects[i].place;
        }
        Set<Integer> tempPlaceHolder = findNonDuplicates(foundPlaces);
        Integer[] allPlaces = tempPlaceHolder.toArray(new Integer[tempPlaceHolder.size()]);

//        int[] intArray = Arrays.stream(integerArray).mapToInt(i->i).toArray();

//        int[] allPlaces = integerArray.toPrimitive();
        for(int i = 0; i < allPlaces.length;i++) {
            println("amount of non-duplicates: " + allPlaces.length);
//            println("test: " + Arrays.toString(allPlaces.toArray()));
        }

        return null;
    }

    public static Set<Integer> findNonDuplicates(int[] input) {
        Set<Integer> nonDuplicates = new HashSet<Integer>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length; j++) {
                if (input[i] != input[j] && i != j) { // non-duplicate element found
                    nonDuplicates.add(input[i]);
                    break;
                }
            }
        }
        return nonDuplicates;
    }

    public void draw() {

    }
}
