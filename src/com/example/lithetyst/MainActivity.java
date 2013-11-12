package com.example.lithetyst; // byt namn 


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;



public class MainActivity extends Activity {

	//
    public final static String EXTRA_MESSAGE = "com.example.lithetyst.MESSAGE";
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
	}

	
	//// FLIKVY ////
	public void startTabActivity(View view) {
	    Intent intent = new Intent(this, TabActivity.class);
	    String message = "Nu vill vi öppna tab-vy";
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
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
