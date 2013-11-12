package com.example.lithetyst;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


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
	    inflater.inflate(R.menu.settings_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_calendar_top:
	    		startTabActivity();
	    		return true;
	        case R.id.action_calendar:
	        	startTabActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void startTabActivity() {
		Intent intent = new Intent(this, TabActivity.class);
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
		String group = link_input.getText().toString(); // url gotten from the text input
		String ical_link = DownloadIcal.get_ical_link(group);
		
		if (DownloadIcal.save_file(ical_link,getBaseContext()))
		{
			System.out.println("Success");
		}
		else
		{
			System.out.println("Failed");
		}
	}
}
