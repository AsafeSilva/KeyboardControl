import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.KeyEvent; 
import java.awt.event.ItemEvent; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class KeyboardControl extends PApplet {






Serial serialPort;
Robot robot;
Frame frame;

boolean enableReading;

public void setup() {
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

public void draw() {
}


public void serialEvent(Serial serial) { 
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


public class Frame extends javax.swing.JFrame implements java.awt.event.ItemListener {

  private PApplet app;

  /**
   * Creates new form NovoJFrame
   */
  public Frame(PApplet app) {
    this.app = app;

    initComponents();

    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void initComponents() {

    listCommPorts = new javax.swing.JComboBox();
    txtStatusSerial = new javax.swing.JLabel();
    btnEnableReading = new javax.swing.JToggleButton();
    txtEnableReading = new javax.swing.JLabel();
    btnSearchPorts = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Keyboard Control");
    setResizable(false);

    txtStatusSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    txtStatusSerial.setText("Search available ports...");
    txtStatusSerial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    btnEnableReading.setText("READ DATA");
    btnEnableReading.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnEnableReadingActionPerformed(evt);
      }
    }
    );

    txtEnableReading.setText("Read disabled");

    btnSearchPorts.setText("Search");
    btnSearchPorts.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSearchPortsActionPerformed(evt);
      }
    }
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addContainerGap(27, Short.MAX_VALUE)
      .addComponent(btnSearchPorts)
      .addGap(18, 18, 18)
      .addComponent(listCommPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
      .addGap(42, 42, 42))
      .addGroup(layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGap(49, 49, 49)
      .addComponent(txtStatusSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
      .addGap(91, 91, 91)
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
      .addComponent(btnEnableReading)
      .addComponent(txtEnableReading))))
      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSearchPorts, listCommPorts});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap(30, Short.MAX_VALUE)
      .addComponent(txtStatusSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
      .addComponent(btnSearchPorts)
      .addComponent(listCommPorts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(btnEnableReading)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(txtEnableReading)
      .addGap(22, 22, 22))
      );

    pack();
  }// </editor-fold>  

  private void btnEnableReadingActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    // TODO add your handling code here:
    txtEnableReading.setText(btnEnableReading.isSelected() ? "Read enabled" : "Read disabled");

    if (_OnToggleCallback != null) 
      _OnToggleCallback.onToggleCallback(btnEnableReading.isSelected());
  }                                                

  private void listCommPortsItemStateChanged(java.awt.event.ItemEvent evt) {                                               
    // TODO add your handling code here:
    if (evt.getStateChange() == ItemEvent.SELECTED) {

      try {
        String portName = listCommPorts.getSelectedItem().toString();
        serialPort = new Serial(this.app, portName, 9600);
        txtStatusSerial.setText("Conected! - " + portName);
        
        serialPort.bufferUntil('\n');
      }
      catch(Exception e) {
        e.printStackTrace();
        serialPort = null;
        txtStatusSerial.setText("Error trying to connect!");
      }
    }
  }                                              

  private void btnSearchPortsActionPerformed(java.awt.event.ActionEvent evt) {                                               

    listCommPorts.removeItemListener(this);

    String[] ports = Serial.list();
    listCommPorts.removeAllItems();
    for (String name : ports)    listCommPorts.addItem(name);

    listCommPorts.setSelectedIndex(-1);   
    listCommPorts.addItemListener(this);

    txtStatusSerial.setText("Select a serial port...");
  }

  public void itemStateChanged(java.awt.event.ItemEvent evt) {
    listCommPortsItemStateChanged(evt);
  }

  // Variables declaration - do not modify                     
  private javax.swing.JToggleButton btnEnableReading;
  private javax.swing.JButton btnSearchPorts;
  private javax.swing.JComboBox<String> listCommPorts;
  private javax.swing.JLabel txtEnableReading;
  private javax.swing.JLabel txtStatusSerial;
  // End of variables declaration

  OnToggleCallback _OnToggleCallback;

  public void setOnToggleCallback(OnToggleCallback _OnToggleCallback) {
    this._OnToggleCallback = _OnToggleCallback;
  }
}

interface OnToggleCallback {
  public void onToggleCallback(boolean state);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "KeyboardControl" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
