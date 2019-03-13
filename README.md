# Vibrate Demo 
[![Build Status](https://travis-ci.com/sierleunam/VibrateDemo.svg?branch=master)](https://travis-ci.com/sierleunam/VibrateDemo)
Sample app to vibrate when clicked on the inteface button or by sending intents to it.

## Operation mode

### App interface
use the default time value or set a vibration time in miliseconds
press the button

### Sending Intents
send an intent with: 
 
	"com.sysdevmobile.vibratedemo.VIBRATE"
	
and optionally add a Long extra named
 	
		"time" 
		
with the time in miliseconds.

