#include "KeyboardControl.h"

KeyboardControl key;

void setup() {
  Serial.begin(9600);

  delay(3000);
}

void loop() {

  // "TEST" 
  key.keyPress(KC_T);
  key.keyPress(KC_E);
  key.keyPress(KC_S);
  key.keyPress(KC_T);
  
  // "@"
  key.keyPress(KC_SHIFT);
  key.keyPress(KC_2);
  key.keyRelease(KC_SHIFT);
  
  // "EMAIL" 
  key.keyPress(KC_E);
  key.keyPress(KC_M);
  key.keyPress(KC_A);
  key.keyPress(KC_I);
  key.keyPress(KC_L);

  while(true){}

}
