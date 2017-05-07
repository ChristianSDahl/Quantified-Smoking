
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;


void setup() {
  //Entry entry;
  size(1000, 1000);
  String[] temparray = loadStrings("entrydata.txt");
  Entry[] entryobject = new Entry[temparray.length];
  for (int i = 0; i < temparray.length; i++) {
    entryobject[i] = new Entry(temparray[i]);
  }
  //function that finds all used places
  Integer[] AmountOfUsedPlaces = usedPlaces(entryobject);
  ArrayList<ArrayList> placeHolderArray = new ArrayList<ArrayList>();

  //for (int i = 0; i < AmountOfUsedPlaces.length;i++) {
  for (int i = 0; i < 1; i++) {
    findSituationAndFeelings(entryobject, i, AmountOfUsedPlaces);

    //for (int j = 0; j < entryobject.length;j++) {
    //   if (entryobject[j].place == i) {
    //     if ( placeHolderArray.get(i).size() == 0){
    //       //method needed that creates a arraylist iwth String arrray contained within. String array contains strings of each feeling

    //       //find all feelings here
    //       //placeHolderArray.get(i).add(//method call here);
    //     }
    //      for (int k = 0; k < placeHolderArray.get(i).size(); j++) {
    //        println("testing");
    //      }
    //   }
    //}
  }

  //println(entry[]);
  for (int i = 0; i < AmountOfUsedPlaces.length; i++) {
    fill(0);
    float xcenter = ((i-0.0)/(AmountOfUsedPlaces.length-1))*(width-300)+150;
    ellipse(xcenter, height/2, 50, 50);
    //println(((i-0.0)/(AmountOfUsedPlaces.length-1)));
    //draw place here
    for (int j = 0; j < entryobject.length; j++) {
      if (entryobject[j].place == i) {
        line(xcenter, height/2, xcenter+50, height/2);
        //draw entryobject[i].situation
        for (int k = 0; k < entryobject.length; k++) {
          if ( entryobject[k].place ==  i && entryobject[k].situation == j) {
            //draw entryobject[k].feeling;
          }
        }
      }
    }
  }
}

Integer[] usedPlaces(Entry[] cigObjects) {
  //ArrayList<String> list;
  //new ArrayList<String>(Arrays.asList("a", "b", "c"));
  int[] foundPlaces = new int[cigObjects.length];
  for (int i = 0; i < cigObjects.length; i++) {
    foundPlaces[i] = cigObjects[i].place;
  }
  Set<Integer> tempPlaceHolder = findNonDuplicates(foundPlaces);
  Integer[] allPlaces = tempPlaceHolder.toArray(new Integer[tempPlaceHolder.size()]);

  //int[] intArray = Arrays.stream(integerArray).mapToInt(i->i).toArray();

  //int[] allPlaces = integerArray.toPrimitive();
  for (int i = 0; i < allPlaces.length; i++) {
    //println("amount of non-duplicates: " + allPlaces.length);
    //println("test: " + allPlaces[i]);
  }

  return allPlaces;
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

public static Set<Integer> findNonDuplicates(ArrayList<Integer> input) { 
  //Integer[] wrapperArr = tempinput.toArray(new Integer[tempinput.size()]);

  //// If you want a `primitive` type array
  //int[] arr = ArrayUtils.toPrimitive(wrapperArr);
  //int[] ints = Ints.toArray(tempinput);



  Set<Integer> nonDuplicates = new HashSet<Integer>(); 
  for (int i = 0; i < input.size(); i++) { 
    for (int j = 0; j < input.size(); j++) { 
      if (input.get(i) != input.get(j) && i != j) { // non-duplicate element found 
        nonDuplicates.add(input.get(i));
        //println("creates");
        break;
      }
    }
  } 
  for (int i = 0; i < input.size(); i++) {
    //println("input: " + input.get(i));
  }
  for (int i = 0; i < nonDuplicates.size(); i++) {
    //println("input: " + nonDuplicates);
  }


  //println("size of nonDuplicates: " + nonDuplicates.size());
  //println("size of input: " + input.size());
  return nonDuplicates;
}

public ArrayList findSituationAndFeelings(Entry[] entryobject, int currentPlace, Integer[] AmountOfUsedPlaces) {
  //for (int i = 0; i < entryobject.length; i++) {
  Integer[] AmountOfUsedSituation = usedSituations(entryobject, currentPlace, AmountOfUsedPlaces);
  //}
  for ( int i = 0; i < AmountOfUsedSituation.length; i++) {
    println("Situation: " + AmountOfUsedSituation[i]);
  }

  ArrayList <ArrayList> tempSitAndFeelings = new ArrayList<ArrayList>(10);
  ArrayList <Integer> tempFeeling = new ArrayList<Integer>();
  for (int j = 0; j < AmountOfUsedPlaces.length; j++) {
    for (int i = 0; i < entryobject.length; i++) {
      if (AmountOfUsedPlaces[j] == currentPlace) {
        //println("Place: " + AmountOfUsedPlaces[j] + ", supposed place: " + currentPlace);
        for ( int k = 0; k < AmountOfUsedSituation.length; k++) {
          if (AmountOfUsedSituation[k] == entryobject[i].situation && AmountOfUsedPlaces[j] == entryobject[i].place) {
            println("place: " + currentPlace + ", situation: " + entryobject[i].situation + ", feeling: " + entryobject[i].feeling + ", i: " + i);
            tempFeeling.add(entryobject[i].feeling);
          }
          tempSitAndFeelings.get(Integer.parseInt(str(AmountOfUsedSituation[j]))).add(tempFeeling);
          println(tempSitAndFeelings.size());
        }
      }
    }
  }

  return null;
}


Integer[] usedSituations(Entry[] cigObjects, int currentPlace, Integer[] AmountOfUsedPlaces) {
  //ArrayList<String> list;
  //new ArrayList<String>(Arrays.asList("a", "b", "c"));
  ArrayList<Integer> foundSituations = new ArrayList<Integer>();
  //int[] foundSituations;
  for (int i = 0; i < cigObjects.length; i++) {
    //for (int j = 0; j < AmountOfUsedPlaces.length; j++) {
    if (AmountOfUsedPlaces[currentPlace] == cigObjects[i].place) {
      //foundSituations = new int[i];
      foundSituations.add(cigObjects[i].situation);
    }
    //}
  }
  for (int j = 0; j < foundSituations.size(); j++) {
    //println("found this situation: " + foundSituations.get(j));
  }

  println(foundSituations.size());
  Set<Integer> tempSituationsHolder = findNonDuplicates(foundSituations);
  Integer[] allSituations = tempSituationsHolder.toArray(new Integer[tempSituationsHolder.size()]);
  //println("amount of nonDuplicate situations for this place: " + tempSituationsHolder.size());
  for (int i = 0; i < allSituations.length; i++) {
    //println("amount of non-duplicates: " + allSituations.length);
    //println("test: " + allSituations[i]);
  }

  return allSituations;
}