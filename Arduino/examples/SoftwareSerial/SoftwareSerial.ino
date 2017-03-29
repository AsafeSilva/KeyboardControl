#include <SoftwareSerial.h>
#include "KeyboardControl.h"

SoftwareSerial serial(7, 8);

KeyboardControl key(&serial);

void setup() {
  serial.begin(9600);

  delay(3000);
}

void loop() {

  key.keyClick(KC_A);
  key.keyClick(KC_R);
  key.keyClick(KC_D);
  key.keyClick(KC_U);
  key.keyClick(KC_I);
  key.keyClick(KC_N);
  key.keyClick(KC_O);

  while(true){}

}
