 //<>//
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;


void setup() {
  float xsit, ysit, xfeel, yfeel;

  //Entry entry;
  size(1000, 1000);
  String[] temparray = loadStrings("entrydata.txt");
  Entry[] entryobject = new Entry[temparray.length];
  for (int i = 0; i < temparray.length; i++) {
    entryobject[i] = new Entry(temparray[i]);
  }
  //function that finds all used places
  Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);
  //println("Placesamount: " + AmountOfUsedPlaces.length);
  ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<ArrayList<ArrayList>>();

  //for (int i = 0; i < AmountOfUsedPlaces.length;i++) {
  for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
    placeHolderArray.add(findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces));
  }
  println("placeHolderArray size: " + placeHolderArray.size());

  //println(entry[]);
  for (int i = 0; i < placeHolderArray.size(); i++) {
    fill(0);
    float xcenter = ((i-0.0)/(AmountOfUsedPlaces.length-1))*(width-300)+150;
    ellipse(xcenter, height/2, 50, 50);
    for (int j = 0; j < placeHolderArray.get(i).size(); j++) {
      xsit = xcenter+60*sin(2*PI*j/placeHolderArray.get(i).size());
      ysit = height/2+60*cos(2*PI*j/placeHolderArray.get(i).size());
      fill(0);
      ellipse(xsit, ysit, 20, 20);

      println(placeHolderArray.get(i).get(j).size());
      for (int k = 0; k < placeHolderArray.get(i).get(j).size(); k++) {
        xfeel = xsit + 20*sin(2*PI*k/placeHolderArray.get(i).get(j).size());
        yfeel = ysit + 20*cos(2*PI*k/placeHolderArray.get(i).get(j).size());
        
        noStroke();
        switch((int) placeHolderArray.get(i).get(j).get(k)) {
        case 0:
          fill(51, 51, 225);
          break;
        case 1:
          fill(0,200,150);
          break;          
        case 2:
          fill(153,80,0);
          break;          
        case 3:
          fill(255,0,0);
          break;          
        case 4:
          fill(255,0,255);
          break;          
        case 5:
          fill(0,50,0);
          break;          
        case 6:
          fill(0,50,150);
          break;          
        case 7:
          fill(150,0,150);
          break;
        }
        ellipse(xfeel, yfeel, 5, 5);
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
  for (int i = 0; i < input.length; i++) { 
    for (int j = 0; j < input.length; j++) { 
      if (input[i] != input[j] && i != j) { // non-duplicate element found 
        nonDuplicates.add(input[i]);
        break;
      }
    }
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

public ArrayList findSituationAndFeelings(Entry[] entryobject, int currentPlace, Integer[] AmountOfUsedPlaces) {
  Integer[] AmountOfUsedSituation = usedSituations(entryobject, currentPlace, AmountOfUsedPlaces);

  ArrayList <ArrayList> tempSitAndFeelings = new ArrayList<ArrayList>();
  ArrayList <Integer> tempFeeling = new ArrayList<Integer>();

  for ( int k = 0; k < AmountOfUsedSituation.length; k++) {
    for (int l = 0; l < entryobject.length; l++) {
      if (AmountOfUsedSituation[k] == entryobject[l].situation && AmountOfUsedPlaces[currentPlace] == entryobject[l].place) {
        tempFeeling.add(entryobject[l].feeling);
      }
    }
    tempSitAndFeelings.add(tempFeeling);
    tempFeeling = new ArrayList<Integer>();
  }

  for (int i = 0; i < tempSitAndFeelings.size(); i++) {
    println("Place: "+ AmountOfUsedPlaces[currentPlace] + ", Situation; " + tempSitAndFeelings.get(i) + " feeling size: " + tempSitAndFeelings.get(i).size());
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