import processing.serial.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

Serial serialPort;
Robot robot;
Frame frame;

boolean enableReading;

void setup() {
  surface.setVisible(false);

  Frame frame = new Frame(this);

  frame.setOnToggleCallback(new OnToggleCallback() {
    @Override
      public void onToggleCallback(boolean state) {
      enableReading = state;
    }
  }
  );

  try {
    robot = new Robot();
  } 
  catch (AWTException e) {
    e.printStackTrace();
  }
}

void draw() {
}


void serialEvent(Serial serial) { 
  if (!enableReading) return;

  String data = serial.readString().trim();  
  if (data == null) return;

  String[] keycode = split(data, '|');

  int code = Integer.parseInt(keycode[1]);

  try {
    if (keycode[0].equals("KP")) {
      robot.keyPress(code);
    } else if (keycode[0].equals("KR")) {
      robot.keyRelease(code);
    } else if (keycode[0].equals("KC")) {
      robot.keyPress(code);
      robot.keyRelease(code);
    }
  }
  catch(Exception e) {
    e.printStackTrace();
  }
} 