package com.example.lithetyst;

import java.util.Calendar;

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
	
	public void mute(String year, String month, String day, String hour, String minute, String seconds)
	{
		PendingIntent pendingIntent;
		Calendar calendar = Calendar.getInstance();
        
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, Integer.parseInt(seconds));
       
        Intent myIntent = new Intent(ctx, MuteReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, myIntent,0);
        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	
	public void unmute(String year, String month, String day, String hour, String minute, String seconds)
	{
		PendingIntent pendingIntent;
		Calendar calendar = Calendar.getInstance();
        
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, Integer.parseInt(seconds));
       
        Intent myIntent = new Intent(ctx, UnmuteReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctx, 0, myIntent,0);
        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        
	}

}
