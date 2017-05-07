class Entry {
  public long ms;
  public int place, situation, feeling;
  public int[] feelarray;
  private int placeint = 0;


  Entry(String cigarette) {

    place = placetoint(split(cigarette, ",")[1]);
    situation = situationtoint(split(cigarette, ",")[2]);
    feeling = feelingtoint(split(cigarette, ",")[3]);
    println("feeling logged; " + feeling);
    //feelarray[]=Integer.parseInt(feeling);
  }
  public int placetoint(String place) {
    println("Place: " + place);
    if (place.equals("Living room")) {
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
    } else if (feeling.equals("Other")) {
      feelint = 7;
    }
    return feelint;
  }
}