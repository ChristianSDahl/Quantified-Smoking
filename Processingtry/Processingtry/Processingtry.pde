import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.Object;
import java.text.FieldPosition;

String[] lines = loadStrings("quickinput.txt");
int numberOfLines = lines.length;

int x = 0;
int y = width-20;

Date d;
SimpleDateFormat sdf;
Calendar cal;


d = new Date(Long.parseLong(lines[0]));
sdf = new SimpleDateFormat();

size(900,900);

background(255);
float start = float(lines[0]);
float end = float(lines[lines.length-1]);

//fill(0);

//int epoch = 1225359677;
for (int i = 0; i < lines.length; i++) {
  d = new Date(Long.parseLong(lines[i]));
  String date = new java.text.SimpleDateFormat("h:mm a").format(d);
  println(date);  
  fill(0);
  line(800,0,800,height);
  
  rect(((float(lines[i]))/end)*width,y,3,3);



  y -= 20;
}

//StringBuffer toAppendTo = new StringBuffer();
//FieldPosition pos = 

//sdf.format(d,toAppendTo,0);