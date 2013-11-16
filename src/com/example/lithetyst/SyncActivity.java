package com.example.lithetyst;

import android.os.Bundle;
import android.os.Vibrator;
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


public class SyncActivity extends Activity
{

	Button sync_button;
	EditText link_input;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
		
		sync_button = (Button) findViewById(R.id.sync_button);
		link_input   = (EditText)findViewById(R.id.link_input);
		createListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.sync, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	// TODO: Rätt meny
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
		    	v.vibrate(500);
		        // Toggla vibration med vibration = !toggle_vibration el dyl
	            return true;
	        case R.id.action_schedule:
	        	startDayActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void startTabActivity() {
		Intent intent = new Intent(this, TabActivity.class);
		startActivity(intent);
	}
	
	public void startDayActivity() {
		Intent intent = new Intent(this, DayActivity.class);
		startActivity(intent);
	}
	
	private void createListeners() 
	{
		   sync_button.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
						sync();
				
		            }
		        });	
	}
	
	private void sync()
	{
		// url gotten from the text input
		String group = link_input.getText().toString().toUpperCase(); 
		String ical_link = DownloadIcal.get_ical_link(group);
		
		if (DownloadIcal.save_file(ical_link,getBaseContext()))
		{
			startTabActivity();
			MuteManager mg = new MuteManager(getBaseContext());
			mg.set_next_mute();
			mg.set_next_unmute();
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
