//<>// //<>//
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;



//place
PImage livingroom, kitchen, desArea, outside, car, bar, other;
//situation
PImage food, dbreak, alcohol, coffee, tv, onthego, sex, company, work, others;
float placeres = width/5;
float sitres = width/6.5;

ArrayList<String[]> tempSituationArrayList = new ArrayList<String[]>();

void setup() {
  float xcenter, ycenter, xsit, ysit, xfeel, yfeel;

  //place image setup
  livingroom = loadImage("livingroom.png");
  kitchen = loadImage("kitchen.png");
  desArea = loadImage("designatedsmokingareas.png");
  outside = loadImage("outside.png");
  car = loadImage("inthecar.png");
  bar = loadImage("atthebar.png");
  other = loadImage("placeother.png");

  //situation images setup
  food = loadImage("after food.png");
  dbreak = loadImage("break icon.png");
  alcohol = loadImage("alcohol nocol.png");
  coffee = loadImage("coffee break.png");
  tv = loadImage("watchingtv.png");
  onthego = loadImage("onthego.png");
  sex = loadImage("after sexual intercause.png");
  company = loadImage("withcompany.png");
  work = loadImage("work.png");
  others = loadImage("other.png");

  //Entry entry;
  size(1000, 1000);
  String[] temparray = loadStrings("entrydata.txt");

  Entry[] entryobject = new Entry[temparray.length];
  for (int i = 0; i < temparray.length; i++) {
    entryobject[i] = new Entry(temparray[i]);
  }
  //function that finds all used places
  Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);
  ArrayList<ArrayList<ArrayList>> placeHolderArray = new ArrayList<ArrayList<ArrayList>>();

  for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
    placeHolderArray.add(findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces));
  }

  for (int i = 0; i < placeHolderArray.size(); i++) {
    imageMode(CENTER);
    noStroke();
    fill(255);
    xcenter = width/2 + 200*sin(2*PI*i/placeHolderArray.size());
    ycenter = height/2 + 200*cos(2*PI*i/placeHolderArray.size());
    //((i-0.0)/(AmountOfUsedPlaces.length-1))*(width-300)+150;
    ellipse(xcenter, ycenter, 50, 50);
    switch((int) AmountOfUsedPlaces[i]) {
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
      xsit = xcenter+60*sin(2*PI*j/placeHolderArray.get(i).size());
      ysit = ycenter+60*cos(2*PI*j/placeHolderArray.get(i).size());
      fill(255);
      ellipse(xsit, ysit, 20, 20);
      switch(parseInt(tempSituationArrayList.get(i)[j])) {
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
        xfeel = xsit + 20*sin(2*PI*k/placeHolderArray.get(i).get(j).size());
        yfeel = ysit + 20*cos(2*PI*k/placeHolderArray.get(i).get(j).size());

        switch((int) placeHolderArray.get(i).get(j).get(k)) {
        case 0:
          fill(#FF1236);
          break;
        case 1:
          fill(#1253FF);
          break;          
        case 2:
          fill(#5D7921);
          break;          
        case 3:
          fill(#FFEB08);
          break;          
        case 4:
          fill(#B4501D);
          break;          
        case 5:
          fill(#B79E0B);
          break;          
        case 6:
          fill(#868685);
          break;          
        case 7:
          fill(#FF17D9);
          break;
        case 8:
          fill(#272526);
          break;
        case 9:
          fill(#F59802);
          break;
        case 10:
          fill(#4AF502);
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


  String[] tempSituationArray = new String[AmountOfUsedSituation.length];

  for (int i = 0; i < tempSituationArray.length; i++) {
    tempSituationArray[i] = str(AmountOfUsedSituation[i]); 
    println("tempsit: " + tempSituationArray[i]);
  }
  tempSituationArrayList.add(tempSituationArray);
  println("size of ArrayList: " + tempSituationArrayList.size());

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