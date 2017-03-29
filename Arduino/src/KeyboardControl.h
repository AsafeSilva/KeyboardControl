#pragma once


#include <Arduino.h>
#include <SoftwareSerial.h>

#include "KeyCodes.h"

class KeyboardControl{

private:

	SoftwareSerial *serial;

	void sendData(String keycode);

public:
	KeyboardControl();
	KeyboardControl(SoftwareSerial *serial);

	void keyPress(int keycode);
	void keyRelease(int keycode);
	void keyClick(int keycode);
};