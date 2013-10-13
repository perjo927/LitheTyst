package com.example.lithetyst;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.DatePickerDialog;

import java.util.Calendar;

import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.Button;

public class MainActivity extends Activity {
    private CalendarView calendarView;
    private int yr, mon, dy;
    private Calendar selectedDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Calendar c = Calendar.getInstance();
        yr = c.get(Calendar.YEAR);
        mon = c.get(Calendar.MONTH);
        dy = c.get(Calendar.DAY_OF_MONTH);
        
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        Button datePickerButton = (Button) findViewById(R.id.date_picker_button);
        
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

}
