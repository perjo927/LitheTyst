package com.example.lithetyst;
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
public class UnmuteReceiver extends BroadcastReceiver
{
      
    @Override
    public void onReceive(Context context, Intent intent)
    {
       Intent service1 = new Intent(context, UnmuteService.class);
       context.startService(service1);
        
    }   
}