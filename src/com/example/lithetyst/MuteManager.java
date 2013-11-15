package com.example.lithetyst;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MuteManager 
{
	private Context ctx;
	public MuteManager(Context _ctx)
	{
		ctx = _ctx;
	}
	
	private void mute(String year, String month, String day, String hour, String minute)
	{
		PendingIntent pendingIntent;
		Calendar calendar = Calendar.getInstance();
        
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
       
        Intent myIntent = new Intent(ctx, MuteReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, myIntent,0);
        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	
	private void unmute(String year, String month, String day, String hour, String minute)
	{
		PendingIntent pendingIntent;
		Calendar calendar = Calendar.getInstance();
        
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
       
        Intent myIntent = new Intent(ctx, UnmuteReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, myIntent,0);
        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        
	}
	
	public void set_next_mute()
	{
		Calendar calendar = Calendar.getInstance();
		Events event_handler = new Events();
		ArrayList< Map<String, String> >events = event_handler.get_events(ctx);
		
		for (int i = 0; i < events.size(); i++)
		{
			Map <String, String> event = events.get(i);
			
			
			String year, month, day, hour, minute;
			year = Integer.toString(calendar.YEAR);
			month = Integer.toString(calendar.MONTH+1);
			day = Integer.toString(calendar.DAY_OF_MONTH);
			hour = Integer.toString(calendar.HOUR_OF_DAY);
			minute = Integer.toString(calendar.MINUTE);
			System.out.println(year);
			System.out.println(month);
			System.out.println(day);
			System.out.println(hour);
			System.out.println(minute);
			if (month.length() == 1)
			{
				month = "0" + month;
			}
			if (day.length() == 1)
			{
				day = "0" + day;
			}
			if (hour.length() == 1)
			{
				hour = "0" + hour;
			}
			if (minute.length() == 1)
			{
				minute = "0" + minute;
			}
			
			Long event1 = Long.parseLong(year+month+day+hour+minute);
			
			
			Long event2 = Long.parseLong(event.get("start-year")+event.get("start-month")+
					event.get("start-day")+event.get("start-hour")+event.get("start-minute"));
			
			if (event1 < event2)
			{
				mute(event.get("start-year"),event.get("start-month"),event.get("start-day"),event.get("start-hour"),event.get("start-minute"));
				return;
			}
		}
		return;
	}
	
	public void set_next_unmute()
	{
		Calendar calendar = Calendar.getInstance();
		Events event_handler = new Events();
		ArrayList< Map<String, String> >events = event_handler.get_events(ctx);
		
		for (int i = 0; i < events.size(); i++)
		{
			Map <String, String> event = events.get(i);
			
			String year, month, day, hour, minute;
			year = Integer.toString(calendar.YEAR);
			month = Integer.toString(calendar.MONTH+1);
			day = Integer.toString(calendar.DAY_OF_MONTH);;
			hour = Integer.toString(calendar.HOUR_OF_DAY);;
			minute = Integer.toString(calendar.MINUTE);;
			
			if (month.length() == 1)
			{
				month = "0" + month;
			}
			if (day.length() == 1)
			{
				day = "0" + day;
			}
			if (hour.length() == 1)
			{
				hour = "0" + hour;
			}
			if (minute.length() == 1)
			{
				minute = "0" + minute;
			}
			
			Long event1 = Long.parseLong(year+month+day+hour+minute);
									
			Long event2 = Long.parseLong(event.get("end-year")+event.get("end-month")+
					event.get("end-day")+event.get("end-hour")+event.get("end-minute"));
			
			if (event1 < event2)
			{
				unmute(event.get("end-year"),event.get("end-month"),event.get("end-day"),event.get("end-hour"),event.get("end-minute"));
				return;
			}
		}
		return;
	}

}
