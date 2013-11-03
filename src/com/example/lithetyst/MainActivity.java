package com.example.lithetyst; // byt namn 

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	// Kalenderdeklarationer
    private CalendarView calendarView;
    private int yr, mon, dy;
    private Calendar selectedDate;
	////
    
    // Hämtas av getIntent() 
    public final static String EXTRA_MESSAGE = "com.example.lithetyst.MESSAGE";
    
    // Bantonkod
    //Button b1, b2;
	VolumeController vc;// = new VolumeController();	
	// 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// På och av
		 //b1 = (Button) findViewById(R.id.button1);
		 //b2 = (Button) findViewById(R.id.button2);
		 // knapplyssnare
		 //createListeners();
		 
		//// KALENDERKOD ////
		Calendar c = Calendar.getInstance();
        yr = c.get(Calendar.YEAR);
        mon = c.get(Calendar.MONTH);
        dy = c.get(Calendar.DAY_OF_MONTH);
        
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        Button datePickerButton = (Button) findViewById(R.id.date_picker_button);
        
        // egen lyssnare för denna knapp
        datePickerButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                  new DatePickerDialog(MainActivity.this, dateListener, yr, mon, dy).show();
            }
        });
        
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    Toast.makeText(getApplicationContext(), 
                    		"Selected date is "+(month+1)+"-"+dayOfMonth+"-"+ year,
                    		Toast.LENGTH_SHORT).show();
                }
            });
	}
	//// SLUT KALENDERKOD ////
	
	/** button onclick = sendmessage! */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	/** button onclick = startTabActivity! */
	public void startTabActivity(View view) {
	    Intent intent = new Intent(this, TabActivity.class);
	    String message = "Nu vill vi öppna tab-vy";
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	/** på/av */
	public void unMute(View view) {
	    // Do something in response to button
		//vc.mute();		
		System.out.println("unmute");
	}
	public void mute(View view) {
	    // Do something in response to button
		//vc.restoreVolume();
		System.out.println("mute");
	}
	
	
    // 
    public void startSecondActivity(View view) {
    	   Intent intent = new Intent(this, SecondActivity.class);
    	   intent.putExtra("BUTTON NUMBER", 1337);
    	   startActivity(intent);
    	}
    
	
	/* Lyssnare
	private void createListeners() {
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	//startSecondActivity(1);
            }
        });
        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	//startSecondActivity(1);
            	//vc.mute();
            }
        });
    }
    */

	// Main-meny! Top/bottom ?
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
	            //openSettings();
	        	System.out.println("Settings top");
	            return true;
	        case R.id.action_settings:
	            //openSettings();
	        	System.out.println("open settings");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	//// KALENDERKOD
    private DatePickerDialog.OnDateSetListener dateListener =
    		new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                selectedDate=Calendar.getInstance();
                
                yr=year;
                mon=monthOfYear;
                dy=dayOfMonth;
                
                selectedDate.set(yr, mon, dy);
               
                calendarView.setDate(selectedDate.getTimeInMillis());
            }
        };
        //// SLUT KALENDERKOD

}
