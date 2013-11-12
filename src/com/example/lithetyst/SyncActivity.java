package com.example.lithetyst;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
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
		String url = link_input.getText().toString(); // url gotten from the text input
		if (DownloadIcal.save_file(url,getBaseContext()))
		{
			System.out.println("Success");
		}
		else
		{
			System.out.println("Failed");
		}
	}
}
