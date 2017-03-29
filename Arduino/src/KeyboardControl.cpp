#include "KeyboardControl.h"

KeyboardControl::KeyboardControl(){
	serial = NULL;
}

KeyboardControl::KeyboardControl(SoftwareSerial *_serial){
	serial = _serial;
}

void KeyboardControl::keyPress(int keycode){
	sendData("KP|" + String(keycode));
}

void KeyboardControl::keyRelease(int keycode){
	sendData("KR|" + String(keycode));
}

void KeyboardControl::keyClick(int keycode){
	sendData("KC|" + String(keycode));
}

void KeyboardControl::sendData(String keycode){
	if (serial == NULL)	Serial.println(keycode);
	else				serial->println(keycode);
}