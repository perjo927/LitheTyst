package com.example.lithetyst;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
                            
 
public class MuteService extends Service 
{
	private VolumeController vc;
 
    @Override
    public IBinder onBind(Intent arg0)
    {
       // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public void onCreate() 
    {
       // TODO Auto-generated method stub  
       super.onCreate();
       vc = new VolumeController(getBaseContext());
    }
    
   @Override
   public void onStart(Intent intent, int startId)
   {
      vc.mute();
   }
 
    @Override
    public void onDestroy() 
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
 
}