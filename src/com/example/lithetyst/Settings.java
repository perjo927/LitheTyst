/*
 * Initialize the class from a class that extends Activity,
 * send getBaseContext() as an argument to the constructor.
 * */

package com.example.lithetyst;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
	
	private String FILENAME = "settings";
	private SharedPreferences preferences;
	
	public Settings(Context context) {
		preferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
	}
	
	public void setCalendar(String calendar){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("calendar", calendar);
		editor.commit();
	}
	
	public String getCalendar(){
		return preferences.getString("calendar", "");
	}
	
	// expected value for language: "en"/"swe"
	public void setLanguage(String language){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("language", language);
		editor.commit();
	}
	
	public String getLanguage(){
		return preferences.getString("language", "");
	}
	
	// expected value for view: "day"/"week"/"month"
	public void setView(String view){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("view", view);
		editor.commit();
	}
	
	public String getView(){
		return preferences.getString("view", "");
	}
	
	public void setVibrate(boolean vibrate){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("vibrate", vibrate);
		editor.commit();
	}
	
	public boolean getVibrate(){
		return preferences.getBoolean("vibrate", true);
	}
	
}
