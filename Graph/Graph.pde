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
  float cigaretteFloat = float(cigarette.substring(1));
  float moneyFloat = float(money.substring(1));
  
  timeArray[i-1] = timeFloat;
  cigaretteArray[i-1] = cigaretteFloat;
  moneyArray[i-1] = moneyFloat;
}

//Create visualization
fullScreen(); //Window size
background(255); //White

line(width/30,height/12,width/30,height-40); //y-axis
line(width/30,height-40,width-40,height-40); //x-axis

//Time on the x-axis
fill(0);
text("00:00",width/35,height-20);
text("03:00",(width/2)/4,height-20);
text("06:00",(width/2)/2,height-20);
text("09:00",((width/2)/4)*3,height-20);
text("12:00",width/2,height-20);
text("15:00",((width/2)/4)*5,height-20);
text("18:00",(width/2)/2*3,height-20);
text("21:00",((width/2)/4)*7,height-20);
text("24:00",width-50,height-20);

//Number of cigarettes on the y-axis
text("0",width/40,height-35);
text("1",width/40,height-60);
text("2",width/40,height-85);
text("3",width/40,height-110);
text("4",width/40,height-135);
text("5",width/40,height-160);
text("6",width/40,height-185);
text("7",width/40,height-210);
text("8",width/40,height-235);
text("9",width/40,height-260);
text("10",width/43,height-285);
text("11",width/43,height-310);
text("12",width/43,height-335);
text("13",width/43,height-360);
text("14",width/43,height-385);
text("15",width/43,height-410);
text("16",width/43,height-435);
text("17",width/43,height-460);
text("18",width/43,height-485);
text("19",width/43,height-510);
text("20",width/43,height-535);

float maxNum0 = max(timeArray);
float maxNum1 = max(cigaretteArray);
float maxNum2 = max(moneyArray);

//Graph
for (int i = 0; i < timeArray.length; i++) {
  float Ratio = timeArray[i] / maxNum0;
  fill(Ratio * 255, 0, 0); //Red, Green, Blue
  rect(0, i*heightPerLine, Ratio*width, heightPerLine);
}