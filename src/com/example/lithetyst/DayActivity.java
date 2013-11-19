package com.example.lithetyst;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TableRow;
import android.widget.TextView;

public class DayActivity extends Activity {

	// Dagens datum	, eller valbart
	private static int chosenYear;
	private static int chosenMonth;
	private static int chosenDay; 
	private static Map<Integer, Integer> schedule_rows;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/// Daglayout
		setContentView(R.layout.activity_day);
		
		// F�nga datum fr�n ev. omstart 
		Bundle extras = getIntent().getExtras(); 
		if (extras != null) {
		    chosenYear = extras.getInt("year");
		    chosenMonth = extras.getInt("month");
		    chosenDay = extras.getInt("day");
		} else {
			// S�tt dagens datum
			getTodaysDate();
		}
		
		// skapa virtuellt schema
		fillSchedule();
		// Fyll p� schemat 
        fillTableEvents();
		// Visa r�tt dag
		getActualDay();

		
        // Skapa lyssnare til datumv�ljaren
		Button datePickerButton = (Button) findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                  new DatePickerDialog(DayActivity.this, dateListener, 
                		  chosenYear, chosenMonth-1, chosenDay).show();
            }
        });
        
	}

	///// MENY /////
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.day, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	// TODO: ev. fler ikoner
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings_top:
	        	startSyncActivity();
	            return true;
	    	case R.id.action_vibrate_top:
	        case R.id.action_vibrate:
		        // 
		    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    	v.vibrate(500);
		        // Toggla vibration med vibration = !toggle_vibration el dyl
	            return true;
	        case R.id.action_settings:
	        	startSyncActivity();
	            return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	///// SLUT MENY /////
	
	
	////////////////////////////////////////
	// F�nga dagens datum (default) och spara
	private void getTodaysDate(){
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();	
		chosenYear = today.year;
		chosenMonth = today.month+1;
		chosenDay = today.monthDay;
	}
	
	// Fyll i virtuellt schema, som ska populera tabell
	private void fillSchedule() {
		schedule_rows = new HashMap<Integer, Integer>();
		schedule_rows.put(8, R.id.tableRow1);
		schedule_rows.put(9, R.id.tableRow2);
		schedule_rows.put(10, R.id.tableRow3);
		schedule_rows.put(11, R.id.tableRow4);
		schedule_rows.put(12, R.id.tableRow5);
		schedule_rows.put(13, R.id.tableRow6);
		schedule_rows.put(14, R.id.tableRow7);
		schedule_rows.put(15, R.id.tableRow8);
		schedule_rows.put(16, R.id.tableRow9);
		schedule_rows.put(17, R.id.tableRow10);
		schedule_rows.put(18, R.id.tableRow11);
		schedule_rows.put(19, R.id.tableRow12);
		schedule_rows.put(20, R.id.tableRow13);
		schedule_rows.put(21, R.id.tableRow14);
	}
	
	private void getActualDay() {
		// Valt datum
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(chosenYear, chosenMonth, chosenDay);
		calendar.setTime(date );
		// Dagnamn att fylla kalenderhuvudet med
	    String[] days = new String[] { "Torsdag", "Fredag", "L�rdag",
	    		"Sunday", "Monday", "Tisdag", "Onsdag"	};
	    String day = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
		// Foga in
	    TextView dayTextView = (TextView) findViewById(R.id.textView2);
	    dayTextView.setText("Datum: " + chosenYear + "/" + chosenMonth
	    		+ "/" + chosenDay + "\n" + "(" + day + ")");
	}
	
	// TODO: Rensa schemavyn h�r ist�llet f�r refreshActivity?
	// Fyll kalendervyn med events fr�n schemat
	private void fillTableEvents(){
		Events event_handler = new Events(); // Se Events.java
		if (event_handler.fileExists("schema.ical",getBaseContext()))
		{
			// H�mta events
			ArrayList< Map<String, String> > events =
			event_handler.get_events(getBaseContext());
			// Rader till tabellen
			ArrayList<TableRow> tables = new ArrayList<TableRow>(); 
			// Utseende p� tabellen
			TableRow.LayoutParams params = 
			new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT);
			
			// Loopa igenom alla events
			for (int i = 0; i < events.size(); i++)
			{
				Map <String, String> event = events.get(i);
				// Anv�nd bara den aktuella dagens events
				if (event.get("start-year").equals(Integer.toString(chosenYear)) && 
					event.get("start-month").equals(Integer.toString(chosenMonth)) && 
					event.get("start-day").equals(Integer.toString(chosenDay)))
				{
					tables.clear(); // rensa array
					// L�gg till ett block med en kalenderh�ndelse
					Integer hours = Integer.parseInt(event.get("end-hour")) - Integer.parseInt(event.get("start-hour"));
					for (int y = 0; y < hours; y++)
					{
						tables.add((TableRow) findViewById( schedule_rows.get( Integer.parseInt(event.get("start-hour")) +y )));
					}
					
					TextView info = new TextView(getBaseContext());
					
					info.setText(event.get("summary"));
					info.setBackgroundResource(R.color.redorange);
					info.setLayoutParams(params);
					info.setSingleLine(false);
					tables.get(0).addView(info);
					
					for (int y = 1; y < tables.size(); y++)
					{
						TextView block = new TextView(getBaseContext());
						block.setBackgroundResource(R.color.redorange);
						block.setLayoutParams(params);
						tables.get(y).addView(block);
					}
				}
			
			}
		}
    } // fillTableEvents

	// H�r v�ljer man vilken dag man vill kolla schemat f�r
	private DatePickerDialog.OnDateSetListener dateListener =
    		new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int month, int day){         
                chosenYear=year;
                chosenMonth=month+1;
                chosenDay=day;

                // Uppdatera vyn			    
                refreshActivity();
            }
    };
	
    
    
    ///////////////////////////////////////
	//// Starta aktiviteter ///
    /*
	public void startSettingsActivity() {
		//
		Intent intent = new Intent(this, SettingsMenuActivity.class);
		startActivity(intent);
	}*/
	//// Starta aktiviteter ///
	public void startSyncActivity() {
		//
		Intent intent = new Intent(this, SyncActivity.class);
		startActivity(intent);
	}
	
	public void refreshActivity () {

		// Starta om, skicka med nya datumparametrar
		Intent myIntent = new Intent(this, DayActivity.class);
		myIntent.putExtra("year", chosenYear);
		myIntent.putExtra("month", chosenMonth);
		myIntent.putExtra("day", chosenDay);
		finish();
		startActivity(myIntent);
		
		/* 
		// virtuellt schema
		fillSchedule();
		// Fyll p� schemat 
        fillTableEvents();
		// Visa r�tt dag
		getActualDay();
		*/
	}
	//// Slut aktiviteter ////
}
