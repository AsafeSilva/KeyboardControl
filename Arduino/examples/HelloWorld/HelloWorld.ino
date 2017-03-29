#include "KeyboardControl.h"

KeyboardControl key;

void setup() {
  Serial.begin(9600);

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
