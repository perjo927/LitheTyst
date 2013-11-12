/*
 * Initialize the class from a class that extends Activity,
 * send getBaseContext() as an argument to the constructor.
 * */

package com.example.lithetyst;

import android.content.Context; 
import android.media.AudioManager; 


public class VolumeController {
	
	private static AudioManager audioManager;
	Settings settings;
      
	public VolumeController(Context context){
		audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		settings = new Settings(context);
	}
	
	public void mute(){
		
		int ringerMode = 0;
		if ( settings.getVibrate() ){
			ringerMode = AudioManager.RINGER_MODE_VIBRATE;
		}
		else{
			ringerMode = AudioManager.RINGER_MODE_SILENT;
		}
		
		audioManager.setRingerMode(ringerMode);
	}
	
	public void unmute(){
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}
	
}
