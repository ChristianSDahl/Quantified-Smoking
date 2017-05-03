import java.util.Date;

//initial values
int numberOfLines = 2;
float heightPerLine = height / numberOfLines;

//Import data
String[] Lines = loadStrings ("Cigarettes.txt"); //Will be saved in the sketch's data folder

float [] timeArray = new float [numberOfLines];
float [] cigaretteArray = new float [numberOfLines];
float [] moneyArray = new float [numberOfLines];

for (int i = 1; i < numberOfLines+i; i++) {
  
  String time = split(Lines[i], ',' )[0]; //Takes the 0th element of a line
  String cigarette = split(Lines[i], ',' )[1]; //Takes the 1st element of a line
  String money = split(Lines[i], ',' )[2]; //Takes the 2nd element of a line
  
  float timeFloat = float(time.substring(1));
  float cigaretteFloat = float(cigarette);
  float moneyFloat = float(money);
  
  timeArray[i-1] = timeFloat;
  cigaretteArray[i-1] = cigaretteFloat;
  moneyArray[i-1] = moneyFloat;
}

void setup() {
//Create visualization
fullScreen(); //Window size
background(255); //White

line(width/30,height/30,width/30,height/2); //y-axis
line(width/30,height/2,width-40,height/2); //x-axis

//Time on the x-axis
fill(0);
text("00:00",width/35,(height/2)+20);
text("03:00",(width/2)/4,(height/2)+20);
text("06:00",(width/2)/2,(height/2)+20);
text("09:00",((width/2)/4)*3,(height/2)+20);
text("12:00",width/2,(height/2)+20);
text("15:00",((width/2)/4)*5,(height/2)+20);
text("18:00",(width/2)/2*3,(height/2)+20);
text("21:00",((width/2)/4)*7,(height/2)+20);
text("24:00",width-50,(height/2)+20);

//Number of cigarettes on the y-axis
text("0",width/40,height/2);
text("1",width/40,(height/2)-25);
text("2",width/40,(height/2)-50);
text("3",width/40,(height/2)-75);
text("4",width/40,(height/2)-100);
text("5",width/40,(height/2)-125);
text("6",width/40,(height/2)-150);
text("7",width/40,(height/2)-175);
text("8",width/40,(height/2)-200);
text("9",width/40,(height/2)-225);
text("10",width/43,(height/2)-250);
text("11",width/43,(height/2)-275);
text("12",width/43,(height/2)-300);
text("13",width/43,(height/2)-325);
text("14",width/43,(height/2)-350);
text("15",width/43,(height/2)-375);
text("16",width/43,(height/2)-400);
text("17",width/43,(height/2)-425);
text("18",width/43,(height/2)-450);
text("19",width/43,(height/2)-475);
text("20",width/43,(height/2)-500);

textSize(15);
text("Cigarettes",width/80,(height/2)-520); //
text("Time",width-60,(height/2)+40);

float maxNum0 = max(timeArray);
float maxNum1 = max(cigaretteArray);
float maxNum2 = max(moneyArray);

//Graph
for (int i = 0; i < timeArray.length; i++) {
  float Ratio = timeArray[i] / maxNum0;
  fill(Ratio * 255, 0, 0); //Red, Green, Blue
  rect(0, i*heightPerLine, Ratio*width, heightPerLine);
}
}

void draw() {
  Date d = new Date();
  long current = d.getTime()/1000; //Divide by 1000 since unix time_t is seconds since the epoch, but getTime returns milliseconds since the epoch.
  println(current);
}