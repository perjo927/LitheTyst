package com.example.lithetyst; 


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Settings set = new Settings(getBaseContext());
		if (set.getCalendar().equals(""))
		{
			Intent intent = new Intent(this, SyncActivity.class);
			startActivity(intent);
		}
		else
		{
			Intent intent = new Intent(this, DayActivity.class);
			startActivity(intent);	
		}
	}
}
