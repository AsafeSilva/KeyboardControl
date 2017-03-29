#pragma once

#define KC_0	48
#define KC_1	49
#define KC_2	50
#define KC_3	51
#define KC_4	52
#define KC_5	53
#define KC_6	54
#define KC_7	55
#define KC_8	56
#define KC_9	57

#define KC_A	65
#define KC_B	66
#define KC_C	67
#define KC_D	68
#define KC_E	69
#define KC_F	70
#define KC_G	71
#define KC_H	72
#define KC_I	73
#define KC_J	74
#define KC_K	75
#define KC_L	76
#define KC_M	77
#define KC_N	78
#define KC_O	79
#define KC_P	80
#define KC_Q	81
#define KC_R	82
#define KC_S	83
#define KC_T	84
#define KC_U	85
#define KC_V	86
#define KC_W	87
#define KC_X	88
#define KC_Y	89
#define KC_Z	90

#define KC_UNDO		65483
#define KC_COPY		65485
#define KC_PASTE	65487
#define KC_CUT		65489

#define KC_EQUALS				61
#define KC_MULTIPLY				106
#define KC_SUBTRACT				109
#define KC_DIVIDE				111
#define KC_CIRCUMFLEX			514
#define KC_DOLLAR				515
#define KC_EURO_SIGN			516
#define KC_EXCLAMATION_MARK		517
#define KC_LEFT_PARENTHESIS		519
#define KC_PLUS					521
#define KC_RIGHT_PARENTHESIS	522
#define KC_UNDERSCORE			523

#define KC_TAB			9
#define KC_ENTER		10
#define KC_SHIFT		16
#define KC_CONTROL		17
#define KC_ALT			18
#define KC_CAPS_LOCK	20
#define KC_ESCAPE		27
#define KC_SPACE		32
#define KC_PAGE_UP		33
#define KC_PAGE_DOWN	34
#define KC_END			35
#define KC_HOME			36
#define KC_LEFT			37
#define KC_UP			38
#define KC_RIGHT		39
#define KC_DOWN			40
#define KC_F1			112
#define KC_F2			113
#define KC_F3			114
#define KC_F4			115
#define KC_F5			116
#define KC_F6			117
#define KC_F7			118
#define KC_F8			119
#define KC_F9			120
#define KC_F10			121
#define KC_F11			122
#define KC_F12			123
#define KC_DELETE		127
#define KC_NUM_LOCK		144
#define KC_SCROLL_LOCK	145
#define KC_PRINTSCREEN	154
#define KC_INSERT		155
#define KC_HELP			156
#define KC_WINDOWS		524


#include <Arduino.h>
#include <SoftwareSerial.h>

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