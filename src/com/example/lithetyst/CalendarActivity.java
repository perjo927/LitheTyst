package com.example.lithetyst;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class CalendarActivity extends Activity {

	//
    public final static String YEAR = "com.example.lithetyst.YEAR";
    public final static String MONTH = "com.example.lithetyst.MONTH";
    public final static String DAY = "com.example.lithetyst.DAY";
    public final static String TABTYPE = "com.example.lithetyst.TABTYPE";
	
	// Kalenderdeklarationer
    private CalendarView calendarView;
    private int yr, mon, dy;
    private Calendar selectedDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
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
                  new DatePickerDialog(CalendarActivity.this, dateListener, yr, mon, dy).show();
            }
        });
        
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    Toast.makeText(getApplicationContext(), 
                    		"Det valda datumet är: "+(month+1)+"-"+dayOfMonth+"-"+ year,
                    		Toast.LENGTH_SHORT).show();
                    
                    startDayView(year, month, dayOfMonth);

                }
            });		
	}

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
       
   private void startDayView(int year, int month, int dayOfMonth) {
		// Vad vi vill starta
	    Intent intent = new Intent(this, TabActivity.class);
	    
	    // Konvertera
	    String strYear = Integer.toString(year);
	    String strMonth = Integer.toString(month);
	    String strDay = Integer.toString(dayOfMonth);
	    
	    // Skicka vidare till nya aktivitet
	    intent.putExtra(YEAR, strYear);
	    intent.putExtra(MONTH, strMonth);
	    intent.putExtra(DAY, strDay);
	    intent.putExtra(TABTYPE, "day");
	    
		System.out.println(strYear+ strMonth+ strDay);
	    
	    // Starta ny aktivitet
	    startActivity(intent);
   }
}
