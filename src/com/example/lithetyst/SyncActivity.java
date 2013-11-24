package com.example.lithetyst;

import android.os.Bundle;
import android.os.Vibrator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("DefaultLocale")
public class SyncActivity extends Activity
{

	Button sync_button;
	EditText link_input;
	Settings set;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
		
		sync_button = (Button) findViewById(R.id.sync_button);
		link_input   = (EditText)findViewById(R.id.link_input);
		set  = new Settings(getBaseContext());
		createListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.sync, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_schedule_top:
	    		startDayActivity();
	    		return true;
	    	case R.id.action_vibrate_top:
	        case R.id.action_vibrate:
		        // 
		    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	
		    	if (set.getVibrate())
		    	{
		    		set.setVibrate(false);
		    	}
		    	else
		    	{
		    		set.setVibrate(true);		
			    	v.vibrate(500);
		    	}
	            return true;
	        case R.id.action_schedule:
	        	startDayActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void startDayActivity() {
		Intent intent = new Intent(this, DayActivity.class);
		startActivity(intent);
	}
	
	@SuppressLint("DefaultLocale")
	private void createListeners() 
	{
		   sync_button.setOnClickListener(new OnClickListener() {
		            @SuppressLint("DefaultLocale")
					public void onClick(View v) 
		            {
						sync();
				
		            }
		        });	
	}
	
	@SuppressLint("DefaultLocale")
	private void sync()
	{
		// url gotten from the text input
		String group = link_input.getText().toString().toUpperCase(); 
		String ical_link = DownloadIcal.get_ical_link(group);
		
		if (DownloadIcal.save_file(ical_link,getBaseContext()))
		{
			MuteManager mg = new MuteManager(getBaseContext());
			mg.set_next_mute();
			mg.set_next_unmute();
			set.setCalendar(ical_link);
			startDayActivity();
		}
		else
		{
			// TODO:
			Context context = getApplicationContext();
			CharSequence text = "Kunde inte hitta schemat!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}
}
