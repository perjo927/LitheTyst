package com.example.lithetyst;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.StrictMode;

public class DownloadIcal 
{
	
	public static boolean save_file(String url_string,Context ctx)
	{
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		try
		{
			FileOutputStream fos = ctx.openFileOutput("schema.ical", Context.MODE_PRIVATE);
		    
		    // Create a URL for the desired page
		    
		    URL url = new URL(url_string);
	
		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;
		    while ((line = in.readLine()) != null) 
		    {
		    	fos.write((line+"!SEP").getBytes());
		    }
		    fos.close();
		    in.close();
		    return true;
		}
		catch (MalformedURLException e) 
		{
			return false;
		} 
		catch (IOException e) 
		{
			return false;
		}	
	}
}
