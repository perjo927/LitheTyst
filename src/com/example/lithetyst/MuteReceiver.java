package com.example.lithetyst;
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
public class MuteReceiver extends BroadcastReceiver
{
      
    @Override
    public void onReceive(Context context, Intent intent)
    {
       Intent service1 = new Intent(context, MuteService.class);
       context.startService(service1);
        
    }   
}