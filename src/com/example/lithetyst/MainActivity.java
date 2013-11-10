package com.example.lithetyst; // byt namn 


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.EditText;


public class MainActivity extends Activity {

	//
    public final static String EXTRA_MESSAGE = "com.example.lithetyst.MESSAGE";
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
	}


	/// onClick-händelser! ///

	/*
	// button onclick = sendmessage! 
	public void sendMessage(View view) {
		// Vad vill vi starta?
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    // Hitta redigeringsrutan
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    // Fånga texten
	    String message = editText.getText().toString();
	    // Skicka vidare till nya aktivitet
	    intent.putExtra(EXTRA_MESSAGE, message);
	    // Starta ny aktivitet
	    startActivity(intent);
	}
	*/
	/// button onclick = startTabActivity! 
	/*
	public void startCalendar(View view) {
	    Intent intent = new Intent(this, CalendarActivity.class);
	    startActivity(intent);
	}
	*/
	
	
	
	//// FLIKVY ////
	public void startTabActivity(View view) {
	    Intent intent = new Intent(this, TabActivity.class);
	    String message = "Nu vill vi öppna tab-vy";
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	////
	
	/** på/av */
	/*
	public void unMute(View view) {
		//vc.mute();		
		System.out.println("unmute");
	}
	public void mute(View view) {
		//vc.restoreVolume();
		System.out.println("mute");
	}
	*/
	/*
    // Meningslös aktivitet
    public void startSecondActivity(View view) {
    	   Intent intent = new Intent(this, SecondActivity.class);
    	   intent.putExtra("BUTTON NUMBER", 1337);
    	   startActivity(intent);
    	}  
    	*/
	/// slut onClick ///

    
	// Main-meny
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
		
		// Inflate the menu; this adds items to the action bar if it is present.
	    // getMenuInflater().inflate(R.menu.main, menu);
	    // return true;
	}
	
	// Vad göra med menyval?
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
        	case R.id.action_about:
        		System.out.println("About");
        		return true;
        	case R.id.action_help:
        		System.out.println("Help");
        		return true;
	        case R.id.action_settings_top:
	            //openSettings(); // startActivity
	        	System.out.println("Settings top");
	            return true;
	        case R.id.action_settings:
	            //openSettings(); // startActivity
	        	System.out.println("open settings");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	// Slut meny	
	
}
